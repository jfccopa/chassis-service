-- CREATE SCHEMA IF NOT EXISTS three_track_db;

-- \c three_track_db;

CREATE  TABLE ad_function ( 
	i_function_id        serial  NOT NULL ,
	v_name               varchar(64)   ,
	v_description        varchar(32)   ,
	v_icon               varchar(32)   ,
	i_order              integer   ,
	c_state              char(1)  NOT NULL ,
	t_create_date        timestamp   ,
	i_create_id          integer  NOT NULL ,
	t_update_date        timestamp   ,
	i_update_id          integer   ,
	b_deleted            boolean   ,
	CONSTRAINT pk_ad_menu_i_id PRIMARY KEY ( i_function_id )
 );

CREATE  TABLE ad_organization ( 
	i_organization_id    serial  NOT NULL ,
	v_name               varchar(64)   ,
	v_description        varchar(64)   ,
	c_state              char(1)   ,
	t_create_date        timestamp  NOT NULL ,
	i_create_id          integer  NOT NULL ,
	t_update_date        timestamp   ,
	i_update_id          integer   ,
	b_deleted            boolean DEFAULT false NOT NULL ,
	CONSTRAINT pk_ad_organization_i_id PRIMARY KEY ( i_organization_id )
 );

CREATE  TABLE ad_role ( 
	i_role_id            serial  NOT NULL ,
	v_name               varchar(32)  NOT NULL ,
	ad_code              varchar(8)   ,
	c_state              char(1)  NOT NULL ,
	t_create_date        timestamp  NOT NULL ,
	i_create_id          integer  NOT NULL ,
	t_update_date        timestamp   ,
	i_update_id          integer   ,
	b_deleted            boolean DEFAULT false  ,
	CONSTRAINT pk_administration_i_id PRIMARY KEY ( i_role_id )
 );

CREATE  TABLE ad_role_function ( 
	i_role_id            integer   ,
	i_function_id        integer   
 );

CREATE  TABLE ad_user ( 
	i_user_id            serial  NOT NULL ,
	v_name               varchar(32)  NOT NULL ,
	v_password           varchar(64)  NOT NULL ,
	c_state              char(1)  NOT NULL ,
	i_person_id          integer   ,
	i_organization_id    integer   ,
	t_create_date        timestamp  NOT NULL ,
	i_create_id          integer  NOT NULL ,
	t_update_date        timestamp   ,
	i_update_id          integer   ,
	b_deleted            boolean   ,
	CONSTRAINT pk_ad_user_i_id PRIMARY KEY ( i_user_id )
 );

CREATE  TABLE ad_user_function ( 
	i_user_id            integer   ,
	i_function_id        integer   
 );

CREATE  TABLE ad_login ( 
	i_login_id           serial  NOT NULL ,
	i_user_id            integer NOT NULL ,
	t_login_date         timestamp NOT NULL ,
	t_logout_date        timestamp,
	CONSTRAINT pk_ad_login_i_id PRIMARY KEY ( i_login_id )
 );

CREATE  TABLE de_driver ( 
	i_driver_id          serial  NOT NULL ,
	v_name               varchar(100)   ,
	v_vehicle_type       varchar   ,
	p_location_coords    point   ,
	v_phone_number       varchar   ,
	t_create_date       date   ,
	i_create_id         integer   ,
	t_update_date       date   ,
	i_update_id         integer   ,
	b_deleted            boolean   ,
	CONSTRAINT pk_de_driver_i_driver_id PRIMARY KEY ( i_driver_id )
 );

CREATE  TABLE de_incidence ( 
	i_incidence_id       integer  NOT NULL ,
	v_name               varchar(100)   ,
	CONSTRAINT pk_de_incidence_i_incidence_id PRIMARY KEY ( i_incidence_id )
 );

CREATE  TABLE de_status_route ( 
	i_statusroute_id     serial  NOT NULL ,
	v_status_name        varchar(100)   ,
	t_create_date       date   ,
	i_create_id         integer   ,
	t_update_date       date   ,
	i_update_id         integer   ,
	b_deleted            boolean   ,
	CONSTRAINT pk_de_status_route_i_statusroute_id PRIMARY KEY ( i_statusroute_id )
 );

CREATE  TABLE se_product ( 
	i_product_id         serial  NOT NULL ,
	v_name               varchar(512)  NOT NULL ,
	v_description        varchar(2048)  NOT NULL ,
	v_serial_number      varchar(32)   ,
	i_quantity           integer DEFAULT 0 NOT NULL ,
	d_price              decimal(9,2)   ,
	d_weight             decimal(9,3)   ,
	d_volume             decimal(9,3)   ,
	c_state              char(1) DEFAULT 'A' NOT NULL ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	i_organization_id    integer  NOT NULL ,
	CONSTRAINT pk_se_product_iproductid PRIMARY KEY ( i_product_id )
 );

COMMENT ON TABLE se_product IS 'Seller customer';

COMMENT ON COLUMN se_product.i_product_id IS 'Product identifier';

COMMENT ON COLUMN se_product.v_name IS 'Name of the product';

COMMENT ON COLUMN se_product.v_description IS 'Description of the product';

COMMENT ON COLUMN se_product.v_serial_number IS 'Serial number of the product';

COMMENT ON COLUMN se_product.i_quantity IS 'Quantity of products';

COMMENT ON COLUMN se_product.d_price IS 'Price of the product';

COMMENT ON COLUMN se_product.d_weight IS 'Weight of the product';

COMMENT ON COLUMN se_product.d_volume IS 'Volume of the product';

COMMENT ON COLUMN se_product.c_state IS 'Estate of register';

COMMENT ON COLUMN se_product.t_update_date IS 'Updated date';

COMMENT ON COLUMN se_product.i_update_id IS 'Update by ID';

CREATE  TABLE ad_action ( 
	i_action_id          serial  NOT NULL ,
	v_name               varchar(32)   ,
	i_function_id        integer   ,
	c_state              char(1)  NOT NULL ,
	t_create_date        timestamp  NOT NULL ,
	i_create_id          integer  NOT NULL ,
	t_update_date        timestamp   ,
	i_update_id          integer   ,
	b_deleted            boolean   ,
	CONSTRAINT pk_ad_action_i_id PRIMARY KEY ( i_action_id )
 );

CREATE  TABLE bu_order ( 
	i_order_id           serial  NOT NULL ,
	v_number             text   ,
	d_order_date         date   ,
	d_order_shipdate     date   ,
	d_requireddate       date   ,
	i_driver_id          integer   ,
	i_customer_id        integer   ,
	i_status_order_id    integer   ,
	i_address_id         integer  NOT NULL ,
	d_subtotal_price     decimal(9,2)  NOT NULL ,
	d_igv                decimal(9,2)  NOT NULL ,
	d_total_price        decimal(9,2)  NOT NULL ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	i_organization_id    integer  NOT NULL ,
	CONSTRAINT pk_tbl_orderid PRIMARY KEY ( i_order_id ),
	CONSTRAINT unq_order_status_order_id UNIQUE ( i_status_order_id ) ,
	CONSTRAINT unq_order_n_address_id UNIQUE ( i_address_id ) 
 );

COMMENT ON TABLE bu_order IS 'Master table for orders in 3Track';

COMMENT ON COLUMN bu_order.i_order_id IS 'Id Order';

COMMENT ON COLUMN bu_order.v_number IS 'Number signed to order';

COMMENT ON COLUMN bu_order.d_order_date IS 'date of creation';

COMMENT ON COLUMN bu_order.d_requireddate IS 'date aprox for deliver';

COMMENT ON COLUMN bu_order.i_driver_id IS 'freight assigned to deliver the order';

COMMENT ON COLUMN bu_order.i_customer_id IS 'customer order';

CREATE  TABLE bu_order_detail ( 
	i_orderdetail_id     integer  NOT NULL ,
	i_order_id           integer   ,
	i_product_id         integer   ,
	f_unit_price         decimal   ,
	i_quantity           integer   ,
	d_total_price        decimal(9,2)  NOT NULL ,
	i_discount           smallint   ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	i_organization_id    integer   ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	CONSTRAINT pk_order_detail_order_detail_id PRIMARY KEY ( i_orderdetail_id )
 );

COMMENT ON TABLE bu_order_detail IS 'detail of order';

COMMENT ON COLUMN bu_order_detail.i_orderdetail_id IS 'id of detail order';

COMMENT ON COLUMN bu_order_detail.i_order_id IS 'id of order related';

COMMENT ON COLUMN bu_order_detail.i_product_id IS 'id product';

COMMENT ON COLUMN bu_order_detail.i_discount IS 'Discount applied to this product';

CREATE  TABLE bu_status_order ( 
	i_statusorder_id     serial  NOT NULL ,
	v_status_name        varchar(50)   ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	i_organization_id    integer   ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	CONSTRAINT pk_status_order_status_order_id PRIMARY KEY ( i_statusorder_id )
 );

COMMENT ON TABLE bu_status_order IS 'status of a order, could be \n- Pending\n- Created\n- Canceled';

CREATE  TABLE de_activity ( 
	i_activity_id        serial  NOT NULL ,
	i_type_id            integer   ,
	p_location_coords    point   ,
	i_order_id           integer   ,
	i_activity_number    integer   ,
	t_create_date       date   ,
	i_create_id         integer   ,
	t_update_date       date   ,
	i_update_id         integer   ,
	i_organization_id    integer  NOT NULL ,
	b_deleted            boolean   ,
	CONSTRAINT pk_de_activities_i_activity_id PRIMARY KEY ( i_activity_id )
 );

CREATE  TABLE de_route ( 
	i_route_id           serial  NOT NULL ,
	v_route_code         varchar   ,
	d_price_route        varchar   ,
	v_vehicle_type       varchar   ,
	i_driver_id          integer   ,
	j_activities         json   ,
	d_start_date         date   ,
	i_statusroute_id     integer   ,
	t_create_date       date   ,
	i_create_id         integer   ,
	t_update_date       date   ,
	i_update_id         integer   ,
	b_deleted            boolean   ,
	CONSTRAINT pk_de_routes_i_route_id PRIMARY KEY ( i_route_id )
 );

CREATE  TABLE image ( 
	i_image_id           serial  NOT NULL ,
	v_name               varchar(248)  NOT NULL ,
	v_url                varchar(64)  NOT NULL ,
	i_product_id         integer  NOT NULL ,
	i_organization_id    integer  NOT NULL ,
	CONSTRAINT pk_image_i_id PRIMARY KEY ( i_image_id )
 );

CREATE  TABLE se_price ( 
	i_price_id           serial  NOT NULL ,
	d_value              decimal(9,2)  NOT NULL ,
	c_state              char(1) DEFAULT 'A' NOT NULL ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	i_organization_id    integer  NOT NULL ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	i_product_id         integer  NOT NULL ,
	CONSTRAINT pk_se_price_i_id PRIMARY KEY ( i_price_id )
 );

COMMENT ON COLUMN se_price.t_create_date IS 'Date of creation';

COMMENT ON COLUMN se_price.i_create_id IS 'Id of the user';

COMMENT ON COLUMN se_price.t_update_date IS 'Data of updated';

COMMENT ON COLUMN se_price.i_update_id IS 'Updated by ID';

CREATE  TABLE bu_address ( 
	i_address_id         serial  NOT NULL ,
	v_address_address    varchar(100)   ,
	i_city_id            integer  NOT NULL ,
	v_address_postalcode varchar(10)   ,
	i_account_id         integer   ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	CONSTRAINT pk_bu_address_n_address_id PRIMARY KEY ( i_address_id ),
	CONSTRAINT unq_bu_address_n_city_id UNIQUE ( i_city_id ) 
 );

CREATE  TABLE bu_city ( 
	i_city_id            serial  NOT NULL ,
	v_city_name          varchar(70)   ,
	i_country_id         integer  NOT NULL ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	CONSTRAINT pk_bu_city_n_city_id PRIMARY KEY ( i_city_id ),
	CONSTRAINT unq_bu_city_n_country_id UNIQUE ( i_country_id ) 
 );

CREATE  TABLE bu_country ( 
	i_country_id         serial  NOT NULL ,
	v_country_name       varchar(50)   ,
	t_create_date       timestamp  NOT NULL ,
	i_create_id         integer  NOT NULL ,
	t_update_date       timestamp   ,
	i_update_id         integer   ,
	b_deleted            boolean DEFAULT FALSE NOT NULL ,
	CONSTRAINT pk_country_n_id PRIMARY KEY ( i_country_id )
 );

ALTER TABLE ad_action ADD CONSTRAINT fk_ad_action_ad_action FOREIGN KEY ( i_function_id ) REFERENCES ad_function( i_function_id );

ALTER TABLE ad_role_function ADD CONSTRAINT fk_ad_role_function_ad_role FOREIGN KEY ( i_role_id ) REFERENCES ad_role( i_role_id );

ALTER TABLE ad_role_function ADD CONSTRAINT fk_ad_role_function FOREIGN KEY ( i_function_id ) REFERENCES ad_function( i_function_id );

ALTER TABLE ad_user ADD CONSTRAINT fk_ad_user_ad_organization FOREIGN KEY ( i_organization_id ) REFERENCES ad_organization( i_organization_id );

ALTER TABLE ad_user_function ADD CONSTRAINT fk_ad_user_function_ad_user FOREIGN KEY ( i_user_id ) REFERENCES ad_user( i_user_id );

ALTER TABLE ad_user_function ADD CONSTRAINT fk_ad_user_function FOREIGN KEY ( i_function_id ) REFERENCES ad_function( i_function_id );

ALTER TABLE bu_address ADD CONSTRAINT fk_bu_address_bu_order FOREIGN KEY ( i_address_id ) REFERENCES bu_order( i_address_id );

ALTER TABLE bu_city ADD CONSTRAINT fk_bu_city_bu_order FOREIGN KEY ( i_city_id ) REFERENCES bu_address( i_city_id );

ALTER TABLE bu_country ADD CONSTRAINT fk_bu_country_bu_city FOREIGN KEY ( i_country_id ) REFERENCES bu_city( i_country_id );

ALTER TABLE bu_order ADD CONSTRAINT fk_bu_order_de_driver FOREIGN KEY ( i_driver_id ) REFERENCES de_driver( i_driver_id );

ALTER TABLE bu_order_detail ADD CONSTRAINT fk_order_detail_order_detail FOREIGN KEY ( i_order_id ) REFERENCES bu_order( i_order_id );

ALTER TABLE bu_order_detail ADD CONSTRAINT fk_bu_order_detail_se_product FOREIGN KEY ( i_product_id ) REFERENCES se_product( i_product_id );

ALTER TABLE bu_status_order ADD CONSTRAINT fk_status_order_status_order FOREIGN KEY ( i_statusorder_id ) REFERENCES bu_order( i_status_order_id );

ALTER TABLE de_activity ADD CONSTRAINT fk_de_activity_db_login FOREIGN KEY ( i_order_id ) REFERENCES bu_order( i_order_id );

ALTER TABLE de_route ADD CONSTRAINT fk_de_route_de_driver FOREIGN KEY ( i_driver_id ) REFERENCES de_driver( i_driver_id );

ALTER TABLE de_route ADD CONSTRAINT fk_de_route_de_status_route FOREIGN KEY ( i_statusroute_id ) REFERENCES de_status_route( i_statusroute_id );

ALTER TABLE image ADD CONSTRAINT fk_image_se_product FOREIGN KEY ( i_product_id ) REFERENCES se_product( i_product_id );

ALTER TABLE se_price ADD CONSTRAINT fk_se_price_se_product FOREIGN KEY ( i_product_id ) REFERENCES se_product( i_product_id );

