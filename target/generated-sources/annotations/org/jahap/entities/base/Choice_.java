package org.jahap.entities.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import org.jahap.entities.base.Language;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(Choice.class)
public class Choice_ { 

    public static volatile SingularAttribute<Choice, Integer> groupid;
    public static volatile SingularAttribute<Choice, String> choicetext;
    public static volatile SingularAttribute<Choice, Integer> choiceint;
    public static volatile SingularAttribute<Choice, BigDecimal> choicefloat;
    public static volatile SingularAttribute<Choice, Language> language;
    public static volatile SingularAttribute<Choice, Long> id;
    public static volatile SingularAttribute<Choice, String> choicecode;
    public static volatile SingularAttribute<Choice, String> groupname;
    public static volatile SingularAttribute<Choice, String> groupcode;

}