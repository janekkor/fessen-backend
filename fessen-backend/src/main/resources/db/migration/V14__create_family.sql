CREATE TABLE FAMILY(
ID BIGINT AUTO_INCREMENT not null,
NAME VARCHAR(50) not null,
CODE VARCHAR(10) not null,
PASSWORD VARCHAR(50) not null,
CREATION_TIME TIMESTAMP not null,
MOD_USER VARCHAR(50) not null,
MOD_TIME TIMESTAMP not null
);

ALTER TABLE FAMILY
ADD PRIMARY KEY (ID);

ALTER TABLE FAMILY
ADD CONSTRAINT UNIQUE_FAMILY_NAME
UNIQUE(NAME);

ALTER TABLE FAMILY
ADD CONSTRAINT UNIQUE_FAMILY_CODE
UNIQUE(CODE);