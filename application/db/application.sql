/**
 * Postgres database
 */
create database application;


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

INSERT INTO users (user_id, user_name, password, user_about_you, user_country, user_gender, created_date, access, right_name) VALUES (1, 'admin', 'admin', NULL, NULL, NULL, NULL, 1, NULL);


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


CREATE SEQUENCE customer_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 12327
  CACHE 1;
ALTER TABLE customer_id_seq OWNER TO postgres;


CREATE SEQUENCE user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE user_id_seq OWNER TO postgres;



CREATE SEQUENCE menu_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE menu_id_seq OWNER TO postgres;

-- ===================================================
-- Menu for menubar
-- ==================================================
CREATE TABLE menu2
(
  menu_id bigint NOT NULL,
  menu_code character varying(20),
  parent_code character varying(30),
  menu_name character varying(30),
  menu_url character varying(124),
  menu_type smallint,
  system_menu smallint,
  menu_level smallint,
  CONSTRAINT menu2_pkey PRIMARY KEY (menu_id)
)
WITH (
  OIDS=FALSE
);

INSERT INTO menu2 (menu_id, menu_code, parent_code, menu_name, menu_url, menu_type, system_menu, menu_level, sequence) VALUES (1, '000ADMIN', NULL, 'MENU ADMIN', NULL, 1, 1, 1, 0);
INSERT INTO menu2 (menu_id, menu_code, parent_code, menu_name, menu_url, menu_type, system_menu, menu_level, sequence) VALUES (2, '001CUSTOMER', '000ADMIN', 'CUSTOMER', '/pages/master/customerList.xhtml', 2, 2, 2, 1);
INSERT INTO menu2 (menu_id, menu_code, parent_code, menu_name, menu_url, menu_type, system_menu, menu_level, sequence) VALUES (3, '002MENU', '000ADMIN', 'MENU', '/pages/setup/menuList.xhtml', 2, 2, 3, 3);



CREATE TABLE "role"
(
  role_id bigint NOT NULL,
  role_shortdescription character varying(30) NOT NULL,
  role_longdescription character varying(1000),
  "version" integer NOT NULL DEFAULT 0,
  CONSTRAINT pk_role PRIMARY KEY (role_id)
)
WITH (
  OIDS=FALSE
);

INSERT INTO role (role_id, role_shortdescription, role_longdescription, version) VALUES (1, 'ROLE_ADMIN', 'Role administrator', 0);
INSERT INTO role (role_id, role_shortdescription, role_longdescription, version) VALUES (2, 'ROLE_USER', 'Role User', 0);


CREATE TABLE role_menu
(
  role_menu_id bigint NOT NULL,
  role_id bigint NOT NULL,
  menu_id bigint NOT NULL,
  created_date timestamp without time zone,
  created_by character varying(24),
  CONSTRAINT pk_role_menu PRIMARY KEY (role_menu_id)
)
WITH (
  OIDS=TRUE
);

INSERT INTO role_menu (role_menu_id, role_id, menu_id, created_date, created_by) VALUES (1, 1, 1, NULL, 'admin');
INSERT INTO role_menu (role_menu_id, role_id, menu_id, created_date, created_by) VALUES (2, 1, 2, NULL, 'admin');
INSERT INTO role_menu (role_menu_id, role_id, menu_id, created_date, created_by) VALUES (3, 1, 3, NULL, 'admin');


CREATE TABLE user_role
(
  user_role_id bigint NOT NULL,
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  "version" integer NOT NULL DEFAULT 0,
  CONSTRAINT pk_user_role PRIMARY KEY (user_role_id),
  CONSTRAINT ref_to_role FOREIGN KEY (role_id)
      REFERENCES "role" (role_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT ref_to_usr FOREIGN KEY (user_id)
      REFERENCES users (user_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS=FALSE
);

INSERT INTO user_role (user_role_id, user_id, role_id, version) VALUES (1, 1, 1, 0);

