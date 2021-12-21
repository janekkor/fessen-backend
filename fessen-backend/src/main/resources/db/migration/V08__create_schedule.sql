CREATE TABLE SCHEDULE(
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
MEMBER_ID BIGINT not null,
MEAL_ID BIGINT not null,
DAY TIMESTAMP not null,
CREATION_TIME TIMESTAMP not null,
MOD_USER VARCHAR(5) not null,
MOD_TIME TIMESTAMP not null
);

ALTER TABLE SCHEDULE
ADD FOREIGN KEY (MEMBER_ID) 
REFERENCES MEMBER(ID);

ALTER TABLE SCHEDULE
ADD FOREIGN KEY (MEAL_ID) 
REFERENCES MEAL(ID);