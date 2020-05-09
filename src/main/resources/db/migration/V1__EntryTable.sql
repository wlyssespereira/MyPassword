CREATE TABLE ENTRY
(
    id     UUID         NOT NULL PRIMARY KEY,
    title  VARCHAR(100) NOT NULL,
    "user" VARCHAR(100) NOT NULL,
    passwd VARCHAR(100) NOT NULL,
    notes  VARCHAR(100),
    url    VARCHAR(100),
    email  VARCHAR(100)
);