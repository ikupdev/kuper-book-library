insert into com.user values (1, 'kuper', 'kup-92@yandex.ru', 'Ilya', 'Kupriyanov',
                               '$2a$10$FqxNE.yXiRKHcD4j2KjfouL8ipxgLUPwIHK67ofME7loTp1w6N/Xu',
                             current_timestamp, current_timestamp, 'ACTIVE'); /* password: abcd1234 */
select setval('user_id_seq', (SELECT max(id) FROM com.user));

insert into com.role values (1, 'ROLE_USER');
insert into com.role values (2, 'ROLE_ADMIN');
select setval('role_id_seq', (SELECT max(id) FROM com.role));

insert into com.user_role values (1, 1);
