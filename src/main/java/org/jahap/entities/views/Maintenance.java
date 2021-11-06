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
package org.jahap.entities.views;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "maintenance")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Maintenance.findAll", query = "SELECT m FROM Maintenance m"),
	@NamedQuery(name = "Maintenance.findById", query = "SELECT m FROM Maintenance m WHERE m.id = :id"),
	@NamedQuery(name = "Maintenance.findByBlocks", query = "SELECT m FROM Maintenance m WHERE m.blocks = :blocks"),
	@NamedQuery(name = "Maintenance.findByCatName", query = "SELECT m FROM Maintenance m WHERE m.catName = :catName"),
	@NamedQuery(name = "Maintenance.findByCode", query = "SELECT m FROM Maintenance m WHERE m.code = :code"),
	@NamedQuery(name = "Maintenance.findByFloor", query = "SELECT m FROM Maintenance m WHERE m.floor = :floor"),
	@NamedQuery(name = "Maintenance.findByNoMaintenance", query = "SELECT m FROM Maintenance m WHERE m.noMaintenance = :noMaintenance")})
public class Maintenance implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
        @Basic(optional = false)
        @NotNull
        @Column(name = "id")
	private Long id;
	@Size(max = 255)
        @Column(name = "blocks")
	private String blocks;
	@Size(max = 255)
        @Column(name = "cat_name")
	private String catName;
	@Size(max = 255)
        @Column(name = "code")
	private String code;
	@Size(max = 255)
        @Column(name = "floor")
	private String floor;
	@Column(name = "no_maintenance")
	private Boolean noMaintenance;

	public Maintenance() {
	}

	public Maintenance(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlocks() {
		return blocks;
	}

	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Boolean getNoMaintenance() {
		return noMaintenance;
	}

	public void setNoMaintenance(Boolean noMaintenance) {
		this.noMaintenance = noMaintenance;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		  
		if (!(object instanceof Maintenance)) {
			return false;
		}
		Maintenance other = (Maintenance) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.jahap.entities.views.Maintenance[ id=" + id + " ]";
	}
	
}
