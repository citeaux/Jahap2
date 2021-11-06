
-- Imports Report into DB
-- POSTGRESQL: the files have to be placed in the DATA folder

ALTER TABLE revaccounts
    ADD rev_group VARCHAR(50);


INSERT INTO "public".choice (id, groupid, groupcode, groupname, choicecode, choicetext, choiceint, choicefloat, "language") 
	VALUES (9, 5, 'REVGR', 'revenuegroup', 'FB', 'Food and Beverage', NULL, NULL, 1),
	VALUES (10, 5, 'REVGR', 'revenuegroup', 'LG', 'Logis', NULL, NULL, 1);
