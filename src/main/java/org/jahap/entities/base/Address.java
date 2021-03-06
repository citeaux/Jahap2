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
package org.jahap.entities.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import org.jahap.entities.acc.Bill;
import org.jahap.entities.res.Res;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author russ
 */
@Entity
@Table(name = "ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
    @NamedQuery(name = "Address.findByChristianname", query = "SELECT a FROM Address a WHERE a.christianname = :christianname"),
    @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
    @NamedQuery(name = "Address.findByEmail", query = "SELECT a FROM Address a WHERE a.email = :email"),
    @NamedQuery(name = "Address.findByName", query = "SELECT a FROM Address a WHERE a.name = :name"),
    @NamedQuery(name = "Address.findByPhone", query = "SELECT a FROM Address a WHERE a.phone = :phone"),
    @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street"),
    @NamedQuery(name = "Address.findByZipcode", query = "SELECT a FROM Address a WHERE a.zipcode = :zipcode")})
public class Address implements Serializable, address_ie {
    @OneToMany(mappedBy = "address")
    private Collection<Bill> billCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "CHRISTIANNAME")
    private String christianname;
    @Size(max = 255)
    @Column(name = "CITY")
    private String city;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "PHONE")
    private String phone;
    @Size(max = 255)
    @Column(name = "STREET")
    private String street;
    @Size(max = 255)
    @Column(name = "ZIPCODE")
    private String zipcode;
   // @OneToOne(cascade = CascadeType.ALL, mappedBy = "address1")
   // private Bill bill;
    @OneToMany(mappedBy = "addressid")
    private Collection<Res> resCollection;
    
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne
    private Language language;
    
    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID")
    @ManyToOne
    private Country country;
    @JoinColumn(name = "CURRENCY", referencedColumnName = "ID")
    @ManyToOne
    private Currency currency;
    @Column(name = "TITLE")
    @Size(max = 50)
    private String Titel;
    @Column(name = "HOMEPAGE")
    @Size(max = 100)
    private String Homepage;
    @Column(name = "ADDRESSTYPE")
    @Size(max = 100)
    private String Addresstype;
    @Column(name = "REMARKS")
    @Size(max = 200)
    private String Remarks;
    @Column(name ="GREETING")
    @Size(max = 50)
    private String Greeting;
    @Column(name = "SALUTATION")
    @Size(max = 50)
    private String Salutation;
    
    public Address() {
    }

    public Address(Long id) {
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
    public String getChristianname() {
        return christianname;
    }

    @Override
    public void setChristianname(String christianname) {
        this.christianname = christianname;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
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
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getZipcode() {
        return zipcode;
    }

    @Override
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

   

   
/*
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
*/
    @XmlTransient
    @Override
    public Collection<Res> getResCollection() {
        return resCollection;
    }

    @Override
    public void setResCollection(Collection<Res> resCollection) {
        this.resCollection = resCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
          
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "org.arnea.jahap.standalone.entities.Address[ id=" + id + " ]";
    }

    @XmlTransient
    @Override
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    @Override
    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    @Override
    public String getTitel() {
        return this.Titel;
    }

    @Override
    public String getHomepage() {
        return this.Homepage;
    }

    @Override
    public String getAddresstype() {
        return this.Addresstype;
    }

    @Override
    public String getRemarks() {
        return this.Remarks;
        
        
    }

    @Override
    public String getGreeting() {
        return this.Greeting;
    }

    @Override
    public String getSalutation() {
       return this.Salutation;
    }

    @Override
    public void setTitel(String title) {
        this.Titel=title;
    }

    @Override
    public void setHomepage(String homepage) {
       this.Homepage=homepage;
        
        
    }

    @Override
    public void setAddresstype(String addresstype) {
   this.Addresstype=addresstype; 
           }

    @Override
    public void setRemarks(String remarks) {
   this.Remarks=remarks;
    }

    @Override
    public void setGreeting(String greeting) {
   this.Greeting=greeting;
    }

    @Override
    public void setSalutation(String salutation) {
     this.Salutation=salutation;
    }
    
}
