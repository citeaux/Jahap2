package org.jahap.entities.res;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.ResState;
import org.jahap.entities.res.Occ;
import org.jahap.entities.res.Res_No;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(Res.class)
public class Res_ { 

    public static volatile SingularAttribute<Res, Occ> occid;
    public static volatile SingularAttribute<Res, Date> arrivaldate;
    public static volatile SingularAttribute<Res, Date> optiondate;
    public static volatile SingularAttribute<Res, Res_No> resnointern;
    public static volatile SingularAttribute<Res, String> resno;
    public static volatile SingularAttribute<Res, Date> arrivaltime;
    public static volatile SingularAttribute<Res, Date> departuredate;
    public static volatile SingularAttribute<Res, String> comment;
    public static volatile SingularAttribute<Res, Long> id;
    public static volatile SingularAttribute<Res, ResState> state;
    public static volatile SingularAttribute<Res, Date> departuretime;
    public static volatile SingularAttribute<Res, Address> addressid;

}