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
package org.jahap.entities.views;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author demokrite
 */
@Entity
@Table(name = "checkin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checkin.findAll", query = "SELECT c FROM Checkin c")
    , @NamedQuery(name = "Checkin.findByResno", query = "SELECT c FROM Checkin c WHERE c.resno = :resno")
    , @NamedQuery(name = "Checkin.findByOccId", query = "SELECT c FROM Checkin c WHERE c.occId = :occId")
    , @NamedQuery(name = "Checkin.findByOccArrivaltime", query = "SELECT c FROM Checkin c WHERE c.occArrivaltime = :occArrivaltime")
    , @NamedQuery(name = "Checkin.findByOccDeparturetime", query = "SELECT c FROM Checkin c WHERE c.occDeparturetime = :occDeparturetime")
    , @NamedQuery(name = "Checkin.findByOccArrivaldate", query = "SELECT c FROM Checkin c WHERE c.occArrivaldate = :occArrivaldate")
    , @NamedQuery(name = "Checkin.findByOccDeparturedate", query = "SELECT c FROM Checkin c WHERE c.occDeparturedate = :occDeparturedate")
    , @NamedQuery(name = "Checkin.findByOccMaintenance", query = "SELECT c FROM Checkin c WHERE c.occMaintenance = :occMaintenance")
    , @NamedQuery(name = "Checkin.findByOccHousekeeping", query = "SELECT c FROM Checkin c WHERE c.occHousekeeping = :occHousekeeping")
    , @NamedQuery(name = "Checkin.findByCatName", query = "SELECT c FROM Checkin c WHERE c.catName = :catName")
    , @NamedQuery(name = "Checkin.findByCatDescription", query = "SELECT c FROM Checkin c WHERE c.catDescription = :catDescription")
    , @NamedQuery(name = "Checkin.findByCode", query = "SELECT c FROM Checkin c WHERE c.room_code = :room_code")
    , @NamedQuery(name = "Checkin.findByName", query = "SELECT c FROM Checkin c WHERE c.room_name = :room_name")
    , @NamedQuery(name = "Checkin.findByClean", query = "SELECT c FROM Checkin c WHERE c.room_clean = :room_clean")
    , @NamedQuery(name = "Checkin.findByNoMaintenance", query = "SELECT c FROM Checkin c WHERE c.room_noMaintenance = :room_noMaintenance")
    , @NamedQuery(name = "Checkin.findByLocationFloor", query = "SELECT c FROM Checkin c WHERE c.locationFloor = :locationFloor")
    , @NamedQuery(name = "Checkin.findByLocationBuilding", query = "SELECT c FROM Checkin c WHERE c.locationBuilding = :locationBuilding")
    , @NamedQuery(name = "Checkin.findByLocationChristianname", query = "SELECT c FROM Checkin c WHERE c.locationChristianname = :locationChristianname")
    , @NamedQuery(name = "Checkin.findByLocationAddressCity", query = "SELECT c FROM Checkin c WHERE c.locationAddressCity = :locationAddressCity")
    , @NamedQuery(name = "Checkin.findByLocationAddressEmail", query = "SELECT c FROM Checkin c WHERE c.locationAddressEmail = :locationAddressEmail")
    , @NamedQuery(name = "Checkin.findByLocationAddressName", query = "SELECT c FROM Checkin c WHERE c.locationAddressName = :locationAddressName")
    , @NamedQuery(name = "Checkin.findByLocationAddressPhone", query = "SELECT c FROM Checkin c WHERE c.locationAddressPhone = :locationAddressPhone")
    , @NamedQuery(name = "Checkin.findByLocationAddressStreet", query = "SELECT c FROM Checkin c WHERE c.locationAddressStreet = :locationAddressStreet")
    , @NamedQuery(name = "Checkin.findByLocationAddressZipcode", query = "SELECT c FROM Checkin c WHERE c.locationAddressZipcode = :locationAddressZipcode")
    , @NamedQuery(name = "Checkin.findByLocationAddressCountry", query = "SELECT c FROM Checkin c WHERE c.locationAddressCountry = :locationAddressCountry")
    , @NamedQuery(name = "Checkin.findByLocationAddressCurrency", query = "SELECT c FROM Checkin c WHERE c.locationAddressCurrency = :locationAddressCurrency")
    , @NamedQuery(name = "Checkin.findByLocationAddressLanguage", query = "SELECT c FROM Checkin c WHERE c.locationAddressLanguage = :locationAddressLanguage")
    , @NamedQuery(name = "Checkin.findByLocationAddressHomepage", query = "SELECT c FROM Checkin c WHERE c.locationAddressHomepage = :locationAddressHomepage")
    , @NamedQuery(name = "Checkin.findByLocationAddressRemarks", query = "SELECT c FROM Checkin c WHERE c.locationAddressRemarks = :locationAddressRemarks")
    , @NamedQuery(name = "Checkin.findByLocationAddressGreeting", query = "SELECT c FROM Checkin c WHERE c.locationAddressGreeting = :locationAddressGreeting")
    , @NamedQuery(name = "Checkin.findByLocationAddressSalutation", query = "SELECT c FROM Checkin c WHERE c.locationAddressSalutation = :locationAddressSalutation")
    , @NamedQuery(name = "Checkin.findByLocationAddressTitle", query = "SELECT c FROM Checkin c WHERE c.locationAddressTitle = :locationAddressTitle")
    , @NamedQuery(name = "Checkin.findByLocationAddressAddresstype", query = "SELECT c FROM Checkin c WHERE c.locationAddressAddresstype = :locationAddressAddresstype")
    , @NamedQuery(name = "Checkin.findByReservationAddressCountryCode", query = "SELECT c FROM Checkin c WHERE c.reservationAddressCountryCode = :reservationAddressCountryCode")
    , @NamedQuery(name = "Checkin.findByReservationAddressCountryName", query = "SELECT c FROM Checkin c WHERE c.reservationAddressCountryName = :reservationAddressCountryName")
    , @NamedQuery(name = "Checkin.findByReservationAddressCurrencyCode", query = "SELECT c FROM Checkin c WHERE c.reservationAddressCurrencyCode = :reservationAddressCurrencyCode")
    , @NamedQuery(name = "Checkin.findByReservationAddressCurrencyName", query = "SELECT c FROM Checkin c WHERE c.reservationAddressCurrencyName = :reservationAddressCurrencyName")
    , @NamedQuery(name = "Checkin.findByReservationAddressCurrencySymbol", query = "SELECT c FROM Checkin c WHERE c.reservationAddressCurrencySymbol = :reservationAddressCurrencySymbol")
    , @NamedQuery(name = "Checkin.findByReservationAddressLanguageCode", query = "SELECT c FROM Checkin c WHERE c.reservationAddressLanguageCode = :reservationAddressLanguageCode")
    , @NamedQuery(name = "Checkin.findByReservationAddressLanguageName", query = "SELECT c FROM Checkin c WHERE c.reservationAddressLanguageName = :reservationAddressLanguageName")
    , @NamedQuery(name = "Checkin.findByReservationAddressChristianname", query = "SELECT c FROM Checkin c WHERE c.reservationAddressChristianname = :reservationAddressChristianname")
    , @NamedQuery(name = "Checkin.findByReservationAddressCity", query = "SELECT c FROM Checkin c WHERE c.reservationAddressCity = :reservationAddressCity")
    , @NamedQuery(name = "Checkin.findByReservationAddressEmail", query = "SELECT c FROM Checkin c WHERE c.reservationAddressEmail = :reservationAddressEmail")
    , @NamedQuery(name = "Checkin.findByReservationAddressName", query = "SELECT c FROM Checkin c WHERE c.reservationAddressName = :reservationAddressName")
    , @NamedQuery(name = "Checkin.findByReservationAddressPhone", query = "SELECT c FROM Checkin c WHERE c.reservationAddressPhone = :reservationAddressPhone")
    , @NamedQuery(name = "Checkin.findByReservationAddressZipcode", query = "SELECT c FROM Checkin c WHERE c.reservationAddressZipcode = :reservationAddressZipcode")
    , @NamedQuery(name = "Checkin.findByReservationAddressStreet", query = "SELECT c FROM Checkin c WHERE c.reservationAddressStreet = :reservationAddressStreet")
    , @NamedQuery(name = "Checkin.findByReservationAddressHomepage", query = "SELECT c FROM Checkin c WHERE c.reservationAddressHomepage = :reservationAddressHomepage")
    , @NamedQuery(name = "Checkin.findByReservationAddressRemarks", query = "SELECT c FROM Checkin c WHERE c.reservationAddressRemarks = :reservationAddressRemarks")
    , @NamedQuery(name = "Checkin.findByReservationAddressGreeting", query = "SELECT c FROM Checkin c WHERE c.reservationAddressGreeting = :reservationAddressGreeting")
    , @NamedQuery(name = "Checkin.findByReservationAddressSalutation", query = "SELECT c FROM Checkin c WHERE c.reservationAddressSalutation = :reservationAddressSalutation")
    , @NamedQuery(name = "Checkin.findByReservationAddressTitle", query = "SELECT c FROM Checkin c WHERE c.reservationAddressTitle = :reservationAddressTitle")
    , @NamedQuery(name = "Checkin.findByReservationAddressAddresstype", query = "SELECT c FROM Checkin c WHERE c.reservationAddressAddresstype = :reservationAddressAddresstype")
    , @NamedQuery(name = "Checkin.findByGuestAdressCountryCode", query = "SELECT c FROM Checkin c WHERE c.guestAdressCountryCode = :guestAdressCountryCode")
    , @NamedQuery(name = "Checkin.findByGuestAdressCountryName", query = "SELECT c FROM Checkin c WHERE c.guestAdressCountryName = :guestAdressCountryName")
    , @NamedQuery(name = "Checkin.findByGuestAdressCurrencyCode", query = "SELECT c FROM Checkin c WHERE c.guestAdressCurrencyCode = :guestAdressCurrencyCode")
    , @NamedQuery(name = "Checkin.findByGuestAdressCurrencyName", query = "SELECT c FROM Checkin c WHERE c.guestAdressCurrencyName = :guestAdressCurrencyName")
    , @NamedQuery(name = "Checkin.findByGuestAdressCurrencySymbol", query = "SELECT c FROM Checkin c WHERE c.guestAdressCurrencySymbol = :guestAdressCurrencySymbol")
    , @NamedQuery(name = "Checkin.findByGuestAdressLanguageCode", query = "SELECT c FROM Checkin c WHERE c.guestAdressLanguageCode = :guestAdressLanguageCode")
    , @NamedQuery(name = "Checkin.findByGuestAdressLanguageName", query = "SELECT c FROM Checkin c WHERE c.guestAdressLanguageName = :guestAdressLanguageName")
    , @NamedQuery(name = "Checkin.findByGuestAdressChristianname", query = "SELECT c FROM Checkin c WHERE c.guestAdressChristianname = :guestAdressChristianname")
    , @NamedQuery(name = "Checkin.findByGuestAdressCity", query = "SELECT c FROM Checkin c WHERE c.guestAdressCity = :guestAdressCity")
    , @NamedQuery(name = "Checkin.findByGuestAdressEmail", query = "SELECT c FROM Checkin c WHERE c.guestAdressEmail = :guestAdressEmail")
    , @NamedQuery(name = "Checkin.findByGuestAdressName", query = "SELECT c FROM Checkin c WHERE c.guestAdressName = :guestAdressName")
    , @NamedQuery(name = "Checkin.findByGuestAdressPhone", query = "SELECT c FROM Checkin c WHERE c.guestAdressPhone = :guestAdressPhone")
    , @NamedQuery(name = "Checkin.findByGuestAdressZipcode", query = "SELECT c FROM Checkin c WHERE c.guestAdressZipcode = :guestAdressZipcode")
    , @NamedQuery(name = "Checkin.findByGuestAdressStreet", query = "SELECT c FROM Checkin c WHERE c.guestAdressStreet = :guestAdressStreet")
    , @NamedQuery(name = "Checkin.findByGuestAdressHomepage", query = "SELECT c FROM Checkin c WHERE c.guestAdressHomepage = :guestAdressHomepage")
    , @NamedQuery(name = "Checkin.findByGuestAdressRemarks", query = "SELECT c FROM Checkin c WHERE c.guestAdressRemarks = :guestAdressRemarks")
    , @NamedQuery(name = "Checkin.findByGuestAdressGreeting", query = "SELECT c FROM Checkin c WHERE c.guestAdressGreeting = :guestAdressGreeting")
    , @NamedQuery(name = "Checkin.findByGuestAdressSalutation", query = "SELECT c FROM Checkin c WHERE c.guestAdressSalutation = :guestAdressSalutation")
    , @NamedQuery(name = "Checkin.findByGuestAdressTitle", query = "SELECT c FROM Checkin c WHERE c.guestAdressTitle = :guestAdressTitle")
    , @NamedQuery(name = "Checkin.findByGuestAdressAddresstype", query = "SELECT c FROM Checkin c WHERE c.guestAdressAddresstype = :guestAdressAddresstype")})
public class Checkin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "resno")
    private String resno;
    @Id
    @Column(name = "occ_id")
    private Long occId;
    @Column(name = "res_id")
    private Long resId;       
    @Column(name = "occ_arrivaltime")
    @Temporal(TemporalType.TIME)
    private Date occArrivaltime;
    @Column(name = "occ_departuretime")
    @Temporal(TemporalType.TIME)
    private Date occDeparturetime;
    @Column(name = "occ_arrivaldate")
    @Temporal(TemporalType.DATE)
    private Date occArrivaldate;
    @Column(name = "occ_departuredate")
    @Temporal(TemporalType.DATE)
    private Date occDeparturedate;
    @Column(name = "occ_maintenance")
    private BigInteger occMaintenance;
    @Column(name = "occ_housekeeping")
    private BigInteger occHousekeeping;
    @Column(name = "occ_checkin")
    private Boolean occCheckin;
    @Column(name = "occ_checkin_timestamp")
    private Boolean occCheckinTimestamp;
    @Column(name = "rooms_id")
    private Long room_id;
    @Column(name = "cat_id")
    private Long cat_id;
    @Size(max = 100)
    @Column(name = "cat_name")
    private String catName;
    @Size(max = 255)
    @Column(name = "cat_description")
    private String catDescription;
    @Size(max = 255)
    @Column(name = "rooms_code")
    private String room_code;
    @Size(max = 255)
    @Column(name = "rooms_name")
    private String room_name;
    @Column(name = "rooms_clean")
    private Boolean room_clean;
    @Column(name = "rooms_no_maintenance")
    private Boolean room_noMaintenance;
    @Column(name = "location_address_id")
    private Long location_address_id;
    @Size(max = 100)
    @Column(name = "location_floor")
    private String locationFloor;
    @Size(max = 100)
    @Column(name = "location_building")
    private String locationBuilding;
    @Size(max = 255)
    @Column(name = "location_christianname")
    private String locationChristianname;
    @Size(max = 255)
    @Column(name = "location_address_city")
    private String locationAddressCity;
    @Size(max = 255)
    @Column(name = "location_address_email")
    private String locationAddressEmail;
    @Size(max = 255)
    @Column(name = "location_address_name")
    private String locationAddressName;
    @Size(max = 255)
    @Column(name = "location_address_phone")
    private String locationAddressPhone;
    @Size(max = 255)
    @Column(name = "location_address_street")
    private String locationAddressStreet;
    @Size(max = 255)
    @Column(name = "location_address_zipcode")
    private String locationAddressZipcode;
    @Column(name = "location_address_country")
    private Integer locationAddressCountry;
    @Column(name = "location_address_currency")
    private Integer locationAddressCurrency;
    @Column(name = "location_address_language")
    private Integer locationAddressLanguage;
    @Size(max = 100)
    @Column(name = "location_address_homepage")
    private String locationAddressHomepage;
    @Size(max = 200)
    @Column(name = "location_address_remarks")
    private String locationAddressRemarks;
    @Size(max = 50)
    @Column(name = "location_address_greeting")
    private String locationAddressGreeting;
    @Size(max = 50)
    @Column(name = "location_address_salutation")
    private String locationAddressSalutation;
    @Size(max = 50)
    @Column(name = "location_address_title")
    private String locationAddressTitle;
    @Size(max = 100)
    @Column(name = "location_address_addresstype")
    private String locationAddressAddresstype;
    @Column(name = "reservation_address_id")
    private Long reservation_address_id;
    @Size(max = 10)
    @Column(name = "reservation_address_country_code")
    private String reservationAddressCountryCode;
    @Size(max = 100)
    @Column(name = "reservation_address_country_name")
    private String reservationAddressCountryName;
    @Size(max = 255)
    @Column(name = "reservation_address_currency_code")
    private String reservationAddressCurrencyCode;
    @Size(max = 255)
    @Column(name = "reservation_address_currency_name")
    private String reservationAddressCurrencyName;
    @Column(name = "reservation_address_currency_symbol")
    private Character reservationAddressCurrencySymbol;
    @Size(max = 5)
    @Column(name = "reservation_address_language_code")
    private String reservationAddressLanguageCode;
    @Size(max = 50)
    @Column(name = "reservation_address_language_name")
    private String reservationAddressLanguageName;
    @Size(max = 255)
    @Column(name = "reservation_address_christianname")
    private String reservationAddressChristianname;
    @Size(max = 255)
    @Column(name = "reservation_address_city")
    private String reservationAddressCity;
    @Size(max = 255)
    @Column(name = "reservation_address_email")
    private String reservationAddressEmail;
    @Size(max = 255)
    @Column(name = "reservation_address_name")
    private String reservationAddressName;
    @Size(max = 255)
    @Column(name = "reservation_address_phone")
    private String reservationAddressPhone;
    @Size(max = 255)
    @Column(name = "reservation_address_zipcode")
    private String reservationAddressZipcode;
    @Size(max = 255)
    @Column(name = "reservation_address_street")
    private String reservationAddressStreet;
    @Size(max = 100)
    @Column(name = "reservation_address_homepage")
    private String reservationAddressHomepage;
    @Size(max = 200)
    @Column(name = "reservation_address_remarks")
    private String reservationAddressRemarks;
    @Size(max = 50)
    @Column(name = "reservation_address_greeting")
    private String reservationAddressGreeting;
    @Size(max = 50)
    @Column(name = "reservation_address_salutation")
    private String reservationAddressSalutation;
    @Size(max = 50)
    @Column(name = "reservation_address_title")
    private String reservationAddressTitle;
    @Size(max = 100)
    @Column(name = "reservation_address_addresstype")
    private String reservationAddressAddresstype;
    @Column(name = "guest_adress_id")
    private Long guest_adress_id;
    @Size(max = 10)
    @Column(name = "guest_adress_country_code")
    private String guestAdressCountryCode;
    @Size(max = 100)
    @Column(name = "guest_adress_country_name")
    private String guestAdressCountryName;
    @Size(max = 255)
    @Column(name = "guest_adress_currency_code")
    private String guestAdressCurrencyCode;
    @Size(max = 255)
    @Column(name = "guest_adress_currency_name")
    private String guestAdressCurrencyName;
    @Column(name = "guest_adress_currency_symbol")
    private Character guestAdressCurrencySymbol;
    @Size(max = 5)
    @Column(name = "guest_adress_language_code")
    private String guestAdressLanguageCode;
    @Size(max = 50)
    @Column(name = "guest_adress_language_name")
    private String guestAdressLanguageName;
    @Size(max = 255)
    @Column(name = "guest_adress_christianname")
    private String guestAdressChristianname;
    @Size(max = 255)
    @Column(name = "guest_adress_city")
    private String guestAdressCity;
    @Size(max = 255)
    @Column(name = "guest_adress_email")
    private String guestAdressEmail;
    @Size(max = 255)
    @Column(name = "guest_adress_name")
    private String guestAdressName;
    @Size(max = 255)
    @Column(name = "guest_adress_phone")
    private String guestAdressPhone;
    @Size(max = 255)
    @Column(name = "guest_adress_zipcode")
    private String guestAdressZipcode;
    @Size(max = 255)
    @Column(name = "guest_adress_street")
    private String guestAdressStreet;
    @Size(max = 100)
    @Column(name = "guest_adress_homepage")
    private String guestAdressHomepage;
    @Size(max = 200)
    @Column(name = "guest_adress_remarks")
    private String guestAdressRemarks;
    @Size(max = 50)
    @Column(name = "guest_adress_greeting")
    private String guestAdressGreeting;
    @Size(max = 50)
    @Column(name = "guest_adress_salutation")
    private String guestAdressSalutation;
    @Size(max = 50)
    @Column(name = "guest_adress_title")
    private String guestAdressTitle;
    @Size(max = 100)
    @Column(name = "guest_adress_addresstype")
    private String guestAdressAddresstype;

    
    
    public Checkin() {
    }

    


    public Boolean getOccCheckin() {
        return occCheckin;
    }

    public Boolean getOccCheckinTimestamp() {
        return occCheckinTimestamp;
    }

    public String getRoom_code() {
        return room_code;
    }

    public String getRoom_name() {
        return room_name;
    }

    public Boolean getRoom_clean() {
        return room_clean;
    }

    public Boolean getRoom_noMaintenance() {
        return room_noMaintenance;
    }
    
    public String getResno() {
        return resno;
    }

    public void setResno(String resno) {
        this.resno = resno;
    }

    public Long getOccId() {
        return occId;
    }

    public void setOccId(Long occId) {
        this.occId = occId;
    }

    public Date getOccArrivaltime() {
        return occArrivaltime;
    }

    public void setOccArrivaltime(Date occArrivaltime) {
        this.occArrivaltime = occArrivaltime;
    }

    public Date getOccDeparturetime() {
        return occDeparturetime;
    }

    public void setOccDeparturetime(Date occDeparturetime) {
        this.occDeparturetime = occDeparturetime;
    }

    public Date getOccArrivaldate() {
        return occArrivaldate;
    }

    public void setOccArrivaldate(Date occArrivaldate) {
        this.occArrivaldate = occArrivaldate;
    }

    public Date getOccDeparturedate() {
        return occDeparturedate;
    }

    public void setOccDeparturedate(Date occDeparturedate) {
        this.occDeparturedate = occDeparturedate;
    }

    public BigInteger getOccMaintenance() {
        return occMaintenance;
    }

    public void setOccMaintenance(BigInteger occMaintenance) {
        this.occMaintenance = occMaintenance;
    }

    public BigInteger getOccHousekeeping() {
        return occHousekeeping;
    }

    public void setOccHousekeeping(BigInteger occHousekeeping) {
        this.occHousekeeping = occHousekeeping;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public String getRoomCode() {
        return room_code;
    }

    public void setRoomCode(String code) {
        this.room_code = code;
    }

    public String getRoomName() {
        return room_name;
    }

    public void setRoomName(String name) {
        this.room_name = name;
    }

    public Boolean getRoomClean() {
        return room_clean;
    }

    public void setRoomClean(Boolean clean) {
        this.room_clean = clean;
    }

    public Boolean getRoomNoMaintenance() {
        return room_noMaintenance;
    }

    public void setNoRoomMaintenance(Boolean noMaintenance) {
        this.room_noMaintenance = noMaintenance;
    }

    public String getLocationFloor() {
        return locationFloor;
    }

    public void setLocationFloor(String locationFloor) {
        this.locationFloor = locationFloor;
    }

    public String getLocationBuilding() {
        return locationBuilding;
    }

    public void setLocationBuilding(String locationBuilding) {
        this.locationBuilding = locationBuilding;
    }

    public String getLocationChristianname() {
        return locationChristianname;
    }

    public void setLocationChristianname(String locationChristianname) {
        this.locationChristianname = locationChristianname;
    }

    public String getLocationAddressCity() {
        return locationAddressCity;
    }

    public void setLocationAddressCity(String locationAddressCity) {
        this.locationAddressCity = locationAddressCity;
    }

    public String getLocationAddressEmail() {
        return locationAddressEmail;
    }

    public void setLocationAddressEmail(String locationAddressEmail) {
        this.locationAddressEmail = locationAddressEmail;
    }

    public String getLocationAddressName() {
        return locationAddressName;
    }

    public void setLocationAddressName(String locationAddressName) {
        this.locationAddressName = locationAddressName;
    }

    public String getLocationAddressPhone() {
        return locationAddressPhone;
    }

    public void setLocationAddressPhone(String locationAddressPhone) {
        this.locationAddressPhone = locationAddressPhone;
    }

    public String getLocationAddressStreet() {
        return locationAddressStreet;
    }

    public void setLocationAddressStreet(String locationAddressStreet) {
        this.locationAddressStreet = locationAddressStreet;
    }

    public String getLocationAddressZipcode() {
        return locationAddressZipcode;
    }

    public void setLocationAddressZipcode(String locationAddressZipcode) {
        this.locationAddressZipcode = locationAddressZipcode;
    }

    public Integer getLocationAddressCountry() {
        return locationAddressCountry;
    }

    public void setLocationAddressCountry(Integer locationAddressCountry) {
        this.locationAddressCountry = locationAddressCountry;
    }

    public Integer getLocationAddressCurrency() {
        return locationAddressCurrency;
    }

    public void setLocationAddressCurrency(Integer locationAddressCurrency) {
        this.locationAddressCurrency = locationAddressCurrency;
    }

    public Integer getLocationAddressLanguage() {
        return locationAddressLanguage;
    }

    public void setLocationAddressLanguage(Integer locationAddressLanguage) {
        this.locationAddressLanguage = locationAddressLanguage;
    }

    public String getLocationAddressHomepage() {
        return locationAddressHomepage;
    }

    public void setLocationAddressHomepage(String locationAddressHomepage) {
        this.locationAddressHomepage = locationAddressHomepage;
    }

    public String getLocationAddressRemarks() {
        return locationAddressRemarks;
    }

    public void setLocationAddressRemarks(String locationAddressRemarks) {
        this.locationAddressRemarks = locationAddressRemarks;
    }

    public String getLocationAddressGreeting() {
        return locationAddressGreeting;
    }

    public void setLocationAddressGreeting(String locationAddressGreeting) {
        this.locationAddressGreeting = locationAddressGreeting;
    }

    public String getLocationAddressSalutation() {
        return locationAddressSalutation;
    }

    public void setLocationAddressSalutation(String locationAddressSalutation) {
        this.locationAddressSalutation = locationAddressSalutation;
    }

    public String getLocationAddressTitle() {
        return locationAddressTitle;
    }

    public void setLocationAddressTitle(String locationAddressTitle) {
        this.locationAddressTitle = locationAddressTitle;
    }

    public String getLocationAddressAddresstype() {
        return locationAddressAddresstype;
    }

    public void setLocationAddressAddresstype(String locationAddressAddresstype) {
        this.locationAddressAddresstype = locationAddressAddresstype;
    }

    public String getReservationAddressCountryCode() {
        return reservationAddressCountryCode;
    }

    public void setReservationAddressCountryCode(String reservationAddressCountryCode) {
        this.reservationAddressCountryCode = reservationAddressCountryCode;
    }

    public String getReservationAddressCountryName() {
        return reservationAddressCountryName;
    }

    public void setReservationAddressCountryName(String reservationAddressCountryName) {
        this.reservationAddressCountryName = reservationAddressCountryName;
    }

    public String getReservationAddressCurrencyCode() {
        return reservationAddressCurrencyCode;
    }

    public void setReservationAddressCurrencyCode(String reservationAddressCurrencyCode) {
        this.reservationAddressCurrencyCode = reservationAddressCurrencyCode;
    }

    public String getReservationAddressCurrencyName() {
        return reservationAddressCurrencyName;
    }

    public void setReservationAddressCurrencyName(String reservationAddressCurrencyName) {
        this.reservationAddressCurrencyName = reservationAddressCurrencyName;
    }

    public Character getReservationAddressCurrencySymbol() {
        return reservationAddressCurrencySymbol;
    }

    public void setReservationAddressCurrencySymbol(Character reservationAddressCurrencySymbol) {
        this.reservationAddressCurrencySymbol = reservationAddressCurrencySymbol;
    }

    public String getReservationAddressLanguageCode() {
        return reservationAddressLanguageCode;
    }

    public void setReservationAddressLanguageCode(String reservationAddressLanguageCode) {
        this.reservationAddressLanguageCode = reservationAddressLanguageCode;
    }

    public String getReservationAddressLanguageName() {
        return reservationAddressLanguageName;
    }

    public void setReservationAddressLanguageName(String reservationAddressLanguageName) {
        this.reservationAddressLanguageName = reservationAddressLanguageName;
    }

    public String getReservationAddressChristianname() {
        return reservationAddressChristianname;
    }

    public void setReservationAddressChristianname(String reservationAddressChristianname) {
        this.reservationAddressChristianname = reservationAddressChristianname;
    }

    public String getReservationAddressCity() {
        return reservationAddressCity;
    }

    public void setReservationAddressCity(String reservationAddressCity) {
        this.reservationAddressCity = reservationAddressCity;
    }

    public String getReservationAddressEmail() {
        return reservationAddressEmail;
    }

    public void setReservationAddressEmail(String reservationAddressEmail) {
        this.reservationAddressEmail = reservationAddressEmail;
    }

    public String getReservationAddressName() {
        return reservationAddressName;
    }

    public void setReservationAddressName(String reservationAddressName) {
        this.reservationAddressName = reservationAddressName;
    }

    public String getReservationAddressPhone() {
        return reservationAddressPhone;
    }

    public void setReservationAddressPhone(String reservationAddressPhone) {
        this.reservationAddressPhone = reservationAddressPhone;
    }

    public String getReservationAddressZipcode() {
        return reservationAddressZipcode;
    }

    public void setReservationAddressZipcode(String reservationAddressZipcode) {
        this.reservationAddressZipcode = reservationAddressZipcode;
    }

    public String getReservationAddressStreet() {
        return reservationAddressStreet;
    }

    public void setReservationAddressStreet(String reservationAddressStreet) {
        this.reservationAddressStreet = reservationAddressStreet;
    }

    public String getReservationAddressHomepage() {
        return reservationAddressHomepage;
    }

    public void setReservationAddressHomepage(String reservationAddressHomepage) {
        this.reservationAddressHomepage = reservationAddressHomepage;
    }

    public String getReservationAddressRemarks() {
        return reservationAddressRemarks;
    }

    public void setReservationAddressRemarks(String reservationAddressRemarks) {
        this.reservationAddressRemarks = reservationAddressRemarks;
    }

    public String getReservationAddressGreeting() {
        return reservationAddressGreeting;
    }

    public void setReservationAddressGreeting(String reservationAddressGreeting) {
        this.reservationAddressGreeting = reservationAddressGreeting;
    }

    public String getReservationAddressSalutation() {
        return reservationAddressSalutation;
    }

    public void setReservationAddressSalutation(String reservationAddressSalutation) {
        this.reservationAddressSalutation = reservationAddressSalutation;
    }

    public String getReservationAddressTitle() {
        return reservationAddressTitle;
    }

    public void setReservationAddressTitle(String reservationAddressTitle) {
        this.reservationAddressTitle = reservationAddressTitle;
    }

    public String getReservationAddressAddresstype() {
        return reservationAddressAddresstype;
    }

    public void setReservationAddressAddresstype(String reservationAddressAddresstype) {
        this.reservationAddressAddresstype = reservationAddressAddresstype;
    }

    public String getGuestAdressCountryCode() {
        return guestAdressCountryCode;
    }

    public void setGuestAdressCountryCode(String guestAdressCountryCode) {
        this.guestAdressCountryCode = guestAdressCountryCode;
    }

    public String getGuestAdressCountryName() {
        return guestAdressCountryName;
    }

    public void setGuestAdressCountryName(String guestAdressCountryName) {
        this.guestAdressCountryName = guestAdressCountryName;
    }

    public String getGuestAdressCurrencyCode() {
        return guestAdressCurrencyCode;
    }

    public void setGuestAdressCurrencyCode(String guestAdressCurrencyCode) {
        this.guestAdressCurrencyCode = guestAdressCurrencyCode;
    }

    public String getGuestAdressCurrencyName() {
        return guestAdressCurrencyName;
    }

    public void setGuestAdressCurrencyName(String guestAdressCurrencyName) {
        this.guestAdressCurrencyName = guestAdressCurrencyName;
    }

    public Character getGuestAdressCurrencySymbol() {
        return guestAdressCurrencySymbol;
    }

    public void setGuestAdressCurrencySymbol(Character guestAdressCurrencySymbol) {
        this.guestAdressCurrencySymbol = guestAdressCurrencySymbol;
    }

    public String getGuestAdressLanguageCode() {
        return guestAdressLanguageCode;
    }

    public void setGuestAdressLanguageCode(String guestAdressLanguageCode) {
        this.guestAdressLanguageCode = guestAdressLanguageCode;
    }

    public String getGuestAdressLanguageName() {
        return guestAdressLanguageName;
    }

    public void setGuestAdressLanguageName(String guestAdressLanguageName) {
        this.guestAdressLanguageName = guestAdressLanguageName;
    }

    public String getGuestAdressChristianname() {
        return guestAdressChristianname;
    }

    public void setGuestAdressChristianname(String guestAdressChristianname) {
        this.guestAdressChristianname = guestAdressChristianname;
    }

    public String getGuestAdressCity() {
        return guestAdressCity;
    }

    public void setGuestAdressCity(String guestAdressCity) {
        this.guestAdressCity = guestAdressCity;
    }

    public String getGuestAdressEmail() {
        return guestAdressEmail;
    }

    public void setGuestAdressEmail(String guestAdressEmail) {
        this.guestAdressEmail = guestAdressEmail;
    }

    public String getGuestAdressName() {
        return guestAdressName;
    }

    public void setGuestAdressName(String guestAdressName) {
        this.guestAdressName = guestAdressName;
    }

    public String getGuestAdressPhone() {
        return guestAdressPhone;
    }

    public void setGuestAdressPhone(String guestAdressPhone) {
        this.guestAdressPhone = guestAdressPhone;
    }

    public String getGuestAdressZipcode() {
        return guestAdressZipcode;
    }

    public void setGuestAdressZipcode(String guestAdressZipcode) {
        this.guestAdressZipcode = guestAdressZipcode;
    }

    public String getGuestAdressStreet() {
        return guestAdressStreet;
    }

    public void setGuestAdressStreet(String guestAdressStreet) {
        this.guestAdressStreet = guestAdressStreet;
    }

    public String getGuestAdressHomepage() {
        return guestAdressHomepage;
    }

    public void setGuestAdressHomepage(String guestAdressHomepage) {
        this.guestAdressHomepage = guestAdressHomepage;
    }

    public String getGuestAdressRemarks() {
        return guestAdressRemarks;
    }

    public void setGuestAdressRemarks(String guestAdressRemarks) {
        this.guestAdressRemarks = guestAdressRemarks;
    }

    public String getGuestAdressGreeting() {
        return guestAdressGreeting;
    }

    public void setGuestAdressGreeting(String guestAdressGreeting) {
        this.guestAdressGreeting = guestAdressGreeting;
    }

    public String getGuestAdressSalutation() {
        return guestAdressSalutation;
    }

    public void setGuestAdressSalutation(String guestAdressSalutation) {
        this.guestAdressSalutation = guestAdressSalutation;
    }

    public String getGuestAdressTitle() {
        return guestAdressTitle;
    }

    public void setGuestAdressTitle(String guestAdressTitle) {
        this.guestAdressTitle = guestAdressTitle;
    }

    public String getGuestAdressAddresstype() {
        return guestAdressAddresstype;
    }

    public void setGuestAdressAddresstype(String guestAdressAddresstype) {
        this.guestAdressAddresstype = guestAdressAddresstype;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public Long getLocation_address_id() {
        return location_address_id;
    }

    public void setLocation_address_id(Long location_address_id) {
        this.location_address_id = location_address_id;
    }

    public Long getReservation_address_id() {
        return reservation_address_id;
    }

    public void setReservation_address_id(Long reservation_address_id) {
        this.reservation_address_id = reservation_address_id;
    }

    public Long getGuest_adress_id() {
        return guest_adress_id;
    }

    public void setGuest_adress_id(Long guest_adress_id) {
        this.guest_adress_id = guest_adress_id;
    }
     
    
    
    
    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }
    
}
