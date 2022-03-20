package org.jahap.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigInteger;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(LogAccounting.class)
public class LogAccounting_ { 

    public static volatile SingularAttribute<LogAccounting, String> date;
    public static volatile SingularAttribute<LogAccounting, Integer> amount;
    public static volatile SingularAttribute<LogAccounting, String> positionname;
    public static volatile SingularAttribute<LogAccounting, BigInteger> accountPosition;
    public static volatile SingularAttribute<LogAccounting, Long> id;
    public static volatile SingularAttribute<LogAccounting, String> time;
    public static volatile SingularAttribute<LogAccounting, String> account;

}