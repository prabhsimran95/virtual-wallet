INSERT INTO USER VALUES('101','Prabhsimran.06@gmail.com','201','Prabhsimran Singh','MoneyBank');
INSERT INTO USER VALUES('102','Ranjoodhparmar@gmail.com','202','Ranjoodh Singh','MyBank');

INSERT INTO Account VALUES(1001,500,'101');
INSERT INTO ACCOUNT VALUES(1002,1000,'101');
INSERT INTO ACCOUNT VALUES(1003,2000,'101');

INSERT INTO TRANSACTION VALUES(301,200,1001,'2017-11-01 16:25:25',1002,'Withdrawal',1001);
INSERT INTO TRANSACTION VALUES(302,200,1001,'2017-09-05 11:21:00',1002,'Deposit',1002);
INSERT INTO TRANSACTION VALUES(303,500,1002,'2016-01-11 07:45:25',1003,'Deposit',1002);
INSERT INTO TRANSACTION VALUES(304,500,1002,'2017-12-06 12:01:25',1003,'Withdrawal',1003);