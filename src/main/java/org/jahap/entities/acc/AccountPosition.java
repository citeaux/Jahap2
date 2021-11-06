/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jahap.entities.acc;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import org.jahap.entities.base.Rates;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "ACCOUNT_POSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountPosition.findAll", query = "SELECT a FROM AccountPosition a"),
    @NamedQuery(name = "AccountPosition.findById", query = "SELECT a FROM AccountPosition a WHERE a.id = :id"),
    @NamedQuery(name = "AccountPosition.findByRate", query = "SELECT a FROM AccountPosition a WHERE a.rate = :rate"),
    @NamedQuery(name = "AccountPosition.findByPositionname", query = "SELECT a FROM AccountPosition a WHERE a.positionname = :positionname"),
    @NamedQuery(name = "AccountPosition.findByAmount", query = "SELECT a FROM AccountPosition a WHERE a.amount = :amount")})
public class AccountPosition implements Serializable,  accountsposition_ie {
    @Column(name = "BILLED")
    private boolean billed;
    @Column(name = "DEBIT")
    private boolean debit;
    @Column(name = "CANCELED")
    private boolean canceled;
    @Column(name = "CANCELEDPOSITION")
    private long canceledposition;
    @Column(name = "RATEDATE")
    @Temporal(TemporalType.DATE)
    private Date ratedate;
    @OneToMany(mappedBy = "accountposition")
    private Collection<Revenue> revenueCollection;
    @JoinColumn(name = "RATE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rates rate;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "POSITIONNAME")
    private String positionname;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name="PRICE")
    private double price;
    
    @Column(name = "BILL")
    private long bill;
    @ManyToOne(optional=false)
    @JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    private Accounts account;
    @OneToMany(mappedBy = "accountposition", targetEntity = Vat.class)
    private Collection<Vat> vatCollection;
    @JoinColumn(name = "PAYMENT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Payed payed;
    

    public AccountPosition() {
    }

    public AccountPosition(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public BigInteger getArticle() {
        return null;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public Collection<Vat> getVatCollection() {
        return vatCollection;
    }

    @Override
    public void setVatCollection(Collection<Vat> vatCollection) {
        this.vatCollection = vatCollection;
    }

    public long getBill() {
        return bill;
    }

    public void setBill(long bill) {
        this.bill = bill;
    }

    

    @Override
    public String getPositionname() {
        return positionname;
    }

    @Override
    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }
   /*
    @Override
    public Bill getBill() {
        return bill;
    }

    @Override
    public void setBill(Bill bill) {
        this.bill = bill;
    }
   */
    @Override
    public Payed getPayed() {
        return payed;
    }

    @Override
    public void setPayed(Payed payed) {
        this.payed = payed;
    }

    

    

   

    @Override
    public Accounts getAccount() {
        return account;
    }

    @Override
    public void setAccount(Accounts account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof AccountPosition)) {
            return false;
        }
        AccountPosition other = (AccountPosition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.AccountPosition[ id=" + id + " ]";
    }

    @Override
    public Rates getRate() {
        return rate;
    }

    @Override
    public void setRate(Rates rate) {
        this.rate = rate;
    }

    @Override
    public boolean getBilled() {
        return billed;
    }

    @Override
    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    @Override
    public boolean getDebit() {
        return debit;
    }

    @Override
    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    @Override
    public Date getRatedate() {
        return ratedate;
    }

    @Override
    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
    }

    @XmlTransient
    @Override
    public Collection<Revenue> getRevenueCollection() {
        return revenueCollection;
    }

    @Override
    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
        this.revenueCollection = revenueCollection;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public long getCanceledposition() {
        return canceledposition;
    }

    @Override
    public void setCanceledposition(long canceledposition) {
        this.canceledposition = canceledposition;
    }
    
    
    
}
