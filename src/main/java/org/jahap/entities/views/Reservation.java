package org.jahap.entities.views;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the reservations database table.
 * 
 */
@Entity
@Table(name="reservations")
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date arrivaldate;

	@Column(name="cat_description")
	private String catDescription;

	@Column(name="cat_name")
	private String catName;

	private String code;

	@Temporal(TemporalType.DATE)
	private Date departuredate;

	@Column(name="guest_christianname")
	private String guestChristianname;

	@Column(name="guest_city")
	private String guestCity;

	@Column(name="guest_email")
	private String guestEmail;

	@Column(name="guest_greeting")
	private String guestGreeting;

	@Column(name="guest_language_code")
	private String guestLanguageCode;

	@Column(name="guest_language_name")
	private String guestLanguageName;

	@Column(name="guest_name")
	private String guestName;

	@Column(name="guest_phone")
	private String guestPhone;

	@Column(name="guest_remarks")
	private String guestRemarks;

	@Column(name="guest_salutation")
	private String guestSalutation;

	@Column(name="guest_street")
	private String guestStreet;

	@Column(name="guest_title")
	private String guestTitle;

	@Column(name="guest_zipcode")
	private String guestZipcode;

	@Column(name="hotel_bankaccountdata1")
	private String hotelBankaccountdata1;

	@Column(name="hotel_bankaccountdata2")
	private String hotelBankaccountdata2;

	@Column(name="hotel_code")
	private String hotelCode;

	@Column(name="hotel_footertext")
	private String hotelFootertext;

	@Column(name="hotel_name")
	private String hotelName;
    
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
	private Long id;

	private String name;

	@Column(name="orderer_christianname")
	private String ordererChristianname;

	@Column(name="orderer_city")
	private String ordererCity;

	@Column(name="orderer_email")
	private String ordererEmail;

	@Column(name="orderer_greeting")
	private String ordererGreeting;

	@Column(name="orderer_language_code")
	private String ordererLanguageCode;

	@Column(name="orderer_language_name")
	private String ordererLanguageName;

	@Column(name="orderer_name")
	private String ordererName;

	@Column(name="orderer_phone")
	private String ordererPhone;

	@Column(name="orderer_remarks")
	private String ordererRemarks;

	@Column(name="orderer_salutation")
	private String ordererSalutation;

	@Column(name="orderer_street")
	private String ordererStreet;

	@Column(name="orderer_title")
	private String ordererTitle;

	@Column(name="orderer_zipcode")
	private String ordererZipcode;

	private String resno;

	@Column(name="services_amount")
	private Integer servicesAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="services_fromdate")
	private Date servicesFromdate;

	@Column(name="services_price")
	private BigDecimal servicesPrice;

	@Temporal(TemporalType.DATE)
	@Column(name="services_todate")
	private Date servicesTodate;

	public Reservation() {
	}

	public Date getArrivaldate() {
		return this.arrivaldate;
	}

	public void setArrivaldate(Date arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getCatDescription() {
		return this.catDescription;
	}

	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDeparturedate() {
		return this.departuredate;
	}

	public void setDeparturedate(Date departuredate) {
		this.departuredate = departuredate;
	}

	public String getGuestChristianname() {
		return this.guestChristianname;
	}

	public void setGuestChristianname(String guestChristianname) {
		this.guestChristianname = guestChristianname;
	}

	public String getGuestCity() {
		return this.guestCity;
	}

	public void setGuestCity(String guestCity) {
		this.guestCity = guestCity;
	}

	public String getGuestEmail() {
		return this.guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestGreeting() {
		return this.guestGreeting;
	}

	public void setGuestGreeting(String guestGreeting) {
		this.guestGreeting = guestGreeting;
	}

	public String getGuestLanguageCode() {
		return this.guestLanguageCode;
	}

	public void setGuestLanguageCode(String guestLanguageCode) {
		this.guestLanguageCode = guestLanguageCode;
	}

	public String getGuestLanguageName() {
		return this.guestLanguageName;
	}

	public void setGuestLanguageName(String guestLanguageName) {
		this.guestLanguageName = guestLanguageName;
	}

	public String getGuestName() {
		return this.guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestPhone() {
		return this.guestPhone;
	}

	public void setGuestPhone(String guestPhone) {
		this.guestPhone = guestPhone;
	}

	public String getGuestRemarks() {
		return this.guestRemarks;
	}

	public void setGuestRemarks(String guestRemarks) {
		this.guestRemarks = guestRemarks;
	}

	public String getGuestSalutation() {
		return this.guestSalutation;
	}

	public void setGuestSalutation(String guestSalutation) {
		this.guestSalutation = guestSalutation;
	}

	public String getGuestStreet() {
		return this.guestStreet;
	}

	public void setGuestStreet(String guestStreet) {
		this.guestStreet = guestStreet;
	}

	public String getGuestTitle() {
		return this.guestTitle;
	}

	public void setGuestTitle(String guestTitle) {
		this.guestTitle = guestTitle;
	}

	public String getGuestZipcode() {
		return this.guestZipcode;
	}

	public void setGuestZipcode(String guestZipcode) {
		this.guestZipcode = guestZipcode;
	}

	public String getHotelBankaccountdata1() {
		return this.hotelBankaccountdata1;
	}

	public void setHotelBankaccountdata1(String hotelBankaccountdata1) {
		this.hotelBankaccountdata1 = hotelBankaccountdata1;
	}

	public String getHotelBankaccountdata2() {
		return this.hotelBankaccountdata2;
	}

	public void setHotelBankaccountdata2(String hotelBankaccountdata2) {
		this.hotelBankaccountdata2 = hotelBankaccountdata2;
	}

	public String getHotelCode() {
		return this.hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelFootertext() {
		return this.hotelFootertext;
	}

	public void setHotelFootertext(String hotelFootertext) {
		this.hotelFootertext = hotelFootertext;
	}

	public String getHotelName() {
		return this.hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrdererChristianname() {
		return this.ordererChristianname;
	}

	public void setOrdererChristianname(String ordererChristianname) {
		this.ordererChristianname = ordererChristianname;
	}

	public String getOrdererCity() {
		return this.ordererCity;
	}

	public void setOrdererCity(String ordererCity) {
		this.ordererCity = ordererCity;
	}

	public String getOrdererEmail() {
		return this.ordererEmail;
	}

	public void setOrdererEmail(String ordererEmail) {
		this.ordererEmail = ordererEmail;
	}

	public String getOrdererGreeting() {
		return this.ordererGreeting;
	}

	public void setOrdererGreeting(String ordererGreeting) {
		this.ordererGreeting = ordererGreeting;
	}

	public String getOrdererLanguageCode() {
		return this.ordererLanguageCode;
	}

	public void setOrdererLanguageCode(String ordererLanguageCode) {
		this.ordererLanguageCode = ordererLanguageCode;
	}

	public String getOrdererLanguageName() {
		return this.ordererLanguageName;
	}

	public void setOrdererLanguageName(String ordererLanguageName) {
		this.ordererLanguageName = ordererLanguageName;
	}

	public String getOrdererName() {
		return this.ordererName;
	}

	public void setOrdererName(String ordererName) {
		this.ordererName = ordererName;
	}

	public String getOrdererPhone() {
		return this.ordererPhone;
	}

	public void setOrdererPhone(String ordererPhone) {
		this.ordererPhone = ordererPhone;
	}

	public String getOrdererRemarks() {
		return this.ordererRemarks;
	}

	public void setOrdererRemarks(String ordererRemarks) {
		this.ordererRemarks = ordererRemarks;
	}

	public String getOrdererSalutation() {
		return this.ordererSalutation;
	}

	public void setOrdererSalutation(String ordererSalutation) {
		this.ordererSalutation = ordererSalutation;
	}

	public String getOrdererStreet() {
		return this.ordererStreet;
	}

	public void setOrdererStreet(String ordererStreet) {
		this.ordererStreet = ordererStreet;
	}

	public String getOrdererTitle() {
		return this.ordererTitle;
	}

	public void setOrdererTitle(String ordererTitle) {
		this.ordererTitle = ordererTitle;
	}

	public String getOrdererZipcode() {
		return this.ordererZipcode;
	}

	public void setOrdererZipcode(String ordererZipcode) {
		this.ordererZipcode = ordererZipcode;
	}

	public String getResno() {
		return this.resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public Integer getServicesAmount() {
		return this.servicesAmount;
	}

	public void setServicesAmount(Integer servicesAmount) {
		this.servicesAmount = servicesAmount;
	}

	public Date getServicesFromdate() {
		return this.servicesFromdate;
	}

	public void setServicesFromdate(Date servicesFromdate) {
		this.servicesFromdate = servicesFromdate;
	}

	public BigDecimal getServicesPrice() {
		return this.servicesPrice;
	}

	public void setServicesPrice(BigDecimal servicesPrice) {
		this.servicesPrice = servicesPrice;
	}

	public Date getServicesTodate() {
		return this.servicesTodate;
	}

	public void setServicesTodate(Date servicesTodate) {
		this.servicesTodate = servicesTodate;
	}

}