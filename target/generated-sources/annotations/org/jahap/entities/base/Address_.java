package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.acc.Bill;
import org.jahap.entities.base.Country;
import org.jahap.entities.base.Currency;
import org.jahap.entities.base.Language;
import org.jahap.entities.res.Res;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Country> country;
    public static volatile SingularAttribute<Address, String> Greeting;
    public static volatile SingularAttribute<Address, String> Homepage;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, Language> language;
    public static volatile CollectionAttribute<Address, Res> resCollection;
    public static volatile SingularAttribute<Address, String> christianname;
    public static volatile SingularAttribute<Address, String> Salutation;
    public static volatile SingularAttribute<Address, String> Addresstype;
    public static volatile SingularAttribute<Address, String> zipcode;
    public static volatile SingularAttribute<Address, String> phone;
    public static volatile SingularAttribute<Address, String> Remarks;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, String> Titel;
    public static volatile SingularAttribute<Address, String> name;
    public static volatile SingularAttribute<Address, Currency> currency;
    public static volatile SingularAttribute<Address, Long> id;
    public static volatile CollectionAttribute<Address, Bill> billCollection;
    public static volatile SingularAttribute<Address, String> email;

}