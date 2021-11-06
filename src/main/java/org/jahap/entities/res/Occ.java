/*
 * The MIT License
 *
 * Copyright 2017 Open Jahap Community.
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
package org.jahap.entities.res;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Rooms;

/**
 *
 * @author demokrite
 */
@Entity
@Table(name = "occ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Occ.findAll", query = "SELECT o FROM Occ o")
    , @NamedQuery(name = "Occ.findById", query = "SELECT o FROM Occ o WHERE o.id = :id")
    , @NamedQuery(name = "Occ.findByArrivaltime", query = "SELECT o FROM Occ o WHERE o.arrivaltime = :arrivaltime")
    , @NamedQuery(name = "Occ.findByDeparturetime", query = "SELECT o FROM Occ o WHERE o.departuretime = :departuretime")
    , @NamedQuery(name = "Occ.findByArrivaldate", query = "SELECT o FROM Occ o WHERE o.arrivaldate = :arrivaldate")
    , @NamedQuery(name = "Occ.findByDeparturedate", query = "SELECT o FROM Occ o WHERE o.departuredate = :departuredate")
  
    , @NamedQuery(name = "Occ.findByPax", query = "SELECT o FROM Occ o WHERE o.pax = :pax")
    , @NamedQuery(name = "Occ.findByCheckin", query = "SELECT o FROM Occ o WHERE o.checkin = :checkin")
    , @NamedQuery(name = "Occ.findByCheckinTimestamp", query = "SELECT o FROM Occ o WHERE o.checkinTimestamp = :checkinTimestamp")
    , @NamedQuery(name = "Occ.findByCheckoutTimestamp", query = "SELECT o FROM Occ o WHERE o.checkoutTimestamp = :checkoutTimestamp")})
public class Occ implements Serializable, occ_ie {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "arrivaltime")
    @Temporal(TemporalType.TIME)
    private Date arrivaltime;
    @Column(name = "departuretime")
    @Temporal(TemporalType.TIME)
    private Date departuretime;
    @Column(name = "arrivaldate")
    @Temporal(TemporalType.DATE)
    private Date arrivaldate;
    @Column(name = "departuredate")
    @Temporal(TemporalType.DATE)
    private Date departuredate;
    @Column(name = "pax")
    private int pax;
    @Column(name = "checkin")
    private Boolean checkin;
    @Column(name = "checkout")
    private Boolean checkout;
    @Column(name = "checkin_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkinTimestamp;
    @Column(name = "checkout_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutTimestamp;
    
    @JoinColumn(name = "ROOM", referencedColumnName = "ID")
    @ManyToOne
    private Rooms room;
    @JoinColumn(name = "RES", referencedColumnName = "ID")
    @ManyToOne
    private Res res;
    @JoinColumn(name="GUEST", referencedColumnName = "ID")
    @ManyToOne
    private Address guest;
    @JoinColumn(name="ACCOUNT", referencedColumnName = "ID")
    @ManyToOne
    private Accounts account;
    @JoinColumn(name="HOUSEKEEPING", referencedColumnName = "ID")
    @OneToOne
    private Housekeepingblock housekeepingblock;
    @JoinColumn(name="MAINTENANCE", referencedColumnName = "ID")
    @OneToOne
    private Maintenanceblock maintenanceblock;

    public Occ() {
    }

    public Occ(Long id) {
        this.id = id;
    }

    @Override
    public Boolean getCheckout() {
        return checkout;
    }

    @Override
    public void setCheckout(Boolean checkout) {
        this.checkout = checkout;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getArrivaltime() {
        return arrivaltime;
    }

    @Override
    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    @Override
    public Date getDeparturetime() {
        return departuretime;
    }

    @Override
    public void setDeparturetime(Date departuretime) {
        this.departuretime = departuretime;
    }

    @Override
    public Housekeepingblock getHousekeepingblock() {
        return housekeepingblock;
    }

    @Override
    public void setHousekeepingblock(Housekeepingblock housekeepingblock) {
        this.housekeepingblock = housekeepingblock;
    }

    @Override
    public Date getArrivaldate() {
        return arrivaldate;
    }

    @Override
    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    @Override
    public Date getDeparturedate() {
        return departuredate;
    }

    @Override
    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

   
    

    @Override
    public int getPax() {
        return pax;
    }

    @Override
    public void setPax(int pax) {
        this.pax = pax;
    }

    @Override
    public Boolean getCheckin() {
        return checkin;
    }

    @Override
    public void setCheckin(Boolean checkin) {
        this.checkin = checkin;
    }

    @Override
    public Date getCheckinTimestamp() {
        return checkinTimestamp;
    }

    @Override
    public void setCheckinTimestamp() {
        LocalDate today=LocalDate.now();
        Timestamp ts= Timestamp.valueOf(today.toString()); // hotel timestamp !!!!
        this.checkinTimestamp = ts;
    }

    @Override
    public Date getCheckoutTimestamp() {
        return checkoutTimestamp;
    }

    @Override
    public void setCheckoutTimestamp() {
        LocalDate today=LocalDate.now();
        Timestamp ts= Timestamp.valueOf(today.toString());
        
        this.checkoutTimestamp = ts;
    }

    @Override
    public Rooms getRoom() {
        return room;
    }

    @Override
    public void setRoom(Rooms room) {
        this.room = room;
    }

    @Override
    public Res getRes() {
        return res;
    }

    @Override
    public void setRes(Res res) {
        this.res = res;
    }

    @Override
    public Address getGuest() {
        return guest;
    }

    @Override
    public void setGuest(Address guest) {
        this.guest = guest;
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
    public Maintenanceblock getMaintenanceblock() {
        return maintenanceblock;
    }

    @Override
    public void setMaintenanceblock(Maintenanceblock maintenanceblock) {
        this.maintenanceblock = maintenanceblock;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Occ)) {
            return false;
        }
        Occ other = (Occ) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.res.Occ[ id=" + id + " ]";
    }
    
}
