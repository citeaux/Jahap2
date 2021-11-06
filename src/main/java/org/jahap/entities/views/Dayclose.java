/*
 * The MIT License
 *
 * Copyright 2015 Open Jahap Community.
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
 *
 * @author russ
 */
@Entity
@Table(name = "dayclose")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Dayclose.findAll", query = "SELECT d FROM Dayclose d"),
	@NamedQuery(name = "Dayclose.findByType", query = "SELECT d FROM Dayclose d WHERE d.type = :type"),
	@NamedQuery(name = "Dayclose.findByName", query = "SELECT d FROM Dayclose d WHERE d.name = :name"),
	@NamedQuery(name = "Dayclose.findByPosition", query = "SELECT d FROM Dayclose d WHERE d.position = :position"),
	@NamedQuery(name = "Dayclose.findByIdJobscheduler", query = "SELECT d FROM Dayclose d WHERE d.idJobscheduler = :idJobscheduler"),
	@NamedQuery(name = "Dayclose.findByIdJob", query = "SELECT d FROM Dayclose d WHERE d.idJob = :idJob"),
	@NamedQuery(name = "Dayclose.findById", query = "SELECT d FROM Dayclose d WHERE d.id = :id"),
	@NamedQuery(name = "Dayclose.findByDefinition", query = "SELECT d FROM Dayclose d WHERE d.definition = :definition")})
public class Dayclose implements Serializable {
	private static final long serialVersionUID = 1L;
	@Size(max = 50)
        @Column(name = "type")
	private String type;
	@Size(max = 100)
        @Column(name = "name")
	private String name;
	@Column(name = "position")
	private Integer position;
	@Column(name = "id_jobscheduler")
	private long idJobscheduler;
	@Column(name = "id_job")
	private long idJob;
	@Column(name = "id")
	@Id
	private long id;
	@Size(max = 2147483647)
        @Column(name = "definition")
	private String definition;

	public Dayclose() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public long getIdJobscheduler() {
		return idJobscheduler;
	}

	public void setIdJobscheduler(long idJobscheduler) {
		this.idJobscheduler = idJobscheduler;
	}

	public long getIdJob() {
		return idJob;
	}

	public void setIdJob(long idJob) {
		this.idJob = idJob;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
}
