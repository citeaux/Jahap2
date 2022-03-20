package org.jahap.entities.jobs;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.jobs.JobJobscheduler;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-12-05T10:32:46")
@StaticMetamodel(Jobs.class)
public class Jobs_ { 

    public static volatile CollectionAttribute<Jobs, JobJobscheduler> jobJobschedulerCollection;
    public static volatile SingularAttribute<Jobs, String> name;
    public static volatile SingularAttribute<Jobs, String> definition;
    public static volatile SingularAttribute<Jobs, Long> id;
    public static volatile SingularAttribute<Jobs, String> type;

}