/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "maintenanceblock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maintenanceblock.findAll", query = "SELECT m FROM Maintenanceblock m"),
    @NamedQuery(name = "Maintenanceblock.findById", query = "SELECT m FROM Maintenanceblock m WHERE m.id = :id"),
    @NamedQuery(name = "Maintenanceblock.findByName", query = "SELECT m FROM Maintenanceblock m WHERE m.name = :name"),
    @NamedQuery(name = "Maintenanceblock.findByComment", query = "SELECT m FROM Maintenanceblock m WHERE m.comment = :comment")})
public class Maintenanceblock implements Serializable, Maintenanceblock_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "comment")
    private String comment;

    public Maintenanceblock() {
    }

    public Maintenanceblock(Long id) {
        this.id = id;
    }

	@Override
    public long getId() {
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

	@Override
    public String getComment() {
        return comment;
    }

	@Override
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
          
        if (!(object instanceof Maintenanceblock)) {
            return false;
        }
        Maintenanceblock other = (Maintenanceblock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.res.Maintenanceblock[ id=" + id + " ]";
    }
    
}
