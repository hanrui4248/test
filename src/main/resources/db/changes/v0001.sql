create table "student"(
    id    BIGSERIAL NOT NULL,
    first_name VARCHAR,
    last_name  VARCHAR,
    CONSTRAINT student_pk PRIMARY KEY(id)
);
create table "testuser"(
    id BIGSERIAL NOT NULL CONSTRAINT user_pk PRIMARY KEY ,
    first_name VARCHAR,
    last_name  VARCHAR,
    email      VARCHAR,
    password   VARCHAR
);
create table "cours" (
     id BIGSERIAL NOT NULL ,
     sigle VARCHAR,
     title VARCHAR,
     description TEXT,
     update_at TIMESTAMP,
     CONSTRAINT cours_pk PRIMARY KEY (id)
 );

