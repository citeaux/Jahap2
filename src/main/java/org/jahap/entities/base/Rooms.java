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




package org.jahap.entities.base;

import org.jahap.entities.res.Occ;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "ROOMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findById", query = "SELECT r FROM Rooms r WHERE r.id = :id"),
    @NamedQuery(name = "Rooms.findByCategory", query = "SELECT r FROM Rooms r WHERE r.category = :category"),
    @NamedQuery(name = "Rooms.findByCode", query = "SELECT r FROM Rooms r WHERE r.code = :code"),
    @NamedQuery(name = "Rooms.findByName", query = "SELECT r FROM Rooms r WHERE r.name = :name")})
public class Rooms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "CAT", referencedColumnName = "ID")
    @ManyToOne
    private Cat category;
    @Size(max = 255)
    @Column(name = "CODE")
    private String code;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "room",targetEntity=Occ.class)
    private Collection<Occ> occCollection;
    @JoinColumn(name = "LOCATION", referencedColumnName = "ID")
    @ManyToOne
    private Location location;
    @Column(name="CLEAN")
    private boolean clean;
    @Column(name="NO_MAINTENANCE")
    private boolean no_maintenance;
    @Column(name="PAX")
    private byte pax;
    
    public Rooms() {
    }

    public Rooms(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cat getCategory() {
        return category;
    }

    public void setCategory(Cat category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public byte getPax() {
        return pax;
    }

    public void setPax(byte pax) {
        this.pax = pax;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public boolean isNo_maintenance() {
        return no_maintenance;
    }

    public void setNo_maintenance(boolean no_maintenance) {
        this.no_maintenance = no_maintenance;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Occ> getOccCollection() {
        return occCollection;
    }

    public void setOccCollection(Collection<Occ> occCollection) {
        this.occCollection = occCollection;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Rooms[ id=" + id + " ]";
    }
    
}
