package org.jahap.entities.res;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.base.Cat;
import org.jahap.entities.res.Occ;
import org.jahap.entities.res.Res;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(Occcat.class)
public class Occcat_ { 

    public static volatile SingularAttribute<Occcat, Date> arrivaldate;
    public static volatile SingularAttribute<Occcat, Res> res;
    public static volatile SingularAttribute<Occcat, Cat> cat;
    public static volatile SingularAttribute<Occcat, Date> departuredate;
    public static volatile SingularAttribute<Occcat, Long> id;
    public static volatile SingularAttribute<Occcat, Occ> occ;

}