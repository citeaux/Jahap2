
--checked
INSERT INTO "public".address (id, christianname, city, email, "name", phone, street, zipcode, country, currency, "language", homepage, addresstype, remarks, greeting, salutation, title) 
	VALUES (1, 'Peter', 'Albital', NULL, 'Dillinger', '07866673', 'Hollertszug 4', '85454', 1, 1, 1, 'privat', 1, NULL, 'Mr', 'Dear Mr', NULL),
               (2, 'Hildegard', 'Nachigental', NULL, 'Walburger', '054544', 'Am Kassau 33', '24445', 2, 2, 2, 'company', 2, NULL, 'Mdme', 'Dear Mdme', NULL);
            
--checked
INSERT INTO ROOMS (ID,CATEGORY,CODE,NAME)
       VALUES(1,'Single','101','Garden Room'),
             (2,'Double','102','Victorian Room');
--checked
INSERT INTO RES (ID,ARRIVALDATE,DEPARTUREDATE,RESNO,ADDRESSID)
        VALUES(1,'10.07.2013','11.07.2013','1',1),
              (2,'01.12.2013','02.12.2013','2',2);

-- checked
INSERT INTO VATTYPE(ID,NAME,DATEVAT,PERCENTAGE)
        VALUES(1,'No VAT','2013-12-15',0),
              (2,'Full','2013-12-15',19),
              (3,'Half','2013-12-15',7);
--checked
INSERT INTO REVACCOUNTS (ID,REVACCOUNTNUMBER, NAME)
     VALUES(1,200000,'Logis'),(2,300000,'Food and Beverage');

--checked
INSERT INTO RATES (ID,NAME,PRICE,CODE,OVERNIGHT,REVACCOUNT,NET,VATTYPE)
         VALUES(1,'Logis Single Room',50,'LSR',TRUE,1,FALSE,2),
               (2,'Breakfast',12.80,'BKF',FALSE,2,FALSE,2);

--checked
INSERT INTO ACCOUNTS (ID, BALANCE, CHECKOUT, CHECKINDATE, CHECKOUTDATE,CSCSERVICE,ADDRESS,RESERVATION) 
     VALUES (1, NULL, NULL, '11.07.2013', NULL,1,1,1),
            (2, NULL, NULL, '05.07.2013', NULL,1,2,2);
--checked

INSERT INTO VAT(ID,AMOUNT,DEBIT,DATE,VATTYPE,ACCOUNTPOSITION)
        VALUES(1,7.10,FALSE,'2013-08-05',2,2),
              (2,7.10,FALSE,'2013-08-05',2,2),
              (3,11.56,FALSE,'2013-08-05',2,1),
              (4,3.72,FALSE,'2013-08-05',2,1);


--checked
INSERT INTO PAYMENTTYPES(ID,NAME,RECEIVABLE)
        VALUES(1,'CASH',FALSE),
              (2,'CREDITCARD',TRUE);
              

--checked
INSERT INTO PAYED(ID,DEBIT,PAYMENTTYPE,TOTAL,OPENPOS)
       VALUES(1,FALSE,1,88.88,FALSE);

--checked
INSERT INTO ACCOUNT_POSITION (ID, BILLED, AMOUNT,PRICE, DEBIT, RATE, RATEDATE, ACCOUNT, POSITIONNAME,CANCELED, CANCELEDPOSITION,BILL,VAT,PAYMENT) 
      VALUES (1, TRUE,1, 44.44, FALSE, 1, '2013-08-05', 1, 'xTEXT2',TRUE,1,1,1,1),
             (2, TRUE,1, 44.44, TRUE, 1, '2013-08-05', 1, 'xTEXT2',FALSE,1,1,2,1),
             (3, FALSE,1, 72.44, FALSE, 1, '2013-08-05', 1, 'xTEXT4',FALSE,0,NULL,3,NULL),  
             (4, FALSE,1, 23.33, FALSE, 1, '2013-08-05', 2, 'xTEXT6',FALSE,0,NULL,4,NULL);
--checked
INSERT INTO BILL(ID,BILLNO,ADDRESS,BILLDATE,BILLNAME,CANCELED,CANCELEDBILL,TOTAL)
       VALUES (1,1,1,'2013-08-05','Testbill',FALSE,0,88.88);


--checked
INSERT INTO REVENUE (ID, DEBIT, ACCOUNTPOSITION, REVACCOUNT, AMOUNT,REVDATE) 
       VALUES (1, FALSE, 1, 1, 37.34,'2013-08-05'),
              (2, TRUE, 2, 1, 37.34,'2013-08-05'),
              (3, FALSE, 3, 1, 60.88,'2013-08-05'),   
              (4, FALSE, 4, 1, 19.62,'2013-08-05');
--checked
INSERT INTO OCC (ID, ARRIVALDATE,DEPARTUREDATE,GUEST, ROOM,RES, ACCOUNT)
       VALUES (1,'2013-12-01','2013-12-05',1,1,1,1),
              (2,'2013-12-05','2013-12-10',2,2,2,2);
--checked
INSERT INTO CSC (ID, RATE, FROMDATE, TODATE, ACCOUNT,AMOUNT,PRICE,SERVICE)
	VALUES(1,1,'2013-12-01','2013-12-05',1,4,45.56,1),
	      (2,1,'2013-12-05','2013-12-10',2,3,70.89,1);

INSERT INTO "public".currency (id, currency_code, currency_name, currency_symbol) 
	VALUES (1, 'DOLLAR', 'Dollar', '$'),
               (2, 'EURO', 'Euro', '€'),
               (3, 'POUND', 'British Pound', '£');

INSERT INTO "public"."language" (id, language_code, language_name) 
	VALUES(1, 'EN', 'English'),
              (2, 'FR', 'French'),
              (3, 'DE', 'German');

INSERT INTO "public".country (id, country_code, country_name, "language", currency) 
	VALUES (1, 'USA', 'USA', 1, 1),
               (2, 'UK', 'UK', 1, 3),
               (3, 'FR', 'France', 2, 2),
               (4, 'DE', 'Germany', 3, 2);

INSERT INTO "public".choice (id, groupid, groupcode, groupname, choicecode, choicetext, choiceint, choicefloat, "language") 
	VALUES (1, 1, 'GREET', 'greeting', 'Mr', 'Mr', NULL, NULL, 1),
	 (2, 1, 'GREET', 'greeting', 'Mrs', 'Mrs', NULL, NULL, 1),
	 (3, 2, 'SALUT', 'salutation', 'DMr', 'Dear Mr', NULL, NULL, 1),
	 (4, 2, 'SALUT', 'salutation', 'DMrs', 'Dear Mrs', NULL, NULL, 1),
	 (5, 3, 'TITLE', 'title', 'DR', 'Dr.', NULL, NULL, 1),
	 (6, 3, 'TITLE', 'title', 'DPL', 'Dplm. Ing.', NULL, NULL, 1),
	 (7, 4, 'ATYPE', 'addresstype', 'PR', 'privat', NULL, NULL, 1),
	 (8, 4, 'ATYPE', 'addresstype', 'PR', 'company', NULL, NULL, 1);
         (9, 5, 'REVGR', 'revenuegroup', 'FB', 'Food and Beverage', NULL, NULL, 1);
         (10, 5, 'REVGR', 'revenuegroup', 'LG', 'Logis', NULL, NULL, 1);
         (11, 6, 'DATEF', 'dateformat', 'dmy', 'dd.mm.yyyy', NULL, NULL, 1);
         (12, 6, 'DATEF', 'dateformat', 'dmy1', 'dd/mm/yyyy', NULL, NULL, 1);
         (13, 6, 'DATEF', 'dateformat', 'mdy', 'mm/dd/yyyy', NULL, NULL, 1);
         (14, 6, 'DATEF', 'dateformat', 'mdy1', 'mm-dd-yyyy', NULL, NULL, 1);
         (15, 6, 'DATEF', 'dateformat', 'ymd', 'yyyyy.mm.dd', NULL, NULL, 1);
         (16, 7, 'NUMF', 'numberformat', 'enf', '# ###,00', NULL, NULL, 1);
         (17, 7, 'NUMF', 'numberformat', 'enf1', '#.###,00', NULL, NULL, 1);
         (18, 7, 'NUMF', 'numberformat', 'enf2', '#''###,00', NULL, NULL, 1);
         (19, 7, 'NUMF', 'numberformat', 'unf', '#,###.00', NULL, NULL, 1);
         (20, 7, 'NUMF', 'numberformat', 'unf2', '#''###.00', NULL, NULL, 1);
