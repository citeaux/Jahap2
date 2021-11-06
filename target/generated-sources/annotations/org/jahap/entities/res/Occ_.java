package org.jahap.entities.res;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Rooms;
import org.jahap.entities.res.Housekeepingblock;
import org.jahap.entities.res.Maintenanceblock;
import org.jahap.entities.res.Res;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Occ.class)
public class Occ_ { 

    public static volatile SingularAttribute<Occ, Res> res;
    public static volatile SingularAttribute<Occ, Integer> pax;
    public static volatile SingularAttribute<Occ, Date> checkinTimestamp;
    public static volatile SingularAttribute<Occ, Date> departuredate;
    public static volatile SingularAttribute<Occ, Date> checkoutTimestamp;
    public static volatile SingularAttribute<Occ, Date> departuretime;
    public static volatile SingularAttribute<Occ, Rooms> room;
    public static volatile SingularAttribute<Occ, Maintenanceblock> maintenanceblock;
    public static volatile SingularAttribute<Occ, Date> arrivaldate;
    public static volatile SingularAttribute<Occ, Boolean> checkin;
    public static volatile SingularAttribute<Occ, Housekeepingblock> housekeepingblock;
    public static volatile SingularAttribute<Occ, Date> arrivaltime;
    public static volatile SingularAttribute<Occ, Address> guest;
    public static volatile SingularAttribute<Occ, Long> id;
    public static volatile SingularAttribute<Occ, Boolean> checkout;
    public static volatile SingularAttribute<Occ, Accounts> account;

}