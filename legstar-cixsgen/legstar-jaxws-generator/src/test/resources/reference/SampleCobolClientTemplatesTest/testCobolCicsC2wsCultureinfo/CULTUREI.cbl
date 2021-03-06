       PROCESS XOPTS(APOST)
       PROCESS NOSEQ LIB OPTIMIZE(FULL)
       IDENTIFICATION DIVISION.
       PROGRAM-ID. CULTUREI.
      *****************************************************************
      * OVERVIEW                                                      *
      * --------                                                      *
      * Sample transaction calling a remote service using LegStar     *
      * HTTP client C API (LSHTTAPI).                                 *
      * The HTTP body contains a formatted LegStarMessage.            *
      * It is expected that the receiver will use LegStar COBOL       *
      * binding to convert payload to a Java object.                  *
      *                                                               *
      * Program generated by LegStar Mainframe to Jaxws generator.    *
      * Follow the TODO markers to customize this program.            *
      *****************************************************************

       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       DATA DIVISION.
      *****************************************************************
      *        W O R K I N G    S T O R A G E    S E C T I O N        *
      *****************************************************************
       WORKING-STORAGE SECTION.
       
      *---------------------------------------------------------------*
      *  Constants                                                    *
      *---------------------------------------------------------------*
      * Address of service provider.
      *    
       77  W00-SERVICE-URI PIC X(59) VALUE 'http://192.168.0.112:8080/c2
      -    'ws-cultureinfo/cultureinfoProxy'.

      *    
      * Service credentials.
      *    
       77  W00-USERID                    PIC X(8) VALUE SPACES.
       77  W00-PASSWORD                  PIC X(8) VALUE SPACES.
      *    
      * Service requested.
      *    
       77  W00-SERVICE-NAME PIC X(11) VALUE 'cultureinfo'.

           
      *---------------------------------------------------------------*
      *  CICS API parameters                                          *
      *---------------------------------------------------------------*
       01  W03-RESP                      PIC S9(9) BINARY VALUE 0.
           88  OK-CODE            VALUE 0.
           88  ERROR-CODE         VALUE -1.
       01  W03-RESP2                     PIC S9(9) BINARY VALUE 0.

      *---------------------------------------------------------------*
      * LSHTTAPI API parameters                                       *
      * C Structures are aligned on natural storage boundaries so we  *
      * need to specify SYNCHRONIZED.                                 *
      * The last character of each string is reserved to hold a       *
      * C string delimiter.                                           *
      *---------------------------------------------------------------*
       01  LAPI-TRACE-PARMS SYNCHRONIZED.
           05 LAPI-TRACE-ID              PIC X(17) VALUE SPACES.
           05 LAPI-TRACE-MODE            PIC S9(8) BINARY VALUE 1.
              88 TRACES-OFF       VALUE 0.
              88 TRACES-ON        VALUE 1.
           05 LAPI-ERROR-MESSAGE         PIC X(266) VALUE SPACES.

       01  LAPI-INVOKE-PARMS SYNCHRONIZED.
           05  LAPI-URI                  PIC X(513) VALUE SPACES.
           05  LAPI-SERVICE-NAME         PIC X(33) VALUE SPACES.
           05  LAPI-REQUEST-DATA         POINTER VALUE NULL.
           05  LAPI-REQUEST-DATA-LEN     PIC S9(8) BINARY VALUE ZERO.
           05  LAPI-REPLY-DATA           POINTER VALUE NULL.
           05  LAPI-REPLY-DATA-LEN       PIC S9(8) BINARY VALUE ZERO.
           05  LAPI-OPTIONS.
               10  LAPI-CONNECT-TIMEOUT  PIC 9(9) BINARY VALUE 3.
               10  LAPI-RECV-TIMEOUT     PIC 9(9) BINARY VALUE 10.
               10  LAPI-PROXY-URI        PIC X(513) VALUE SPACES.
               10  LAPI-USERID           PIC X(33) VALUE SPACES.
               10  LAPI-PASSWORD         PIC X(33) VALUE SPACES.
           
      *---------------------------------------------------------------*
      *  Work variables                                               *
      *---------------------------------------------------------------*
       01  ERROR-MESSAGE          PIC X(78) VALUE SPACES.
           88 NO-ERROR-MESSAGE VALUE SPACES.

      *---------------------------------------------------------------*
      *  Request parameters expected by target web service            *
      *---------------------------------------------------------------*
       01 COM-REQUEST.
           02  GetInfo.
             03  arg0.
               04  cultureCode PIC X(32) DISPLAY.
               04  decimalNumber PIC 9(7)V9(2) COMP-3.

       
      *****************************************************************
      *            L I N K A G E       S E C T I O N                  *
      *****************************************************************
       LINKAGE SECTION.

      *---------------------------------------------------------------*
      *  Reply parameters as returned by target web service           *
      *---------------------------------------------------------------*
       01 COM-REPLY.
           02  GetInfoResponse.
             03  R-return.
               04  currencySymbol PIC X(32) DISPLAY.
               04  displayCountry PIC X(32) DISPLAY.
               04  displayLanguage PIC X(32) DISPLAY.
               04  formattedDate PIC X(32) DISPLAY.
               04  formattedDecimalNumber PIC X(32) DISPLAY.
               04  serverCultureInfo.
                 05  cultureCode PIC X(32) DISPLAY.
                 05  displayCountry0 PIC X(32) DISPLAY.
                 05  displayLanguage0 PIC X(32) DISPLAY.

               
      *****************************************************************
      *    P R O C E D U R E  D I V I S I O N   S E C T I O N         *
      *****************************************************************
       PROCEDURE DIVISION.

           PERFORM PROLOG THRU
               END-PROLOG.

           PERFORM INVOKE-SERVICE THRU
               END-INVOKE-SERVICE.
               
           PERFORM EPILOG THRU
               END-EPILOG.

           GOBACK.
       
      *---------------------------------------------------------------*
      *  Initialize the c2ws API. You can turn traces on and specify  *
      *  a trace identifier.                                          *
      *---------------------------------------------------------------*
       PROLOG.

           DISPLAY
               'CULTUREI STARTING ==============================='. 
      *
      * Initialize c2ws API passing trace parameters
      *    
           MOVE 'CULTUREI' TO LAPI-TRACE-ID.
           
           CALL 'init' USING dfheiblk LAPI-TRACE-PARMS
                       RETURNING W03-RESP.
           IF (NOT OK-CODE)
               MOVE 'INITIALIZE-LSHTTAPI failed' TO ERROR-MESSAGE
               PERFORM ABORT-PROGRAM THRU
                   END-ABORT-PROGRAM
           END-IF.

      *
      * Setup invoke parameters
      *    
           MOVE W00-SERVICE-URI   TO LAPI-URI.
           MOVE W00-USERID        TO LAPI-USERID.
           MOVE W00-PASSWORD      TO LAPI-PASSWORD.
           MOVE W00-SERVICE-NAME  TO LAPI-SERVICE-NAME.

           PERFORM SET-REQUEST THRU
               END-SET-REQUEST.

           SET LAPI-REQUEST-DATA     TO ADDRESS OF COM-REQUEST.
           MOVE LENGTH OF COM-REQUEST TO LAPI-REQUEST-DATA-LEN.
           
           DISPLAY 'PROLOG ENDED'.
           
       END-PROLOG.   EXIT.
      
      *---------------------------------------------------------------*
      *  Populate the request parameters                              *
      *---------------------------------------------------------------*
       SET-REQUEST.

           DISPLAY 'SET-REQUEST STARTED'.

      *  TODO set input values in COM-REQUEST                         *
           
           DISPLAY 'SET-REQUEST ENDED'.

       END-SET-REQUEST.   EXIT.
       
      *---------------------------------------------------------------*
      *  Invoke target service and analyze response                   *
      *---------------------------------------------------------------*
       INVOKE-SERVICE.

           DISPLAY 'ABOUT TO INVOKE-SERVICE'.
      *
      * Invoke target web service
      *    
           CALL 'invoke' USING LAPI-INVOKE-PARMS
                         RETURNING W03-RESP.
           IF (NOT OK-CODE)
               MOVE LAPI-ERROR-MESSAGE TO ERROR-MESSAGE
               PERFORM ABORT-PROGRAM THRU
                   END-ABORT-PROGRAM
           END-IF.
           
           SET ADDRESS OF COM-REPLY TO LAPI-REPLY-DATA.

           PERFORM PRINT-RESULTS THRU
               END-PRINT-RESULTS.

           DISPLAY 'INVOKE-SERVICE SUCCESS'.
           
       END-INVOKE-SERVICE.   EXIT.
      
      *---------------------------------------------------------------*
      *  Display results returned from target web service             *
      *---------------------------------------------------------------*
       PRINT-RESULTS.
       
      *  TODO do something useful with data returned in  COM-REPLY    *
           
       END-PRINT-RESULTS.   EXIT.
       
      *---------------------------------------------------------------*
      *  Terminate program.                                           *
      *---------------------------------------------------------------*
       EPILOG.

           PERFORM EXIT-PROGRAM THRU
               END-EXIT-PROGRAM.
           
       END-EPILOG.   EXIT.

      *---------------------------------------------------------------*
      *  Free keyboard and return to CICS                             *
      *---------------------------------------------------------------*
       EXIT-PROGRAM.
       
           EXEC CICS SEND CONTROL FREEKB END-EXEC.
           
           DISPLAY 'CULTUREI STOPPING ==============================='.
           EXEC CICS RETURN END-EXEC.

       END-EXIT-PROGRAM.   EXIT.

      *---------------------------------------------------------------*
      *  Something went wrong. Report error and exit.                 *
      *---------------------------------------------------------------*
       ABORT-PROGRAM.
           
           PERFORM DISPLAY-ERROR-MESSAGE THRU
               END-DISPLAY-ERROR-MESSAGE.
               
           PERFORM EXIT-PROGRAM THRU
               END-EXIT-PROGRAM.

       END-ABORT-PROGRAM.   EXIT.

      *---------------------------------------------------------------*
      *  Display error messages                                       *
      *---------------------------------------------------------------*
       DISPLAY-ERROR-MESSAGE.

           EXEC CICS SEND TEXT FROM(ERROR-MESSAGE) FREEKB END-EXEC. 
           DISPLAY '************************************************'.
           DISPLAY '* ', ERROR-MESSAGE.
           DISPLAY '* COMPLETION CODE : ', W03-RESP.
           DISPLAY '* REASON CODE     : ', W03-RESP2.
           DISPLAY '************************************************'.

       END-DISPLAY-ERROR-MESSAGE.   EXIT.

       END PROGRAM CULTUREI.
