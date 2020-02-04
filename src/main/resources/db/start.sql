insert into users (user_id, name, surname, e_mail, password, login, phone_number, ISADMIN)
values (1, 'Admin', 'Adminovskiy', 'admin@gmail.com', '1234', 'admin', '+3805566113', 1);

insert into users (user_id, name, surname, e_mail, password, login, phone_number, ISADMIN)
values (2, 'User1', 'User1Sur', 'user1@gmail.com', '1234', 'user1', '+380556f6113', null);

insert into users (user_id, name, surname, e_mail, password, login, phone_number, ISADMIN)
values (3, 'User2', 'User2Sur', 'user2@gmail.com', '1234', 'user2', '+380556f6113', null);

insert INTO item (ITEM_ID, PARENT_ID, NAME, TYPE, DESRIPTION)
VALUES  (10, null, 'FANTASY', 0, '0 eto rubrika');

insert INTO item (ITEM_ID, PARENT_ID, NAME, TYPE, DESRIPTION)
VALUES  (11, 10, 'DOM Strannyh detey', 1, 'eto kniga');

insert INTO item (ITEM_ID, PARENT_ID, NAME, TYPE, DESRIPTION)
VALUES  (12, 10, 'DOM Strannyh detey 3ya chastj', 1, 'eto kniga');

insert INTO item (ITEM_ID, PARENT_ID, NAME, TYPE, DESRIPTION)
VALUES  (13, 10, 'Harry Potter and the Prisoner of Azkaban', 1, 'eto kniga');

INSERT into GOOD_ATTRS (AMOUNT, ITEM_ID, PUBLISHING_HOUSE, YEAR, PRICE)
VALUES (10, 11, 'kniznyj klub', 2011, 123.1);

INSERT into GOOD_ATTRS (AMOUNT, ITEM_ID, PUBLISHING_HOUSE, YEAR, PRICE)
VALUES (11, 12, 'kniznyj klub', 2015, 152.1);

INSERT into GOOD_ATTRS (AMOUNT, ITEM_ID, PUBLISHING_HOUSE, YEAR, PRICE)
VALUES (2, 13, 'ababagalamaga', 1999, 120.5);

INSERT INTO AUTHOR (AUTHOR_ID, NAME, SURNAME, DESCRIPTION)
VALUES (50,'Ransom', 'Riggs', NULL);

INSERT INTO AUTHOR (AUTHOR_ID, NAME, SURNAME, DESCRIPTION)
VALUES (51,'Joahn', 'Rowling', NULL);

INSERT INTO AUTHOR_HAS_BOOK (AUTHOR_ID, ITEM_ID) VALUES (50, 11);
INSERT INTO AUTHOR_HAS_BOOK (AUTHOR_ID, ITEM_ID) VALUES (50, 12);
INSERT INTO AUTHOR_HAS_BOOK (AUTHOR_ID, ITEM_ID) VALUES (51, 13);
