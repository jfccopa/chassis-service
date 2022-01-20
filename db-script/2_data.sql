insert into ad_organization
( i_organization_id, v_name, v_description, c_state, t_create_date, i_create_id, t_update_date, i_update_id, b_deleted)
values
(1, '3track.org', '3track', 'A', '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0, false );

insert into ad_user
( i_user_id, v_name, v_password, c_state, i_person_id, t_create_date, i_create_id, t_update_date, i_update_id, b_deleted, i_organization_id)
values
(1, 'admin', 'admin123', 'A', 0, '2021-01-01 00:00:00', 0, '2021-01-01 00:00:00', 0, false, 1 );

insert into se_product
( v_name, v_description, v_serial_number, i_quantity, d_price, d_weight, d_volume, c_state, t_create_date, i_create_id, t_update_date, i_update_id, b_deleted, i_organization_id)
values
('product 1', 'first product', '0001', 1, 20.10, 0.5, 1, 'A', '2021-01-01 00:00:00', 0, null,null, false,0 ),
('product 2', 'first product', '0002', 5, 588.10, 0.5, 1, 'A', '2021-01-01 00:00:00', 0, null,null, false,0 );