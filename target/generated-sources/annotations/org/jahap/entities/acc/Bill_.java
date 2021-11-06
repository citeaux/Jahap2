package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;
import java.util.Date;
import org.jahap.entities.acc.AccountPosition;
import org.jahap.entities.base.Address;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Bill.class)
public class Bill_ { 

    public static volatile SingularAttribute<Bill, String> billname;
    public static volatile SingularAttribute<Bill, Long> canceledbill;
    public static volatile SingularAttribute<Bill, Address> address;
    public static volatile SingularAttribute<Bill, String> billnostring;
    public static volatile SingularAttribute<Bill, Boolean> canceled;
    public static volatile SingularAttribute<Bill, Double> total;
    public static volatile SingularAttribute<Bill, Timestamp> billchange;
    public static volatile SingularAttribute<Bill, Boolean> temp_bill;
    public static volatile SingularAttribute<Bill, Date> billdate;
    public static volatile SingularAttribute<Bill, Long> id;
    public static volatile SingularAttribute<Bill, String> UUID;
    public static volatile SingularAttribute<Bill, Long> billno;
    public static volatile CollectionAttribute<Bill, AccountPosition> accountPositionCollection;

}