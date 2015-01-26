-- data
insert into CURRENCY (NAME) values('BYR');
insert into CURRENCY (NAME) values('USD');
insert into CURRENCY (NAME) values('RUB');

insert into CURRENCY_RATES (CURRENCY_INIT_ID, CURRENCY_EXCH_ID, RATE) values(1, 2, 0.000095);
insert into CURRENCY_RATES (CURRENCY_INIT_ID, CURRENCY_EXCH_ID, RATE) values(2, 1, 10900);
insert into CURRENCY_RATES (CURRENCY_INIT_ID, CURRENCY_EXCH_ID, RATE) values(1, 3, 0.003921);
insert into CURRENCY_RATES (CURRENCY_INIT_ID, CURRENCY_EXCH_ID, RATE) values(3, 1, 255);
insert into CURRENCY_RATES (CURRENCY_INIT_ID, CURRENCY_EXCH_ID, RATE) values(2, 3, 0.243902);
insert into CURRENCY_RATES (CURRENCY_INIT_ID, CURRENCY_EXCH_ID, RATE) values(3, 2, 47);

insert into PERSONS (NAME, SURNAME) values('Siarhei', 'Karpenka');
insert into PERSONS (NAME, SURNAME) values('Pavel', 'Sukharuchanka');
insert into PERSONS (NAME, SURNAME) values('Ivan', 'Sialitski');
insert into PERSONS (NAME, SURNAME) values('Siarhei', 'Ivashkou');
insert into PERSONS (NAME, SURNAME) values('Mikalai', 'Ushanau');
insert into PERSONS (NAME, SURNAME) values('Siarhei', 'Varachai');
insert into PERSONS (NAME, SURNAME) values('James', 'Hlavatty');
insert into PERSONS (NAME, SURNAME) values('Bob', 'Fraser');
insert into PERSONS (NAME, SURNAME) values('Levente', 'Csukas');

insert into ACCOUNTS (PERSON_ID) values(1);
insert into ACCOUNTS (PERSON_ID) values(2);
insert into ACCOUNTS (PERSON_ID) values(3);
insert into ACCOUNTS (PERSON_ID) values(4);
insert into ACCOUNTS (PERSON_ID) values(5);
insert into ACCOUNTS (PERSON_ID) values(6);
insert into ACCOUNTS (PERSON_ID) values(7);
insert into ACCOUNTS (PERSON_ID) values(8);
insert into ACCOUNTS (PERSON_ID) values(9);

insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 33000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 40000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 40000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 30000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 35000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 15000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 45000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 23000);
insert into BILLS (CURRENCY_ID, AMOUNT) values(2, 17000);

insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(1, 1);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(2, 2);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(3, 3);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(4, 4);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(5, 5);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(6, 6);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(7, 7);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(8, 8);
insert into ACCOUNTS_BILLS (ACCOUNT_ID, BILL_ID) values(9, 9);
