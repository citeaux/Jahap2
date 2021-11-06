/*
 * The MIT License
 *
 * Copyright 2017 Open Jahap Community.
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
package org.jahap.entities;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.apache.commons.io.IOUtils;

import org.jahap.entities.base.Language;

/**
 *
 * @author demokrite
 */
@Entity
@Table(name = "reports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reports.findAll", query = "SELECT r FROM Reports r")
    , @NamedQuery(name = "Reports.findById", query = "SELECT r FROM Reports r WHERE r.id = :id")
    , @NamedQuery(name = "Reports.findByName", query = "SELECT r FROM Reports r WHERE r.name = :name")
    , @NamedQuery(name = "Reports.findByDescription", query = "SELECT r FROM Reports r WHERE r.description = :description")
    , @NamedQuery(name = "Reports.findByReportGroup", query = "SELECT r FROM Reports r WHERE r.reportGroup = :reportGroup")
    , @NamedQuery(name = "Reports.findByLanguage", query = "SELECT r FROM Reports r WHERE r.language = :language")
    , @NamedQuery(name = "Reports.findByReportLayout", query = "SELECT r FROM Reports r WHERE r.reportLayout = :reportLayout")})
public class Reports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "report_group")
    private String reportGroup;
    @Lob
    @Column(name = "report")
    private byte[] report;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne
    private Language language;
    @Column(name = "report_layout")
    private String reportLayout;
    @Column(name = "standardreport")
    private Boolean standardreport;

    public Reports() {
    }
    
    
    public Reports(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportGroup() {
        return reportGroup;
    }

    public void setReportGroup(String reportGroup) {
        this.reportGroup = reportGroup;
    }

    public byte[] getReport() {
        return report;
    }

    public void setReport(byte[] report) {
        this.report = report;
    }

    public Language getLanguage() {
        return language;
    }
    
    public void setReportFile(File file)throws IOException {
        InputStream is = new FileInputStream(file); 
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
          int offset = 0;
          int numRead = 0;
          while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
          {
            offset += numRead;
          }
        if (offset < bytes.length)
          {
            throw new IOException("Could not completely read file " + file.getName());
          }
          // Close the input stream and return bytes
          is.close();  
          setReport(bytes);
    }
    
     public void setReportStream(byte[] bytes) throws IOException {
         
       
         
         
          setReport(bytes);
    }
    

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getReportLayout() {
        return reportLayout;
    }

    public void setReportLayout(String reportLayout) {
        this.reportLayout = reportLayout;
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
        if (!(object instanceof Reports)) {
            return false;
        }
        Reports other = (Reports) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Reports[ id=" + id + " ]";
    }

    public Boolean getStandardreport() {
        return standardreport;
    }

    public void setStandardreport(Boolean standardreport) {
        this.standardreport = standardreport;
    }
    
    
    
}
