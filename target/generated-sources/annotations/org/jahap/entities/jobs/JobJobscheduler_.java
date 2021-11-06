package org.jahap.entities.jobs;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.jahap.entities.jobs.Jobs;
import org.jahap.entities.jobs.Jobscheduler;

@Generated(value="EclipseLink-3.0.2.v20210716-re8d4b571c9", date="2021-10-23T10:51:59")
@StaticMetamodel(JobJobscheduler.class)
public class JobJobscheduler_ { 

    public static volatile SingularAttribute<JobJobscheduler, Jobs> idJob;
    public static volatile SingularAttribute<JobJobscheduler, Long> id;
    public static volatile SingularAttribute<JobJobscheduler, Integer> position;
    public static volatile SingularAttribute<JobJobscheduler, Jobscheduler> idJobscheduler;

}