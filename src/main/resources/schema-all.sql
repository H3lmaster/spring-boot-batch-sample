CREATE TABLE log  (
    logId BIGINT PRIMARY KEY NOT NULL auto_increment,
    date timestamp, 
    ip VARCHAR(20),
    request VARCHAR(200),
    status VARCHAR(20),
    userAgent VARCHAR(200)
);