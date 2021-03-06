
package com.legstar.test.coxb.tcobwvb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;


/**
 * <p>Java class for Transactions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Transactions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransactionNbr">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
 *               &lt;totalDigits value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Transaction" type="{http://legstar.com/test/coxb/tcobwvb}Transaction" maxOccurs="5" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transactions", propOrder = {
    "transactionNbr",
    "transaction"
})
public class Transactions
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "TransactionNbr")
    @CobolElement(cobolName = "TRANSACTION-NBR", type = CobolType.BINARY_ITEM, levelNumber = 10, isSigned = false, totalDigits = 9, isODOObject = true, picture = "9(9)", usage = "BINARY", srceLine = 36)
    protected long transactionNbr;
    @XmlElement(name = "Transaction")
    @CobolElement(cobolName = "TRANSACTION", type = CobolType.GROUP_ITEM, levelNumber = 10, minOccurs = 0, maxOccurs = 5, dependingOn = "TRANSACTION-NBR", srceLine = 37)
    protected List<Transaction> transaction;

    /**
     * Gets the value of the transactionNbr property.
     * 
     */
    public long getTransactionNbr() {
        return transactionNbr;
    }

    /**
     * Sets the value of the transactionNbr property.
     * 
     */
    public void setTransactionNbr(long value) {
        this.transactionNbr = value;
    }

    public boolean isSetTransactionNbr() {
        return true;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transaction }
     * 
     * 
     */
    public List<Transaction> getTransaction() {
        if (transaction == null) {
            transaction = new ArrayList<Transaction>();
        }
        return this.transaction;
    }

    public boolean isSetTransaction() {
        return ((this.transaction!= null)&&(!this.transaction.isEmpty()));
    }

    public void unsetTransaction() {
        this.transaction = null;
    }

}
