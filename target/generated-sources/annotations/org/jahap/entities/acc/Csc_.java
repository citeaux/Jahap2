package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.base.Rates;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Csc.class)
public class Csc_ { 

    public static volatile SingularAttribute<Csc, Integer> amount;
    public static volatile SingularAttribute<Csc, Date> todate;
    public static volatile SingularAttribute<Csc, Rates> rate;
    public static volatile SingularAttribute<Csc, String> service;
    public static volatile SingularAttribute<Csc, Double> price;
    public static volatile SingularAttribute<Csc, Long> id;
    public static volatile SingularAttribute<Csc, Accounts> account;
    public static volatile SingularAttribute<Csc, Date> fromdate;

}