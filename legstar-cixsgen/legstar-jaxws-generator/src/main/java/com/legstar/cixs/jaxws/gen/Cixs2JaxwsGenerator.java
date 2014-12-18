/*******************************************************************************
 * Copyright (c) 2010 LegSem.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     LegSem - initial API and implementation
 ******************************************************************************/
package com.legstar.cixs.jaxws.gen;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.legstar.cixs.gen.model.AbstractCixsService;
import com.legstar.cixs.gen.model.CixsOperation;
import com.legstar.cixs.gen.model.options.CobolHttpClientType;
import com.legstar.cixs.gen.model.options.HttpTransportParameters;
import com.legstar.cixs.gen.model.options.PojoParameters;
import com.legstar.cixs.gen.model.options.ProxyTargetType;
import com.legstar.cixs.gen.model.options.TransportType;
import com.legstar.cixs.gen.model.options.WebServiceParameters;
import com.legstar.cixs.jaxws.model.AntBuildCixs2JaxwsModel;
import com.legstar.cixs.jaxws.model.CixsJaxwsService;
import com.legstar.codegen.CodeGenMakeException;
import com.legstar.codegen.CodeGenUtil;

/**
 * This Ant task creates the various Jaxws artifacts needed to implement
 * a servlet proxy that acts as an adapter for a Web Service so that a
 * mainframe program can call the target Web Service without any knowledge
 * of SOAP.
 * The task also generates a sample COBOL CICS program that demonstrates
 * how to call the proxy servlet. 
 */
public class Cixs2JaxwsGenerator extends AbstractCixsJaxwsGenerator {

    /** This generator name. */
    public static final String CIXS_TO_JAXWS_GENERATOR_NAME =
        "LegStar Mainframe to Jaxws generator";

    /** Velocity template for war ant build generation. */
    public static final String SERVICE_ANT_BUILD_WAR_VLC_TEMPLATE =
        "vlc/c2j-service-ant-build-war-xml.vm";

    /** Velocity template for service ant-deploy. */
    public static final String SERVICE_ANT_DEPLOY_VLC_TEMPLATE =
        "vlc/c2j-service-ant-deploy-xml.vm";

    /** Velocity template for web descriptor generation. */
    public static final String SERVICE_WEB_XML_VLC_TEMPLATE =
        "vlc/c2j-service-web-xml.vm";

    /** Velocity template for COBOL client using LSHTTAPI generation. */
    public static final String OPERATION_COBOL_CICS_LSHTTAPI_CLIENT_VLC_TEMPLATE =
        "vlc/c2j-operation-cobol-cics-lshttapi-client.vm";

    /** Velocity template for COBOL client using WEBAPI generation. */
    public static final String OPERATION_COBOL_CICS_WEBAPI_CLIENT_VLC_TEMPLATE =
        "vlc/c2j-operation-cobol-cics-webapi-client.vm";

    /** Velocity template for COBOL client using DFHWBCLI generation. */
    public static final String OPERATION_COBOL_CICS_DFHWBCLI_CLIENT_VLC_TEMPLATE =
        "vlc/c2j-operation-cobol-cics-dfhwbcli-client.vm";

    /** Velocity template for COBOL client using WMQ generation. */
    public static final String OPERATION_COBOL_CICS_WMQ_CLIENT_VLC_TEMPLATE =
        "vlc/c2j-operation-cobol-cics-wmq-client.vm";

    /** The service model name is it appears in templates. */
    public static final String SERVICE_MODEL_NAME = "model";

    /** Default pattern for server PATH. Must be kept in sync with
     * various velocity templates. */
    public static final String DEFAULT_SERVER_PATH_TEMPLATE =
        "/c2ws-${service.name}/${service.name}Proxy";

    /**
     * Constructor.
     */
    public Cixs2JaxwsGenerator() {
        super(new AntBuildCixs2JaxwsModel());
    }

    /** {@inheritDoc}*/
    @Override
    public void addExtendedParameters(final Map < String, Object > parameters) {
        parameters.put("structHelper", new StructuresGenerator());

        /* Contribute the target type parameters */
        switch(getProxyTargetTypeInternal()) {
        case POJO:
            getPojoTargetParameters().add(parameters);
            parameters.put("proxyTargetType", "pojo");
            break;
        case WEBSERVICE:
            parameters.put("proxyTargetType", "webservice");
            getWebServiceTargetParameters().add(parameters);
            break;
        default:
            break;
        }

        /* Contribute http parameters */
        getHttpTransportParameters().add(parameters);

    }

    /** {@inheritDoc}*/
    @Override
    public void checkExtendedExtendedInput() throws CodeGenMakeException {
        CodeGenUtil.checkDirectory(
                getTargetCobolDir(), true, "TargetCobolDir");

        /* Check parameters needed depending on target type */
        switch(getProxyTargetTypeInternal()) {
        case POJO:
            getPojoTargetParameters().check();
            break;
        case WEBSERVICE:
            getWebServiceTargetParameters().check();
            break;
        default:
            throw (new CodeGenMakeException("Missing ProxyTargetType parameter"));
        }

        /* Check that we have a URI to expose to mainframe programs */
        /* Set a sensible path using the service name. */
        if (getHttpTransportParameters().getPath() == null
                || getHttpTransportParameters().getPath().length() == 0) {
            getHttpTransportParameters().setPath(getDefaultServicePath());
        }
        getHttpTransportParameters().check();

    }

    /** {@inheritDoc}*/
    @Override
    public void generateExtended(final Map < String, Object > parameters)
    throws CodeGenMakeException {
        /* Determine target files locations */
        File serviceWebFilesDir = getTargetWDDDir();
        CodeGenUtil.checkDirectory(serviceWebFilesDir, true);
        File serviceAntFilesDir = getTargetAntDir();
        CodeGenUtil.checkDirectory(serviceAntFilesDir, true);
        File serviceCobolCicsClientDir = getTargetCobolDir();
        CodeGenUtil.checkDirectory(serviceCobolCicsClientDir, true);

        generateWebXml(
                getCixsJaxwsService(), parameters, serviceWebFilesDir);
        generateAntBuildWar(
                getCixsJaxwsService(), parameters, serviceAntFilesDir);
        generateAntDeploy(
                getCixsJaxwsService(), parameters, serviceAntFilesDir);

        for (CixsOperation operation : getCixsService().getCixsOperations()) {
            parameters.put("cixsOperation", operation);
            generateCobolCicsClient(
                    getCixsJaxwsService(), operation, parameters,
                    serviceCobolCicsClientDir,
                    TransportType.HTTP,
                    getSampleCobolHttpClientTypeInternal());
        }
    }

    /**
     * Create the Ant Build for a War file generation.
     * @param service the service description
     * @param parameters miscellaneous help parameters
     * @param serviceAntFilesDir where to store the generated file
     * @return the generated file name
     * @throws CodeGenMakeException if generation fails
     */
    public static String generateAntBuildWar(
            final CixsJaxwsService service,
            final Map < String, Object > parameters,
            final File serviceAntFilesDir)
    throws CodeGenMakeException {
        String fileName = "build-war.xml";
        generateFile(CIXS_TO_JAXWS_GENERATOR_NAME,
                SERVICE_ANT_BUILD_WAR_VLC_TEMPLATE,
                SERVICE_MODEL_NAME,
                service,
                parameters,
                serviceAntFilesDir,
                fileName,
                "UTF-8");
        return fileName;
    }

    /**
     * Create the deploy Ant Build file.
     * @param service the Jaxws service description
     * @param parameters miscellaneous help parameters
     * @param serviceAntFilesDir where to store the generated file
     * @return the generated file name
     * @throws CodeGenMakeException if generation fails
     */
    public static String generateAntDeploy(
            final CixsJaxwsService service,
            final Map < String, Object > parameters,
            final File serviceAntFilesDir)
    throws CodeGenMakeException {
        String fileName = "deploy.xml";
        generateFile(JAXWS_GENERATOR_NAME,
                SERVICE_ANT_DEPLOY_VLC_TEMPLATE,
                SERVICE_MODEL_NAME,
                service,
                parameters,
                serviceAntFilesDir,
                fileName,
                "UTF-8");
        return fileName;
    }

    /**
     * Create the Jaxws Web Xml file.
     * @param service the Jaxws service description
     * @param parameters miscellaneous help parameters
     * @param serviceWebFilesDir where to store the generated file
     * @return the generated file name
     * @throws CodeGenMakeException if generation fails
     */
    public static String generateWebXml(
            final CixsJaxwsService service,
            final Map < String, Object > parameters,
            final File serviceWebFilesDir)
    throws CodeGenMakeException {
        String fileName = "web.xml";
        generateFile(CIXS_TO_JAXWS_GENERATOR_NAME,
                SERVICE_WEB_XML_VLC_TEMPLATE,
                SERVICE_MODEL_NAME,
                service,
                parameters,
                serviceWebFilesDir,
                fileName,
                "UTF-8");
        return fileName;
    }

    /**
     * Create a COBOl CICS Client program to use for testing.
     * @param service the Jaxws service description
     * @param operation the operation for which a program is to be generated
     * @param parameters the set of parameters to pass to template engine
     * @param cobolFilesDir location where COBOL code should be generated
     * @param transportType wire protocol to use between cobol client and proxy
     * @param cobolHttpClientType the type of COBOL http client sample to generate
     * @throws CodeGenMakeException if generation fails
     */
    public static void generateCobolCicsClient(
            final AbstractCixsService service,
            final CixsOperation operation,
            final Map < String, Object > parameters,
            final File cobolFilesDir,
            final TransportType transportType,
            final CobolHttpClientType cobolHttpClientType)
    throws CodeGenMakeException {

        String template;
        switch(transportType) {
        case HTTP:
            switch(cobolHttpClientType) {
            case DFHWBCLI:
                template = OPERATION_COBOL_CICS_DFHWBCLI_CLIENT_VLC_TEMPLATE;
                break;
            case WEBAPI:
                template = OPERATION_COBOL_CICS_WEBAPI_CLIENT_VLC_TEMPLATE;
                break;
            default:
                template = OPERATION_COBOL_CICS_LSHTTAPI_CLIENT_VLC_TEMPLATE;
            }
            break;
        case WMQ:
            template = OPERATION_COBOL_CICS_WMQ_CLIENT_VLC_TEMPLATE;
            break;
        default:
            throw new CodeGenMakeException("Transport " + transportType + " not supported");    
        }

        generateFile(CIXS_TO_JAXWS_GENERATOR_NAME,
                template,
                SERVICE_MODEL_NAME,
                service,
                parameters,
                cobolFilesDir,
                operation.getCicsProgramName() + ".cbl");

    }

    /**
     * @return the directory where COBOL files will be created
     */
    public File getTargetCobolDir() {
        return getAntModel().getTargetCobolDir();
    }

    /**
     * @param targetCobolDir the directory where COBOL files will be created
     *  to set
     */
    public void setTargetCobolDir(final File targetCobolDir) {
        getAntModel().setTargetCobolDir(targetCobolDir);
    }

    /**
     * {@inheritDoc}
     * @see com.legstar.cixs.gen.ant.AbstractCixsGenerator#getAntModel()
     */
    public AntBuildCixs2JaxwsModel getAntModel() {
        return (AntBuildCixs2JaxwsModel) super.getAntModel();
    }

    /**
     * Convenience method to get the inner mapped operations.
     * @return the operations list
     */
    public List < CixsOperation > getCixsOperations() {
        return getCixsJaxwsService().getCixsOperations();
    }

    /** {@inheritDoc}*/
    public String getGeneratorName() {
        return CIXS_TO_JAXWS_GENERATOR_NAME;
    }

    /**
     * When ant 1.7.0 will become widespread, we will be able to expose
     * this method directly (support for enum JDK 1.5).
     * @return the Http Cobol Client Type in use.
     */
    protected CobolHttpClientType getSampleCobolHttpClientTypeInternal() {
        return getAntModel().getSampleCobolHttpClientType();
    }

    /**
     * @return the Http Cobol Client Type in use.
     */
    public String getSampleCobolHttpClientType() {
        return getSampleCobolHttpClientTypeInternal().toString();
    }

    /**
     * When ant 1.7.0 will become widespread, we will be able to expose
     * this method directly (support for enum JDK 1.5).
     * @param sampleCobolHttpClientType the Http Cobol Client Type in use.
     */
    private void setSampleCobolHttpClientTypeInternal(
            final CobolHttpClientType sampleCobolHttpClientType) {
        getAntModel().setSampleCobolHttpClientType(sampleCobolHttpClientType);
    }

    /**
     * @param sampleCobolHttpClientType the Http Cobol Client Type in use.
     */
    public void setSampleCobolHttpClientType(final String sampleCobolHttpClientType) {
        CobolHttpClientType value = CobolHttpClientType.valueOf(
                sampleCobolHttpClientType.toUpperCase(Locale.getDefault()));
        setSampleCobolHttpClientTypeInternal(value);
    }
    /**
     * @return the type of target that the generated proxy service will invoke
     */
    protected ProxyTargetType getProxyTargetTypeInternal() {
        return getAntModel().getProxyTargetType();
    }

    /**
     * @param proxyTargetType the type of target that the generated proxy service will invoke
     */
    protected void setProxyTargetTypeInternal(final ProxyTargetType proxyTargetType) {
        getAntModel().setProxyTargetType(proxyTargetType);
    }

    /**
     * @return the type of target that the generated proxy service will invoke
     */
    public String getProxyTargetType() {
        return getProxyTargetTypeInternal().toString();
    }

    /**
     * @param proxyTargetType the type of target that the generated proxy service will invoke
     */
    public void setProxyTargetType(final String proxyTargetType) {
        setProxyTargetTypeInternal(ProxyTargetType.valueOf(proxyTargetType));
    }

    /**
     * @return the set of parameters needed to invoke a POJO
     */
    public PojoParameters getPojoTargetParameters() {
        return getAntModel().getPojoTargetParameters();
    }

    /**
     * @param pojoTargetParameters the set of parameters needed to invoke a POJO to set
     */
    public void setPojoTargetParameters(
            final PojoParameters pojoTargetParameters) {
        getAntModel().setPojoTargetParameters(pojoTargetParameters);
    }

    /**
     * @param pojoTargetParameters the set of parameters needed to invoke a POJO to set
     */
    public void addPojoTargetParameters(
            final PojoParameters pojoTargetParameters) {
        getAntModel().setPojoTargetParameters(pojoTargetParameters);
    }

    /**
     * @return the set of parameters needed to invoke a Web Service
     */
    public WebServiceParameters getWebServiceTargetParameters() {
        return getAntModel().getWebServiceTargetParameters();
    }

    /**
     * @param webServiceTargetParameters the set of parameters needed to invoke a Web Service to set
     */
    public void setWebServiceTargetParameters(
            final WebServiceParameters webServiceTargetParameters) {
        getAntModel().setWebServiceTargetParameters(webServiceTargetParameters);
    }

    /**
     * @param webServiceTargetParameters the set of parameters needed to invoke a Web Service to set
     */
    public void addWebServiceTargetParameters(
            final WebServiceParameters webServiceTargetParameters) {
        getAntModel().setWebServiceTargetParameters(webServiceTargetParameters);
    }

    /**
     * @return the set of parameters needed to access the proxy over HTTP
     */
    public HttpTransportParameters getHttpTransportParameters() {
        return getAntModel().getHttpTransportParameters();
    }

    /**
     * @param httpTransportParameters the set of parameters needed to access the proxy over HTTP
     */
    public void setHttpTransportParameters(
            final HttpTransportParameters httpTransportParameters) {
        getAntModel().setHttpTransportParameters(httpTransportParameters);
    }

    /**
     * @param httpTransportParameters the set of parameters needed to access the proxy over HTTP to set
     */
    public void addHttpTransportParameters(
            final HttpTransportParameters httpTransportParameters) {
        getAntModel().setHttpTransportParameters(httpTransportParameters);
    }

    /**
     * @return a good default path that the host could use to reach
     *  the generated service proxy
     */
    public String getDefaultServicePath() {

        return DEFAULT_SERVER_PATH_TEMPLATE.replace(
                "${service.name}", getCixsJaxwsService().getName());
    }

}
