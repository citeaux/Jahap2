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
package org.jahap.entities.jobs;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "jobs")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Jobs j"),
	@NamedQuery(name = "Jobs.findById", query = "SELECT j FROM Jobs j WHERE j.id = :id"),
	@NamedQuery(name = "Jobs.findByType", query = "SELECT j FROM Jobs j WHERE j.type = :type"),
	@NamedQuery(name = "Jobs.findByName", query = "SELECT j FROM Jobs j WHERE j.name = :name"),
	@NamedQuery(name = "Jobs.findByDefinition", query = "SELECT j FROM Jobs j WHERE j.definition = :definition")})
public class Jobs implements Serializable, Jobs_ie {
	private static final long serialVersionUID = 1L;
	@Id
        @Basic(optional = false)
        @GeneratedValue
        @Column(name = "id")
	private Long id;
	@Size(max = 50)
        @Column(name = "type")
	private String type;
	@Size(max = 100)
        @Column(name = "name")
	private String name;
	@Size(max = 2147483647)
        @Column(name = "definition")
	private String definition;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idJob")
	private Collection<JobJobscheduler> jobJobschedulerCollection;

	public Jobs() {
	}

	public Jobs(Long id) {
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
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
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
	public String getDefinition() {
		return definition;
	}

	@Override
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@XmlTransient
	@Override
	public Collection<JobJobscheduler> getJobJobschedulerCollection() {
		return jobJobschedulerCollection;
	}

	@Override
	public void setJobJobschedulerCollection(Collection<JobJobscheduler> jobJobschedulerCollection) {
		this.jobJobschedulerCollection = jobJobschedulerCollection;
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
		if (!(object instanceof Jobs)) {
			return false;
		}
		Jobs other = (Jobs) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.jahap.entities.jobs.Jobs[ id=" + id + " ]";
	}
	
}
