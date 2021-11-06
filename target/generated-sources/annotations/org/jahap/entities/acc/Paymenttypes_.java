package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.acc.Payed;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Paymenttypes.class)
public class Paymenttypes_ { 

    public static volatile SingularAttribute<Paymenttypes, String> name;
    public static volatile SingularAttribute<Paymenttypes, Boolean> receivable;
    public static volatile SingularAttribute<Paymenttypes, Long> id;
    public static volatile CollectionAttribute<Paymenttypes, Payed> payedCollection;

}