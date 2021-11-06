package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.base.Currency;
import org.jahap.entities.base.Language;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Country.class)
public class Country_ { 

    public static volatile SingularAttribute<Country, Language> contryLanguage;
    public static volatile SingularAttribute<Country, String> countryCode;
    public static volatile SingularAttribute<Country, Currency> countryCurrency;
    public static volatile SingularAttribute<Country, Integer> id;
    public static volatile SingularAttribute<Country, String> countryName;

}