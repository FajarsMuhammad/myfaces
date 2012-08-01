create database samz;


-- User

CREATE TABLE users
(
  user_id bigint NOT NULL,
  user_name character varying(50),
  "password" character varying(50),
  user_about_you character varying(255),
  user_country character varying(50),
  user_gender character varying(10),
  created_date timestamp without time zone,
  "access" integer,
  right_name character varying(50),
  CONSTRAINT users_pkey PRIMARY KEY (user_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users OWNER TO postgres;

-- Menu
CREATE TABLE menu
(
  menu_id bigint NOT NULL,
  parent character varying(30),
  "name" character varying(30),
  url character varying(124),
  "type" character varying(30),
  kind character varying(10),
  right_name character varying(20),
  CONSTRAINT menu_pkey PRIMARY KEY (menu_id)
)
WITH (
  OIDS=FALSE
);


-- Customer
CREATE TABLE customer
(
  customer_id bigint NOT NULL,
  customer_name character varying(50),
  address character varying(50),
  created_date timestamp without time zone,
  created_by character varying(24),
  last_updated timestamp without time zone,
  last_updated_by character varying(24),
  deleted smallint,
  gender character varying(7),
  customer_code character varying(50),
  customer_grade character varying(5),
  term_of_payment integer,
  CONSTRAINT pk_customer PRIMARY KEY (customer_id)
)
WITH (
  OIDS=TRUE
);





