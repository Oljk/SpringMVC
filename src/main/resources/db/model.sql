
begin

dbms_output.put_line('begin');

EXECUTE IMMEDIATE 'create table users (
	user_id NUMBER(10) PRIMARY KEY ,
	name Varchar2 (30) NOT NULL ,
	surname Varchar2 (30) NOT NULL ,
	e_mail Varchar2 (30),
	password Varchar2 (100) NOT NULL ,
	login Varchar2 (20) NOT NULL ,
	phone_number Varchar2 (20),
	isAdmin NUMBER(10)
)'  ;


EXECUTE IMMEDIATE 'Create table Orders (
	order_id NUMBER(10) PRIMARY KEY,
	user_id NUMBER(10) NOT NULL ,
	adress Varchar2 (500),
	summ Decimal(10,0),
	comments Varchar2 (500),
	isDone NUMBER(10)
)';
EXECUTE IMMEDIATE 'Create table Author (
	author_id NUMBER(10) PRIMARY KEY,
	name Varchar2 (30),
	surname Varchar2 (30),
	description Varchar2 (2000)
)';
EXECUTE IMMEDIATE 'Create table Item (
	item_id NUMBER(10) NOT NULL PRIMARY KEY,
	parent_id NUMBER(10),
	name Varchar2 (20),
	type NUMBER(10) NOT NULL ,
	desription Varchar2 (2000)
)';
EXECUTE IMMEDIATE 'Create table author_has_book (
	author_id NUMBER(10) NOT NULL ,
	item_id NUMBER(10) NOT NULL ,
primary key (author_id,item_id)
)';
EXECUTE IMMEDIATE 'Create table Order_has_book (
	order_id NUMBER(10) NOT NULL ,
	item_id NUMBER(10) NOT NULL ,
	amount_of_books NUMBER(10) NOT NULL
)';

EXECUTE IMMEDIATE 'Create table Good_attrs (
	amount NUMBER(10) NOT NULL ,
	item_id NUMBER(10) PRIMARY KEY,
	publishing_house Varchar2(30),
	year number (10) NOT NULL ,
	price NUMBER(10,3) NOT NULL
)';

EXECUTE IMMEDIATE 'Alter table Orders add  foreign key (user_id) references Users (user_id)';
EXECUTE IMMEDIATE 'Alter table Order_has_book add  foreign key (order_id) references Orders (order_id)';
EXECUTE IMMEDIATE 'Alter table author_has_book add  foreign key (author_id) references Author (author_id)';
EXECUTE IMMEDIATE 'Alter table Order_has_book add  foreign key (item_id) references Item (item_id)';
EXECUTE IMMEDIATE 'Alter table Item add  foreign key (parent_id) references Item (item_id)';
EXECUTE IMMEDIATE 'Alter table Good_attrs add  foreign key (item_id) references Item (item_id)';
EXECUTE IMMEDIATE 'Alter table author_has_book add  foreign key (item_id) references Good_attrs (item_id)';

dbms_output.put_line('i alive');

EXCEPTION
	when others then
		dbms_output.put_line('exeption');
end;
/

 CREATE SEQUENCE get_id
	INCREMENT BY 1
	START WITH 100
	MAXVALUE 9999999999
	NOCACHE
	NOCYCLE;

commit;


