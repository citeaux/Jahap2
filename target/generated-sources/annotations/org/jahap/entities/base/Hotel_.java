package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Country;
import org.jahap.entities.base.Currency;
import org.jahap.entities.base.Language;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(Hotel.class)
public class Hotel_ { 

    public static volatile SingularAttribute<Hotel, String> hoteldateformat;
    public static volatile SingularAttribute<Hotel, Currency> hotelCurrency;
    public static volatile SingularAttribute<Hotel, Country> hotelCountry;
    public static volatile SingularAttribute<Hotel, Language> hotelLanguage;
    public static volatile SingularAttribute<Hotel, String> hotelCode;
    public static volatile SingularAttribute<Hotel, String> hotelName;
    public static volatile SingularAttribute<Hotel, Address> hotelAdress;
    public static volatile SingularAttribute<Hotel, String> hotelFootertext;
    public static volatile SingularAttribute<Hotel, Date> operationdate;
    public static volatile SingularAttribute<Hotel, Integer> id;
    public static volatile SingularAttribute<Hotel, String> hotelNumberformat;
    public static volatile SingularAttribute<Hotel, String> hotelBankaccountdata1;
    public static volatile SingularAttribute<Hotel, String> hotelBankaccountdata2;

}