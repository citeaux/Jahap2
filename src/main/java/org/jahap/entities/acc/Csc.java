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

import org.jahap.entities.base.Rates;
import org.jahap.entities.acc.Accounts;
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
@Table(name = "CSC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Csc.findAll", query = "SELECT c FROM Csc c"),
    @NamedQuery(name = "Csc.findById", query = "SELECT c FROM Csc c WHERE c.id = :id"),
    @NamedQuery(name = "Csc.findByFromdate", query = "SELECT c FROM Csc c WHERE c.fromdate = :fromdate"),
    @NamedQuery(name = "Csc.findByTodate", query = "SELECT c FROM Csc c WHERE c.todate = :todate")})
public class Csc implements Serializable, csc_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "FROMDATE")
    @Temporal(TemporalType.DATE)
    private Date fromdate;
    @Column(name = "TODATE")
    @Temporal(TemporalType.DATE)
    private Date todate;
    @JoinColumn(name = "RATE", referencedColumnName = "ID")
    @ManyToOne
    private Rates rate;
    @JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    @ManyToOne
    private Accounts account;
    @Column(name = "SERVICE")
    private String service;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "AMOUNT")
    private int amount;
    
    
    public Csc() {
    }

    public Csc(Long id) {
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
    public Date getFromdate() {
        return fromdate;
    }

    @Override
    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    @Override
    public Date getTodate() {
        return todate;
    }

    @Override
    public void setTodate(Date todate) {
        this.todate = todate;
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
    public double getPrice() {
        return price;
    }
        @Override
    public void setPrice(double price) {
        this.price = price;
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
    public String getService() {
        return service;
    }
    @Override
    public void setService(String service) {
        this.service = service;
    }

    
    
    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Csc)) {
            return false;
        }
        Csc other = (Csc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Csc[ id=" + id + " ]";
    }
    
}
