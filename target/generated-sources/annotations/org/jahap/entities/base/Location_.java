package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, Short> id;
    public static volatile SingularAttribute<Location, String> floor;
    public static volatile SingularAttribute<Location, String> building;
    public static volatile SingularAttribute<Location, Long> addressId;

}