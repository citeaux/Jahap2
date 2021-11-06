package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.acc.Payed;
import org.jahap.entities.acc.Revenue;
import org.jahap.entities.acc.Vat;
import org.jahap.entities.base.Rates;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(AccountPosition.class)
public class AccountPosition_ { 

    public static volatile SingularAttribute<AccountPosition, Integer> amount;
    public static volatile SingularAttribute<AccountPosition, String> positionname;
    public static volatile SingularAttribute<AccountPosition, Long> bill;
    public static volatile CollectionAttribute<AccountPosition, Revenue> revenueCollection;
    public static volatile SingularAttribute<AccountPosition, Boolean> canceled;
    public static volatile SingularAttribute<AccountPosition, Boolean> billed;
    public static volatile SingularAttribute<AccountPosition, Rates> rate;
    public static volatile SingularAttribute<AccountPosition, Double> price;
    public static volatile CollectionAttribute<AccountPosition, Vat> vatCollection;
    public static volatile SingularAttribute<AccountPosition, Long> id;
    public static volatile SingularAttribute<AccountPosition, Boolean> debit;
    public static volatile SingularAttribute<AccountPosition, Date> ratedate;
    public static volatile SingularAttribute<AccountPosition, Accounts> account;
    public static volatile SingularAttribute<AccountPosition, Long> canceledposition;
    public static volatile SingularAttribute<AccountPosition, Payed> payed;

}