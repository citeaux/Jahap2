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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * The housekeeping entity represents a view which is used in the housekeeping Dialog.
 * It includes blocks and the state of the room
 * a Block is a kind of a reservation and can include several days. The block is configured in
 * an separate table.
 * The clean state means the room ist just
 * clean or dirty - it is a state of the room an therefore there located
 * @author russ
 */
@Entity
@Table(name = "housekeeping")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Housekeeping.findAll", query = "SELECT h FROM Housekeeping h"),
	@NamedQuery(name = "Housekeeping.findById", query = "SELECT h FROM Housekeeping h WHERE h.id = :id"),
	@NamedQuery(name = "Housekeeping.findByCode", query = "SELECT h FROM Housekeeping h WHERE h.code = :code"),
	@NamedQuery(name = "Housekeeping.findByCatName", query = "SELECT h FROM Housekeeping h WHERE h.catName = :catName"),
	@NamedQuery(name = "Housekeeping.findByFloor", query = "SELECT h FROM Housekeeping h WHERE h.floor = :floor"),
	@NamedQuery(name = "Housekeeping.findByClean", query = "SELECT h FROM Housekeeping h WHERE h.clean = :clean"),
	@NamedQuery(name = "Housekeeping.findByBlocks", query = "SELECT h FROM Housekeeping h WHERE h.blocks = :blocks")})
public class Housekeeping implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "id")
	@Id
	private long id;
	@Size(max = 255)
        @Column(name = "code")
	private String code;
	@Size(max = 100)
        @Column(name = "cat_name")
	private String catName;
	@Size(max = 100)
        @Column(name = "floor")
	private String floor;
	@Column(name = "clean")
	private Boolean clean;
	@Size(max = 2147483647)
        @Column(name = "blocks")
	private String blocks;

	public Housekeeping() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Boolean getClean() {
		return clean;
	}

	public void setClean(Boolean clean) {
		this.clean = clean;
	}

	public String getBlocks() {
		return blocks;
	}

	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}
	
}
