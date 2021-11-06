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


package org.jahap.sreport;

import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.jahap.business.base.Hotelbean;
import org.jahap.entities.base.Address;
import org.jahap.entities.views.Checkin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russ
 */
public class checkinDataSource implements JRDataSource{

    
Logger log = LoggerFactory.getLogger(checkinDataSource.class);    private List<Checkin>CheckinSource;
    private Hotelbean hbean;

    public checkinDataSource(List<Checkin>CheckinSource) {
        this.hbean = new Hotelbean();
        log.debug("checkinDataSource");
        this.CheckinSource=CheckinSource;
    }
   
    
   private int counter=-1;
    
   private HashMap<String,Integer> fieldNumber= new HashMap<String, Integer>();

   
  
   
   
    public boolean next() throws JRException {
        if(counter<CheckinSource.size()-1){
            counter++;
             return true;   
                }
        return false;
    }

    public Object getFieldValue(JRField jrf) throws JRException {
        log.debug("Reportfield: " + String.valueOf(jrf.getName()));
      if(jrf.getName().equals("resno" )){
          log.debug("resno " + String.valueOf(CheckinSource.get(counter).getResno()) );

          return CheckinSource.get(counter).getResno();
      }
      else if(jrf.getName().equals("res_id"))return CheckinSource.get(counter).getResId();
      else if(jrf.getName().equals("occ_id"))return CheckinSource.get(counter).getOccId();
      else if(jrf.getName().equals("occ_arrivaltime"))return CheckinSource.get(counter).getOccArrivaltime();
      else if(jrf.getName().equals("occ_departuretime"))return CheckinSource.get(counter).getOccDeparturetime();
      else if(jrf.getName().equals("occ_arrivaldate"))return CheckinSource.get(counter).getOccArrivaldate();
      else if(jrf.getName().equals("occ_departuredate"))return CheckinSource.get(counter).getOccDeparturedate();
      else if(jrf.getName().equals("occ_maintenance"))return CheckinSource.get(counter).getOccMaintenance();
      else if(jrf.getName().equals("occ_housekeeping"))return CheckinSource.get(counter).getOccHousekeeping();
      else if(jrf.getName().equals("cat_name" ))return CheckinSource.get(counter).getCatName();
      else if(jrf.getName().equals("cat_description" ))return CheckinSource.get(counter).getCatDescription();
      else if(jrf.getName().equals("rooms_id"))return CheckinSource.get(counter).getRoom_id();
      else if(jrf.getName().equals("rooms_code" ))return CheckinSource.get(counter).getRoomCode();
      else if(jrf.getName().equals("rooms_name" ))return CheckinSource.get(counter).getRoomName();
      else if(jrf.getName().equals("rooms_clean" ))return CheckinSource.get(counter).getRoomClean();
      else if(jrf.getName().equals("rooms_no_maintenance" ))return CheckinSource.get(counter).getRoomNoMaintenance();
      else if(jrf.getName().equals("location_floor" ))return CheckinSource.get(counter).getLocationFloor();
      else if(jrf.getName().equals("location_building" ))return CheckinSource.get(counter).getLocationBuilding();
      else if(jrf.getName().equals("location_christianname" ))return CheckinSource.get(counter).getLocationChristianname();
      else if(jrf.getName().equals("location_address_city" ))return CheckinSource.get(counter).getLocationAddressCity();
      else if(jrf.getName().equals("location_address_email" ))return CheckinSource.get(counter).getLocationAddressEmail();
      else if(jrf.getName().equals("location_address_name" )) return CheckinSource.get(counter).getLocationAddressName();
      else if(jrf.getName().equals("location_address_phone" )) return CheckinSource.get(counter).getLocationAddressPhone();
      else if(jrf.getName().equals("location_address_street" )) return CheckinSource.get(counter).getLocationAddressStreet();
      else if(jrf.getName().equals("location_address_zipcode" ))return CheckinSource.get(counter).getLocationAddressZipcode();
      else if(jrf.getName().equals("location_address_country" )) return CheckinSource.get(counter).getLocationAddressCountry();
      else if(jrf.getName().equals("location_address_currency" ))return CheckinSource.get(counter).getLocationAddressCurrency();
      else if(jrf.getName().equals("location_address_language" )) return CheckinSource.get(counter).getLocationAddressLanguage();
      else if(jrf.getName().equals("location_address_homepage" )) return CheckinSource.get(counter).getLocationAddressHomepage();
      else if(jrf.getName().equals("location_address_remarks" )) return CheckinSource.get(counter).getLocationAddressRemarks();
      else if(jrf.getName().equals("location_address_greeting" )) return CheckinSource.get(counter).getLocationAddressGreeting();
      else if(jrf.getName().equals("location_address_salutation" )) return CheckinSource.get(counter).getLocationAddressSalutation();
      else if(jrf.getName().equals("location_address_title" )) return CheckinSource.get(counter).getLocationAddressTitle();
      else if(jrf.getName().equals("location_address_addresstype" )) return CheckinSource.get(counter).getLocationAddressAddresstype();
      else if(jrf.getName().equals("reservation_address_country_code" )) return CheckinSource.get(counter).getReservationAddressCountryCode();
      else if(jrf.getName().equals("reservation_address_country_name" )) return CheckinSource.get(counter).getReservationAddressCountryName();
      else if(jrf.getName().equals("reservation_address_currency_code" )) return CheckinSource.get(counter).getReservationAddressCurrencyCode();
      else if(jrf.getName().equals("reservation_address_currency_name" )) return CheckinSource.get(counter).getReservationAddressCurrencyName();
      else if(jrf.getName().equals("reservation_address_currency_symbol" )) return CheckinSource.get(counter).getReservationAddressCurrencySymbol();
      else if(jrf.getName().equals("reservation_address_language_code" )) return CheckinSource.get(counter).getReservationAddressLanguageCode();
      else if(jrf.getName().equals("reservation_address_language_name" )) return CheckinSource.get(counter).getReservationAddressLanguageName();
      else if(jrf.getName().equals("reservation_address_christianname" )) return CheckinSource.get(counter).getReservationAddressChristianname();
      else if(jrf.getName().equals("reservation_address_city" )) return CheckinSource.get(counter).getReservationAddressCity();
      else if(jrf.getName().equals("reservation_address_email" )) return CheckinSource.get(counter).getReservationAddressEmail();
      else if(jrf.getName().equals("reservation_address_name" )) return CheckinSource.get(counter).getReservationAddressName();
      else if(jrf.getName().equals("reservation_address_phone" )) return CheckinSource.get(counter).getReservationAddressPhone();
      else if(jrf.getName().equals("reservation_address_zipcode" )) return CheckinSource.get(counter).getReservationAddressZipcode();
      else if(jrf.getName().equals("reservation_address_street" )) return CheckinSource.get(counter).getReservationAddressStreet();
      else if(jrf.getName().equals("reservation_address_homepage" )) return CheckinSource.get(counter).getGuestAdressHomepage();
      else if(jrf.getName().equals("reservation_address_remarks" )) return CheckinSource.get(counter).getReservationAddressRemarks();
      else if(jrf.getName().equals("reservation_address_greeting" )) return CheckinSource.get(counter).getReservationAddressGreeting();
      else if(jrf.getName().equals("reservation_address_salutation" )) return CheckinSource.get(counter).getReservationAddressSalutation();
      else if(jrf.getName().equals("reservation_address_title" )) return CheckinSource.get(counter).getReservationAddressTitle();
      else if(jrf.getName().equals("reservation_address_addresstype" )) return CheckinSource.get(counter).getReservationAddressAddresstype();
      else if(jrf.getName().equals("guest_adress_country_code" )) return CheckinSource.get(counter).getGuestAdressCountryCode();
      else if(jrf.getName().equals("guest_adress_country_name" )) return CheckinSource.get(counter).getGuestAdressCountryName();
      else if(jrf.getName().equals("guest_adress_currency_code" )) return CheckinSource.get(counter).getGuestAdressCurrencyCode();
      else if(jrf.getName().equals("guest_adress_currency_name" )) return CheckinSource.get(counter).getGuestAdressCurrencyName();
      else if(jrf.getName().equals("guest_adress_currency_symbol" )) return CheckinSource.get(counter).getGuestAdressCurrencySymbol();
      else if(jrf.getName().equals("guest_adress_language_code" )) return CheckinSource.get(counter).getGuestAdressLanguageCode();
      else if(jrf.getName().equals("guest_adress_language_name" )) return CheckinSource.get(counter).getGuestAdressLanguageName();
      else if(jrf.getName().equals("guest_adress_christianname" )) return CheckinSource.get(counter).getGuestAdressChristianname();
      else if(jrf.getName().equals("guest_adress_city" )) return CheckinSource.get(counter).getGuestAdressCity();
      else if(jrf.getName().equals("guest_adress_email" )) return CheckinSource.get(counter).getGuestAdressEmail();
      else if(jrf.getName().equals("guest_adress_name" )) return CheckinSource.get(counter).getGuestAdressName();
      else if(jrf.getName().equals("guest_adress_phone" )) return CheckinSource.get(counter).getGuestAdressPhone();
      else if(jrf.getName().equals("guest_adress_zipcode" )) return CheckinSource.get(counter).getGuestAdressZipcode();
      else if(jrf.getName().equals("guest_adress_street" )) return CheckinSource.get(counter).getLocationAddressStreet();
      else if(jrf.getName().equals("guest_adress_homepage" )) return CheckinSource.get(counter).getGuestAdressHomepage();
      else if(jrf.getName().equals("guest_adress_remarks" )) return CheckinSource.get(counter).getGuestAdressRemarks();
      else if(jrf.getName().equals("guest_adress_greeting" )) return CheckinSource.get(counter).getGuestAdressGreeting();
      else if(jrf.getName().equals("guest_adress_salutation" )) return CheckinSource.get(counter).getGuestAdressSalutation();
      else if(jrf.getName().equals("guest_adress_title" )) return CheckinSource.get(counter).getGuestAdressTitle();
      else if(jrf.getName().equals("guest_adress_addresstype" )) return CheckinSource.get(counter).getGuestAdressAddresstype();
      else if(jrf.getName().equals("Hotelname" )) return hbean.getHotelName();
        return "";
       
        
    }
    /*
    public static JRDataSource getDataSource(){
        return new addressDataSource();
    }
    */
}
