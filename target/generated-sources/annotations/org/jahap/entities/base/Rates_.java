package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.acc.AccountPosition;
import org.jahap.entities.acc.Csc;
import org.jahap.entities.acc.Revaccounts;
import org.jahap.entities.base.Vattype;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Rates.class)
public class Rates_ { 

    public static volatile SingularAttribute<Rates, Boolean> overnight;
    public static volatile CollectionAttribute<Rates, Csc> cscCollection;
    public static volatile SingularAttribute<Rates, String> code;
    public static volatile SingularAttribute<Rates, Double> price;
    public static volatile SingularAttribute<Rates, Revaccounts> revaccount;
    public static volatile SingularAttribute<Rates, String> name;
    public static volatile SingularAttribute<Rates, Long> id;
    public static volatile SingularAttribute<Rates, Vattype> vattype;
    public static volatile CollectionAttribute<Rates, AccountPosition> accountPositionCollection;

}