insert into com.user values (3 , 'petr_petrov', 'petrov@yandex.ru', 'Петр', 'Петров', '$2a$10$FqxNE.yXiRKHcD4j2KjfouL8ipxgLUPwIHK67ofME7loTp1w6N/Xu',
                             current_timestamp, current_timestamp, 'ACTIVE'); /* password: abcd1234 */
insert into com.user values (4, 'ivan_ivanov', 'ivanov1@yandex.ru', 'Иван', 'Иванов', '$2a$10$FqxNE.yXiRKHcD4j2KjfouL8ipxgLUPwIHK67ofME7loTp1w6N/Xu',
                             current_timestamp, current_timestamp, 'ACTIVE'); /* password: abcd1234 */
insert into com.user values (5, 'vasily_vasiliev', 'vasiliyev@yandex.ru', 'Василий', 'Васильев', '$2a$10$FqxNE.yXiRKHcD4j2KjfouL8ipxgLUPwIHK67ofME7loTp1w6N/Xu',
                             current_timestamp, current_timestamp, 'ACTIVE'); /* password: abcd1234 */
insert into com.user values (6, 'egor_ivanov', 'ivanov2@yandex.ru', 'Егор', 'Иванов', '$2a$10$FqxNE.yXiRKHcD4j2KjfouL8ipxgLUPwIHK67ofME7loTp1w6N/Xu',
                             current_timestamp, current_timestamp, 'NOT_ACTIVE'); /* password: abcd1234 */
insert into com.user values (7, 'vasily_ivanov', 'ivanov3@yandex.ru', 'Василий', 'Иванов', '$2a$10$FqxNE.yXiRKHcD4j2KjfouL8ipxgLUPwIHK67ofME7loTp1w6N/Xu',
                             current_timestamp, current_timestamp, 'ACTIVE'); /* password: abcd1234 */
select setval('user_id_seq', (SELECT max(id) FROM com.user));

insert into com.user_role values (3, 1);
insert into com.user_role values (4, 1);
insert into com.user_role values (5, 1);
insert into com.user_role values (6, 1);
insert into com.user_role values (7, 1);
insert into com.user_role values (7, 2);
