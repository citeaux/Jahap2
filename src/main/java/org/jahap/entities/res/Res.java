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

package org.jahap.entities.res;

import org.jahap.entities.base.Address;
import org.jahap.entities.base.ResState;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "RES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Res.findAll", query = "SELECT r FROM Res r"),
    @NamedQuery(name = "Res.findById", query = "SELECT r FROM Res r WHERE r.id = :id"),
    @NamedQuery(name = "Res.findByArrivaltime", query = "SELECT r FROM Res r WHERE r.arrivaltime = :arrivaltime"),
    @NamedQuery(name = "Res.findByArrivaldate", query = "SELECT r FROM Res r WHERE r.arrivaldate = :arrivaldate"),
    @NamedQuery(name = "Res.findByDeparturetime", query = "SELECT r FROM Res r WHERE r.departuretime = :departuretime"),
    @NamedQuery(name = "Res.findByResno", query = "SELECT r FROM Res r WHERE r.resno = :resno"),
    @NamedQuery(name = "Res.findByDeparturedate", query = "SELECT r FROM Res r WHERE r.departuredate = :departuredate")})
public class Res implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
     @GeneratedValue
    private Long id;
    @Column(name = "ARRIVALTIME")
    @Temporal(TemporalType.TIME)
    private Date arrivaltime;
    @Column(name = "ARRIVALDATE")
    @Temporal(TemporalType.DATE)
    private Date arrivaldate;
    @Column(name = "DEPARTURETIME")
    @Temporal(TemporalType.TIME)
    private Date departuretime;
    @Size(max = 50)
    @Column(name = "RESNO")
    private String resno;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESNO_INTERN")
    private Res_No resnointern;
    @Column(name = "DEPARTUREDATE")
    @Temporal(TemporalType.DATE)
    private Date departuredate;
    @JoinColumn(name = "ADDRESSID", referencedColumnName = "ID")
    @ManyToOne
    private Address addressid;
    @JoinColumn(name="STATE",referencedColumnName="ID")
    @ManyToOne
    private ResState state;
    @Column(name="OPTIONDATE")
    @Temporal(TemporalType.DATE)
    private Date optiondate;
    @Column(name="COMMENT")
    private String comment;
    @JoinColumn(name = "OCCID", referencedColumnName = "ID")
    @ManyToOne
    private Occ occid;

    public Occ getOccid() {
        return occid;
    }

    public void setOccid(Occ occid) {
        this.occid = occid;
    }
    

    public Res() {
    }

    public Res(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public Date getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public Date getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(Date departuretime) {
        this.departuretime = departuretime;
    }

    public Res_No getResnointern() {
        return resnointern;
    }

    public void setResnointern(Res_No resnointern) {
        this.resnointern = resnointern;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public Address getAddressid() {
        return addressid;
    }

    public void setAddressid(Address addressid) {
        this.addressid = addressid;
    }

    public Date getOptiondate() {
        return optiondate;
    }

    public void setOptiondate(Date optiondate) {
        this.optiondate = optiondate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Res)) {
            return false;
        }
        Res other = (Res) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Res[ id=" + id + " ]";
    }
    
	public ResState getState() {
		return state;
	}
   
	public void setState(ResState state) {
		this.state = state;
	}

    public String getResno() {
        return resno;
    }

    public void setResno(String resno) {
        this.resno = resno;
    }
}
