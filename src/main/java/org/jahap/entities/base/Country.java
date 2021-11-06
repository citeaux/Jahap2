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

package org.jahap.entities.base;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
@Entity
@Table(name = "COUNTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findById", query = "SELECT c FROM Country c WHERE c.id = :id"),
    @NamedQuery(name = "Country.findByCountryCode", query = "SELECT c FROM Country c WHERE c.countryCode = :countryCode"),
    @NamedQuery(name = "Country.findByCountryName", query = "SELECT c FROM Country c WHERE c.countryName = :countryName"),
    @NamedQuery(name = "Country.findByCountryCurrency", query = "SELECT c FROM Country c WHERE c.countryCurrency = :countryCurrency")})
public class Country implements Serializable, Country_ie {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @Size(max = 10)
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    @Size(max = 100)
    @Column(name = "COUNTRY_NAME")
    private String countryName;
    @JoinColumn(name = "CURRENCY", referencedColumnName = "ID")
    @ManyToOne
    private Currency countryCurrency;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne
    private Language contryLanguage;
    
    public Country() {
    }

    public Country(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @NotNull
    @Override
    public String getCountryCode() {
        return countryCode;
    }
    
  
    @Override
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    @NotNull
    @Override
    public String getCountryName() {
        return countryName;
    }


    @Override
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    @NotNull
    public Currency getCountryCurrency() {
        return countryCurrency;
    }
   

    public void setCountryCurrency(Currency countryCurrency) {
        this.countryCurrency = countryCurrency;
    }
    
    @NotNull
    public Language getContryLanguage() {
        return contryLanguage;
    }

    
    public void setContryLanguage(Language contryLanguage) {
        this.contryLanguage = contryLanguage;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jahap.entities.Country[ id=" + id + " ]";
    }
    
}
