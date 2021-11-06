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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "REVACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revaccounts.findAll", query = "SELECT r FROM Revaccounts r"),
    @NamedQuery(name = "Revaccounts.findById", query = "SELECT r FROM Revaccounts r WHERE r.id = :id"),
    @NamedQuery(name = "Revaccounts.findByRevaccnumber", query = "SELECT r FROM Revaccounts r WHERE r.revaccnumber = :revaccnumber"),
    @NamedQuery(name = "Revaccounts.findByName", query = "SELECT r FROM Revaccounts r WHERE r.name = :name")})
public class Revaccounts implements Serializable, revaccounts_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "REVACCOUNTNUMBER")
    private long revaccnumber;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "revaccount")
    private Collection<Revenue> revenueCollection;
    @Column(name="REV_GROUP")
    private String rev_group;
    
    
    @Override
    public Collection<Revenue> getRevenueCollection() {
        return revenueCollection;
    }

    @Override
    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
        this.revenueCollection = revenueCollection;
    }
    
    
    public Revaccounts() {
    }

    public Revaccounts(Long id) {
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
    public long getRevaccnumber() {
        return revaccnumber;
    }

    @Override
    public void setRevaccnumber(long revaccnumber) {
        this.revaccnumber = revaccnumber;
    }
    
    
    @Override
    public String getName() {
        return name;
    }

    public String getRev_group() {
        return rev_group;
    }

    public void setRev_group(String rev_group) {
        this.rev_group = rev_group;
    }

    
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Revaccounts)) {
            return false;
        }
        Revaccounts other = (Revaccounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Revaccounts[ id=" + id + " ]";
    }
    
}
