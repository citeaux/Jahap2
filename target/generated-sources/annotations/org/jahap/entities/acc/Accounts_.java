package org.jahap.entities.acc;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.acc.AccountPosition;
import org.jahap.entities.acc.Csc;
import org.jahap.entities.base.Address;
import org.jahap.entities.res.Res;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Accounts.class)
public class Accounts_ { 

    public static volatile CollectionAttribute<Accounts, Csc> cscCollection;
    public static volatile SingularAttribute<Accounts, Date> checkoutdate;
    public static volatile SingularAttribute<Accounts, Boolean> checkin;
    public static volatile SingularAttribute<Accounts, Address> address;
    public static volatile SingularAttribute<Accounts, Double> balance;
    public static volatile SingularAttribute<Accounts, Date> checkindate;
    public static volatile SingularAttribute<Accounts, Res> reservation;
    public static volatile SingularAttribute<Accounts, Long> id;
    public static volatile SingularAttribute<Accounts, String> state;
    public static volatile SingularAttribute<Accounts, Boolean> checkout;
    public static volatile CollectionAttribute<Accounts, AccountPosition> accountPositionCollection;

}