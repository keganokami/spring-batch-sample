CREATE TABLE person (
	id char(10) NOT NULL,
	name char(100) NOT NULL,
	location char(100) NOT NULL,
	birth_date char(100) NOT NULL,
	primary key(id)
);


INSERT INTO person (id, name, location, birth_date )
VALUES('1',  '山田', '北海道', '20220101');

INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE )
VALUES('2',  '田中', '東京', '20220202');

INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE )
VALUES('3',  '佐藤', '沖縄', '20220303');