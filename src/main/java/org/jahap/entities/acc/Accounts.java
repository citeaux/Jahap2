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
import java.util.Collection;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import org.jahap.entities.base.Address;
import org.jahap.entities.res.Res;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
    @NamedQuery(name = "Accounts.findById", query = "SELECT a FROM Accounts a WHERE a.id = :id"),
    @NamedQuery(name = "Accounts.findByBalance", query = "SELECT a FROM Accounts a WHERE a.balance = :balance"),
    @NamedQuery(name = "Accounts.findByCheckout", query = "SELECT a FROM Accounts a WHERE a.checkout = :checkout"),
    @NamedQuery(name = "Accounts.findByCheckindate", query = "SELECT a FROM Accounts a WHERE a.checkindate = :checkindate"),
    @NamedQuery(name = "Accounts.findByCheckoutdate", query = "SELECT a FROM Accounts a WHERE a.checkoutdate = :checkoutdate")})
public class Accounts implements Serializable, accounts_ie {
    @Column(name = "CHECKOUT")
    private boolean checkout;
    @Column(name = "CHECKIN")
    private boolean checkin;
    
    @OneToMany(mappedBy = "account")
    private Collection<Csc> cscCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "BALANCE")
    private double balance;
   
    @Column(name = "CHECKINDATE")
   @Temporal(TemporalType.DATE)
    private Date checkindate;
   
    @Column(name = "CHECKOUTDATE")
    @Temporal(TemporalType.DATE)
    private Date checkoutdate;
    @JoinColumn(name = "RESERVATION",referencedColumnName = "ID")
    @ManyToOne
    private Res reservation;
    
    @Size(max = 50)
    @Column(name = "STATE")
    private String state;
    
    @OneToMany(mappedBy = "account", targetEntity=AccountPosition.class)
    private Collection<AccountPosition> accountPositionCollection;
    @JoinColumn(name="ADDRESS", referencedColumnName = "ID")
    @ManyToOne
    private Address address; 
    
    
    @Override
    public Collection<AccountPosition> getAccountPositionCollection() {
        return accountPositionCollection;
    }
   
    @Override
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        this.accountPositionCollection = accountPositionCollection;
    }

    public boolean isCheckin() {
        return checkin;
    }

    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }

    public Address getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Accounts() {
    }

    public Accounts(Long id) {
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
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public Date getCheckindate() {
        return checkindate;
    }

    @Override
    public void setCheckindate(Date checkindate) {
        this.checkindate = checkindate;
    }

    @Override
    public Date getCheckoutdate() {
        return checkoutdate;
    }

    @Override
    public void setCheckoutdate(Date checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    @XmlTransient
    @Override
    public Res getReservation() {
        return reservation;
    }

    @Override
    public void setReservation(Res reservation) {
        this.reservation = reservation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Accounts[ id=" + id + " ]";
    }

    public boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

 
    @XmlTransient
    public Collection<Csc> getCscCollection() {
        return cscCollection;
    }

    public void setCscCollection(Collection<Csc> cscCollection) {
        this.cscCollection = cscCollection;
    }

    @Override
    public boolean getCheckin() {
         return this.checkin;        
    }
    
}
