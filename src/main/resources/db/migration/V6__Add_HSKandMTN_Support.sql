CREATE TABLE maintenanceblock
(
   id       integer         NOT NULL,
   name     varchar(100),
   comment  varchar(200)
);

ALTER TABLE maintenanceblock
   ADD CONSTRAINT maintenanceblock_pkey
   PRIMARY KEY (id);

CREATE TABLE housekeepingblock
(
   id       integer         NOT NULL,
   name     varchar(100),
   comment  varchar(200)
);

ALTER TABLE housekeepingblock
   ADD CONSTRAINT housekeepingblock_pkey
   PRIMARY KEY (id);