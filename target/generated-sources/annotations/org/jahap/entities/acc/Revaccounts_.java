package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.acc.Revenue;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Revaccounts.class)
public class Revaccounts_ { 

    public static volatile SingularAttribute<Revaccounts, Long> revaccnumber;
    public static volatile SingularAttribute<Revaccounts, String> name;
    public static volatile SingularAttribute<Revaccounts, Long> id;
    public static volatile CollectionAttribute<Revaccounts, Revenue> revenueCollection;
    public static volatile SingularAttribute<Revaccounts, String> rev_group;

}