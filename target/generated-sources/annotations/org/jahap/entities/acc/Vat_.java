package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.acc.AccountPosition;
import org.jahap.entities.base.Vattype;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(Vat.class)
public class Vat_ { 

    public static volatile SingularAttribute<Vat, Date> date;
    public static volatile SingularAttribute<Vat, Double> amount;
    public static volatile SingularAttribute<Vat, AccountPosition> accountposition;
    public static volatile SingularAttribute<Vat, Long> id;
    public static volatile SingularAttribute<Vat, Boolean> debit;
    public static volatile SingularAttribute<Vat, Vattype> vattype;

}