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

insert into com.bookshelf values (1, 'Название 1', 'Описание 1', 1, current_timestamp, current_timestamp);
insert into com.bookshelf values (2, 'Название 2', 'Описание 2', 1, current_timestamp, current_timestamp);
insert into com.bookshelf values (3, 'Книжная полка 3', null, 1, current_timestamp, current_timestamp);

select setval('bookshelf_id_seq', (SELECT max(id) FROM com.bookshelf));

insert into com.book values (1, '7zeyHAAACAAJ', 'Harry Potter et la coupe de feu', null, 'J. K. Rowling, Jean-François Ménard',
                             'Harry Potter a quatorze ans et il entre en quatrième année au collège de Poudlard. Une grande nouvelle attend Harry, Ron et Hermione à leur arrivée : la tenue dun tournoi de magie exceptionnel entre les plus célèbres écoles de sorcellerie. Déjà, les délégations étrangères font leur entrée. Harry se réjouit... Trop vite. Il va se trouver plongé au cœur des événements les plus dramatiques quil ait jamais eu à affronter. Fascinant, drôle, bouleversant, ce quatrième tome est le pilier central des aventures de Harry Potter.',
                             768, 'en', 'Harry Potter a quatorze ans et il entre en quatrième année au collège de Poudlard.',
                             'http://books.google.com/books/content?id=7zeyHAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api',
                             'http://books.google.ru/books?id=7zeyHAAACAAJ&dq=inauthor:rowling&hl=&cd=4&source=gbs_api',
                             'http://books.google.ru/books?id=7zeyHAAACAAJ&dq=inauthor:rowling&hl=&source=gbs_api',
                             'https://books.google.com/books/about/Harry_Potter_et_la_coupe_de_feu.html?hl=&id=7zeyHAAACAAJ', null, null,
                             'http://play.google.com/books/reader?id=7zeyHAAACAAJ&hl=&printsec=frontcover&source=gbs_api', null, current_timestamp, current_timestamp);
insert into com.book values (2, '9lLrAAAACAAJ', 'Conversations with J. K. Rowling', null, 'J. K. Rowling',
                             'Harry Potter a quatorze ans et il entre en quatrième année au collège de Poudlard. Une grande nouvelle attend Harry, Ron et Hermione à leur arrivée : la tenue dun tournoi de magie exceptionnel entre les plus célèbres écoles de sorcellerie. Déjà, les délégations étrangères font leur entrée. Harry se réjouit... Trop vite. Il va se trouver plongé au cœur des événements les plus dramatiques quil ait jamais eu à affronter. Fascinant, drôle, bouleversant, ce quatrième tome est le pilier central des aventures de Harry Potter.',
                             40, 'en', null,
                             'http://books.google.com/books/content?id=7zeyHAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api',
                             'http://books.google.ru/books?id=7zeyHAAACAAJ&dq=inauthor:rowling&hl=&cd=4&source=gbs_api',
                             'http://books.google.ru/books?id=7zeyHAAACAAJ&dq=inauthor:rowling&hl=&source=gbs_api',
                             'https://books.google.com/books/about/Harry_Potter_et_la_coupe_de_feu.html?hl=&id=7zeyHAAACAAJ', null, null,
                             'http://play.google.com/books/reader?id=7zeyHAAACAAJ&hl=&printsec=frontcover&source=gbs_api', null, current_timestamp, current_timestamp);
insert into com.book values (3, '8loxvgAACAAJ', 'The Casual Vacancy', null, 'J. K. Rowling',
                             'A big novel about a small town... When Barry Fairbrother dies in his early forties, the town of Pagford is left in shock. Pagford is, seemingly, an English idyll, with a cobbled market square and an ancient abbey, but what lies behind the pretty façade is a town at war. Rich at war with poor, teenagers at war with their parents, wives at war with their husbands, teachers at war with their pupils...Pagford is not what it first seems. And the empty seat left by Barry on the parish council soon becomes the catalyst for the biggest war the town has yet seen. Who will triumph in an election fraught with passion, duplicity, and unexpected revelations? A big novel about a small town, The Casual Vacancy is J.K. Rowlings first novel for adults. It is the work of a storyteller like no other.',
                             768, 'en', null,
                             'http://books.google.com/books/content?id=7zeyHAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api',
                             'http://books.google.ru/books?id=7zeyHAAACAAJ&dq=inauthor:rowling&hl=&cd=4&source=gbs_api',
                             'http://books.google.ru/books?id=7zeyHAAACAAJ&dq=inauthor:rowling&hl=&source=gbs_api',
                             'https://books.google.com/books/about/Harry_Potter_et_la_coupe_de_feu.html?hl=&id=7zeyHAAACAAJ', null, null,
                             'http://play.google.com/books/reader?id=7zeyHAAACAAJ&hl=&printsec=frontcover&source=gbs_api', null, current_timestamp, current_timestamp);
select setval('book_id_seq', (SELECT max(id) FROM com.book));
insert into com.bookshelf_book values (1, 1);
insert into com.bookshelf_book values (1, 2);
