package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.acc.Paymenttypes;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Payed.class)
public class Payed_ { 

    public static volatile SingularAttribute<Payed, Boolean> canceled;
    public static volatile SingularAttribute<Payed, Double> total;
    public static volatile SingularAttribute<Payed, Long> canceledpayment;
    public static volatile SingularAttribute<Payed, Long> id;
    public static volatile SingularAttribute<Payed, Boolean> debit;
    public static volatile SingularAttribute<Payed, Paymenttypes> paymenttype;
    public static volatile SingularAttribute<Payed, Boolean> openpos;

}