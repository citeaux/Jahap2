package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.acc.AccountPosition;
import org.jahap.entities.acc.Revaccounts;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Revenue.class)
public class Revenue_ { 

    public static volatile SingularAttribute<Revenue, Double> amount;
    public static volatile SingularAttribute<Revenue, AccountPosition> accountposition;
    public static volatile SingularAttribute<Revenue, Revaccounts> revaccount;
    public static volatile SingularAttribute<Revenue, Long> id;
    public static volatile SingularAttribute<Revenue, Boolean> debit;
    public static volatile SingularAttribute<Revenue, Date> revdate;

}