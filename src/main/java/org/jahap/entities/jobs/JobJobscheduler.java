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
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "job_jobscheduler")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "JobJobscheduler.findAll", query = "SELECT j FROM JobJobscheduler j"),
	@NamedQuery(name = "JobJobscheduler.findById", query = "SELECT j FROM JobJobscheduler j WHERE j.id = :id"),
	@NamedQuery(name = "JobJobscheduler.findByPosition", query = "SELECT j FROM JobJobscheduler j WHERE j.position = :position")})
public class JobJobscheduler implements Serializable, JobJobscheduler_ie {
	private static final long serialVersionUID = 1L;
	@Id
        @Basic(optional = false)
        @GeneratedValue
        @Column(name = "id")
	private Long id;
	@Column(name = "position")
	private Integer position;
	@JoinColumn(name = "id_job", referencedColumnName = "id")
        @ManyToOne(optional = false)
	private Jobs idJob;
	@JoinColumn(name = "id_jobscheduler", referencedColumnName = "id")
        @ManyToOne(optional = false)
	private Jobscheduler idJobscheduler;

	public JobJobscheduler() {
	}

	public JobJobscheduler(Long id) {
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
	public Integer getPosition() {
		return position;
	}

	@Override
	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public Jobs getIdJob() {
		return idJob;
	}

	@Override
	public void setIdJob(Jobs idJob) {
		this.idJob = idJob;
	}

	@Override
	public Jobscheduler getIdJobscheduler() {
		return idJobscheduler;
	}

	@Override
	public void setIdJobscheduler(Jobscheduler idJobscheduler) {
		this.idJobscheduler = idJobscheduler;
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
		if (!(object instanceof JobJobscheduler)) {
			return false;
		}
		JobJobscheduler other = (JobJobscheduler) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.jahap.entities.jobs.JobJobscheduler[ id=" + id + " ]";
	}
	
}
