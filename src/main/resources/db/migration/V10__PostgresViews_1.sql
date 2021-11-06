
CREATE VIEW housekeeping
(
  id,
  code,
  cat_name,
  floor,
  clean,
  blocks
)
AS 
  SELECT rooms.id,
    rooms.code,
    cat.cat_name,
    location.floor,
    rooms.clean,
    string_agg(concat(to_char((occ.arrivaldate)::timestamp with time zone, 'DD.MM.YYYY'::text), '-', to_char((occ.departuredate)::timestamp with time zone, 'DD.MM.YYYY'::text)), ' / '::text) AS blocks
   FROM location,
    cat,
    rooms
     LEFT JOIN occ ON rooms.id = occ.room AND occ.arrivaldate >= 'now'::text::date
  WHERE rooms.location = location.id AND rooms.cat = cat.id 
  GROUP BY rooms.code, rooms.id, location.floor, cat.cat_name;


GRANT REFERENCES, INSERT, TRIGGER, UPDATE, SELECT, DELETE, TRUNCATE ON housekeeping TO postgres;

COMMENT ON VIEW housekeeping IS 'Shows all Rooms with Housekeepingblocks form current date to the futur';

CREATE VIEW Maintenance
(
  id,
  code,
  cat_name,
  floor,
  no_maintenance,
  blocks
)
AS
SELECT rooms.id,
    rooms.code,
    cat.cat_name,
    location.floor,
    rooms.no_maintenance,
    string_agg(concat(to_char(occ.arrivaldate::timestamp with time zone, 'DD.MM.YYYY'::text), '-', to_char(occ.departuredate::timestamp with time zone, 'DD.MM.YYYY'::text)), ' / '::text) AS blocks
   FROM location,
    cat,
    rooms
     LEFT JOIN occ ON rooms.id = occ.room AND occ.arrivaldate >= 'now'::text::date AND occ.maintenance IS NOT NULL
  WHERE rooms.location = location.id AND rooms.cat = cat.id
  GROUP BY rooms.code, rooms.id, location.floor, cat.cat_name;

GRANT REFERENCES, INSERT, TRIGGER, UPDATE, SELECT, DELETE, TRUNCATE ON Maintenance TO postgres;


CREATE OR REPLACE VIEW reservations AS 
  SELECT orderer.christianname AS orderer_christianname,
    orderer.city AS orderer_city,
    orderer.email AS orderer_email,
    orderer.name AS orderer_name,
    orderer.phone AS orderer_phone,
    orderer.street AS orderer_street,
    orderer.zipcode AS orderer_zipcode,
    orderer.greeting AS orderer_greeting,
    orderer.salutation AS orderer_salutation,
    orderer.title AS orderer_title,
    orderer.remarks AS orderer_remarks,
    orderer_language.language_code AS orderer_language_code,
    orderer_language.language_name AS orderer_language_name,
    guest.christianname AS guest_christianname,
    guest.city AS guest_city,
    guest.email AS guest_email,
    guest.name AS guest_name,
    guest.phone AS guest_phone,
    guest.street AS guest_street,
    guest.zipcode AS guest_zipcode,
    guest.greeting AS guest_greeting,
    guest.salutation AS guest_salutation,
    guest.title AS guest_title,
    guest.remarks AS guest_remarks,
    guest_language.language_code AS guest_language_code,
    guest_language.language_name AS guest_language_name,
    services.fromdate AS services_fromdate,
    services.todate AS services_todate,
    services.price AS services_price,
    services.amount AS services_amount,
    rooms.name,
    rooms.code,
    res.resno,
    res.departuredate,
    cat.cat_name,
    cat.cat_description,
    res.arrivaldate,
    hotel.hotel_name,
    hotel.hotel_code,
    hotel.hotel_bankaccountdata1,
    hotel.hotel_bankaccountdata2,
    hotel.hotel_footertext,
    occ.id
   FROM accounts
     LEFT JOIN csc services ON accounts.cscservice = services.id,
    occ
     LEFT JOIN address guest ON occ.guest = guest.id
     LEFT JOIN language guest_language ON guest.language = guest_language.id,
    res
     LEFT JOIN address orderer ON res.addressid = orderer.id
     LEFT JOIN language orderer_language ON orderer.language = orderer_language.id,
    rooms
     LEFT JOIN cat ON rooms.cat = cat.id,
    hotel
  WHERE occ.room = rooms.id AND res.id = occ.res AND occ.account = accounts.id;

ALTER TABLE reservations
  OWNER TO postgres;
