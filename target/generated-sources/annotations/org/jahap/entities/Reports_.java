package org.jahap.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.base.Language;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(Reports.class)
public class Reports_ { 

    public static volatile SingularAttribute<Reports, String> reportGroup;
    public static volatile SingularAttribute<Reports, String> reportLayout;
    public static volatile SingularAttribute<Reports, Boolean> standardreport;
    public static volatile SingularAttribute<Reports, String> name;
    public static volatile SingularAttribute<Reports, byte[]> report;
    public static volatile SingularAttribute<Reports, String> description;
    public static volatile SingularAttribute<Reports, Language> language;
    public static volatile SingularAttribute<Reports, Integer> id;

}