-- Table: job_jobscheduler

-- DROP TABLE job_jobscheduler;

CREATE TABLE job_jobscheduler
(
  id_job bigint NOT NULL,
  id_jobscheduler bigint NOT NULL,
  id bigint NOT NULL,
  "position" integer,
  CONSTRAINT job_jobscheduler_pkey PRIMARY KEY (id),
  CONSTRAINT job FOREIGN KEY (id_job)
      REFERENCES jobs (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT jobscheduler FOREIGN KEY (id_jobscheduler)
      REFERENCES jobscheduler (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_jobscheduler
  OWNER TO postgres;

-- Index: fki_job

-- DROP INDEX fki_job;

CREATE INDEX fki_job
  ON job_jobscheduler
  USING btree
  (id_job);

-- Index: fki_jobscheduler

-- DROP INDEX fki_jobscheduler;

CREATE INDEX fki_jobscheduler
  ON job_jobscheduler
  USING btree
  (id_jobscheduler);

-- *****************************
-- Table: jobs

-- DROP TABLE jobs;

CREATE TABLE jobs
(
  id bigint NOT NULL,
  type character(50),
  name character(100),
  definition text,
  CONSTRAINT jobs_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE jobs
  OWNER TO postgres;


-- Table: job_jobscheduler

-- DROP TABLE job_jobscheduler;

CREATE TABLE job_jobscheduler
(
  id_job bigint NOT NULL,
  id_jobscheduler bigint NOT NULL,
  id bigint NOT NULL,
  "position" integer,
  CONSTRAINT job_jobscheduler_pkey PRIMARY KEY (id),
  CONSTRAINT job FOREIGN KEY (id_job)
      REFERENCES jobs (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT jobscheduler FOREIGN KEY (id_jobscheduler)
      REFERENCES jobscheduler (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE job_jobscheduler
  OWNER TO postgres;

-- Index: fki_job

-- DROP INDEX fki_job;

CREATE INDEX fki_job
  ON job_jobscheduler
  USING btree
  (id_job);

-- Index: fki_jobscheduler

-- DROP INDEX fki_jobscheduler;

CREATE INDEX fki_jobscheduler
  ON job_jobscheduler
  USING btree
  (id_jobscheduler);



CREATE OR REPLACE VIEW dayclose AS 
 SELECT jobs.type,
    jobs.name,
    job_jobscheduler."position",
    job_jobscheduler.id_jobscheduler,
    job_jobscheduler.id_job,
    job_jobscheduler.id,
    jobs.definition
   FROM job_jobscheduler,
    jobs,
    jobscheduler
  WHERE job_jobscheduler.id_job = jobs.id AND job_jobscheduler.id_jobscheduler = jobscheduler.id AND jobscheduler.typ = 'dayclose'::bpchar
  ORDER BY job_jobscheduler."position";

ALTER TABLE dayclose
  OWNER TO "JAHAP";