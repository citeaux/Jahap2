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

import org.jahap.entities.acc.AccountPosition;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "REVENUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revenue.findAll", query = "SELECT r FROM Revenue r"),
    @NamedQuery(name = "Revenue.findById", query = "SELECT r FROM Revenue r WHERE r.id = :id"),
    @NamedQuery(name = "Revenue.findByDebit", query = "SELECT r FROM Revenue r WHERE r.debit = :debit")})
public class Revenue implements   revenue_ie{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "DEBIT")
    private boolean debit;
    @JoinColumn(name = "ACCOUNTPOSITION", referencedColumnName = "ID")
    @ManyToOne
    private AccountPosition accountposition;
    @JoinColumn(name = "REVACCOUNT", referencedColumnName = "ID")
    @ManyToOne
    private Revaccounts revaccount;
    @Column(name="REVDATE")
    @Temporal(TemporalType.DATE)
    private Date revdate;

    
    @Column(name="AMOUNT")
    private double amount;
    
    @Override
    public Revaccounts getRevaccount() {
        return revaccount;
    }

    @Override
    public void setRevaccount(Revaccounts revaccount) {
        this.revaccount = revaccount;
    }
    
    public Revenue() {
    }

    public Revenue(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean getDebit() {
        return debit;
    }
     
    @Override
    public double getAmount() {
        return amount;
    }
    
    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    @Override
    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    @Override
    public AccountPosition getAccountposition() {
        return accountposition;
    }

    @Override
    public void setAccountposition(AccountPosition accountposition) {
        this.accountposition = accountposition;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    public Date getRevdate() {
        return revdate;
    }

    public void setRevdate(Date revdate) {
        this.revdate = revdate;
    }

    
    
    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Revenue)) {
            return false;
        }
        Revenue other = (Revenue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Revenue[ id=" + id + " ]";
    }
    
}
