CREATE TABLE APP_ROLE(
ID BIGINT AUTO_INCREMENT not null,
NAME VARCHAR(50) not null,
DESCRIPTION VARCHAR(500),
MOD_USER VARCHAR(5) not null,
MOD_TIME TIMESTAMP not null
);