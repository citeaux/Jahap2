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
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "PAYMENTTYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymenttypes.findAll", query = "SELECT p FROM Paymenttypes p"),
    @NamedQuery(name = "Paymenttypes.findById", query = "SELECT p FROM Paymenttypes p WHERE p.id = :id"),
    @NamedQuery(name = "Paymenttypes.findByName", query = "SELECT p FROM Paymenttypes p WHERE p.name = :name"),
   })
public class Paymenttypes implements Serializable,  Paymenttypes_ie {
    @Column(name = "RECEIVABLE")
    private Boolean receivable;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    
    @OneToMany(mappedBy = "paymenttype")
    private Collection<Payed> payedCollection;

    public Paymenttypes() {
    }

    public Paymenttypes(Long id) {
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    

    
  

    @XmlTransient
    @Override
    public Collection<Payed> getPayedCollection() {
        return payedCollection;
    }

    @Override
    public void setPayedCollection(Collection<Payed> payedCollection) {
        this.payedCollection = payedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Paymenttypes)) {
            return false;
        }
        Paymenttypes other = (Paymenttypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Paymenttypes[ id=" + id + " ]";
    }

    @Override
    public Boolean getReceivable() {
        return receivable;
    }

    @Override
    public void setReceivable(Boolean receivable) {
        this.receivable = receivable;
    }
    
}
