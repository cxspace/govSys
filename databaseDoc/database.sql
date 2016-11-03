/*
    作者：
    日期：
    描述：
 */

 CREATE TABLE person
(
    id VARCHAR(32) PRIMARY KEY NOT NULL,
    name VARCHAR(20) NOT NULL
);
CREATE TABLE user
(
    id VARCHAR(32) PRIMARY KEY NOT NULL,
    name VARCHAR(20) NOT NULL,
    dept VARCHAR(20) NOT NULL,
    account VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    headImg VARCHAR(100),
    gender BIT(1),
    email VARCHAR(50),
    mobile VARCHAR(20),
    birthday DATETIME,
    state VARCHAR(1),
    memo VARCHAR(200)
);
