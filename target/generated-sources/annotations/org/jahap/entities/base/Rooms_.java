package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.base.Cat;
import org.jahap.entities.base.Location;
import org.jahap.entities.res.Occ;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Rooms.class)
public class Rooms_ { 

    public static volatile SingularAttribute<Rooms, Boolean> no_maintenance;
    public static volatile SingularAttribute<Rooms, String> code;
    public static volatile CollectionAttribute<Rooms, Occ> occCollection;
    public static volatile SingularAttribute<Rooms, Byte> pax;
    public static volatile SingularAttribute<Rooms, String> name;
    public static volatile SingularAttribute<Rooms, Location> location;
    public static volatile SingularAttribute<Rooms, Long> id;
    public static volatile SingularAttribute<Rooms, Cat> category;
    public static volatile SingularAttribute<Rooms, Boolean> clean;

}