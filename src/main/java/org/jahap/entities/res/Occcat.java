/*
 * The MIT License
 *
 * Copyright 2016 Open Jahap Community.
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
import org.jahap.entities.base.Cat;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "occcat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Occcat.findAll", query = "SELECT o FROM Occcat o"),
    @NamedQuery(name = "Occcat.findById", query = "SELECT o FROM Occcat o WHERE o.id = :id"),
    @NamedQuery(name = "Occcat.findByArrivaldate", query = "SELECT o FROM Occcat o WHERE o.arrivaldate = :arrivaldate"),
    @NamedQuery(name = "Occcat.findByDeparturedate", query = "SELECT o FROM Occcat o WHERE o.departuredate = :departuredate"),
    @NamedQuery(name = "Occcat.findByCat", query = "SELECT o FROM Occcat o WHERE o.cat = :cat"),
    @NamedQuery(name = "Occcat.findByOcc", query = "SELECT o FROM Occcat o WHERE o.occ = :occ"),
    @NamedQuery(name = "Occcat.findByRes", query = "SELECT o FROM Occcat o WHERE o.res = :res")})
public class Occcat implements Serializable, Occcat_ie {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "arrivaldate")
    @Temporal(TemporalType.DATE)
    private Date arrivaldate;
    @Column(name = "departuredate")
    @Temporal(TemporalType.DATE)
    private Date departuredate;
    @JoinColumn(name="CAT", referencedColumnName = "ID")
    @ManyToOne
    private Cat cat;
    @JoinColumn(name="OCC", referencedColumnName = "ID")
    @OneToOne
    private Occ occ;
    @JoinColumn(name="RES", referencedColumnName = "ID")
    @OneToOne
    private Res res;

    public Occcat() {
    }

    public Occcat(Long id) {
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
    public Cat getCat() {
        return cat;
    }

    @Override
    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public Occ getOcc() {
        return occ;
    }

    @Override
    public void setOcc(Occ occ) {
        this.occ = occ;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Occcat)) {
            return false;
        }
        Occcat other = (Occcat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.res.Occcat[ id=" + id + " ]";
    }
    
}
