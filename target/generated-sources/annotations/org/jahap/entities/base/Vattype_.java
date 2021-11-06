package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.acc.Vat;
import org.jahap.entities.base.Rates;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Vattype.class)
public class Vattype_ { 

    public static volatile SingularAttribute<Vattype, Float> percentage;
    public static volatile CollectionAttribute<Vattype, Rates> rates;
    public static volatile SingularAttribute<Vattype, String> name;
    public static volatile CollectionAttribute<Vattype, Vat> vatCollection;
    public static volatile SingularAttribute<Vattype, Long> id;
    public static volatile SingularAttribute<Vattype, Date> datevat;

}