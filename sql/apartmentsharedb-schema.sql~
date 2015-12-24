drop database if exists apartmentsharedb;
create database apartmentsharedb;
use apartmentsharedb;

CREATE TABLE users (

    	id BINARY(16) NOT NULL,

    	loginid VARCHAR(15) NOT NULL UNIQUE,

    	password BINARY(16) NOT NULL,

	fullname VARCHAR(255) NOT NULL,
	
   	email VARCHAR(255) NOT NULL,

	phone VARCHAR (20) NOT NULL,


    	PRIMARY KEY (id)

);



CREATE TABLE user_roles (

    userid BINARY(16) NOT NULL,

    role ENUM ('registered','administrator'),

    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,

    PRIMARY KEY (userid, role)

);



CREATE TABLE auth_tokens (

    userid BINARY(16) NOT NULL,

    token BINARY(16) NOT NULL,

    FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,

    PRIMARY KEY (token)

);

CREATE TABLE campus_upc (

    	id BINARY(16) NOT NULL,

  	campusname VARCHAR(20) NOT NULL,

   	address VARCHAR(200) NOT NULL,
	
	longitud FLOAT (17,14) NOT NULL,

	latitud FLOAT (17,14) NOT NULL,

    	PRIMARY KEY (id)

);


CREATE TABLE flat(

    	id BINARY(16) NOT NULL,

    	userid BINARY(16) NOT NULL,
	
	campusid BINARY(16) NOT NULL,

	numpartner INT NOT NULL,
	
	smoker INT NOT NULL,
	
	pets INT NOT NULL,

	girlorboy INT NOT NULL,
	
	sqm INT NOT NULL,
	
	furnished INT NOT NULL,
	
	numrooms INT NOT NULL,

	numbathrooms INT NOT NULL,

	elevator INT NOT NULL,

	plantnum INT NOT NULL,

	internet INT NOT NULL,

	fianza INT NOT NULL,

	estancia INT NOT NULL,
	
	address VARCHAR (200) NOT NULL,

    	description VARCHAR(1000) NOT NULL,
	
	last_modified TIMESTAMP NOT NULL,

    	creation_timestamp DATETIME not null default current_timestamp,

   	FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
	
	FOREIGN KEY (campusid) REFERENCES campus_upc(id),

    PRIMARY KEY (id)

);

CREATE TABLE room(

    	id BINARY(16) NOT NULL,

	flatid BINARY(16) NOT NULL,

    	userid BINARY(16) NOT NULL,
	
	girlorboy INT NOT NULL,
	
	sqm INT NOT NULL,

	furnished INT NOT NULL,
	
	status INT NOT NULL,
	
	price INT NOT NULL,

    	description VARCHAR(1000) NOT NULL,
	
	last_modified TIMESTAMP NOT NULL,

    	creation_timestamp DATETIME not null default current_timestamp,

   	FOREIGN KEY (userid) REFERENCES users(id) on delete cascade,
	
	FOREIGN KEY (flatid) REFERENCES flat(id) on delete cascade,

    PRIMARY KEY (id)

);

CREATE TABLE imagenflat (

    id BINARY(16) NOT NULL,

    flatid BINARY(16) NOT NULL,

    FOREIGN KEY (flatid) REFERENCES flat(id) on delete cascade,
	
    PRIMARY KEY (id)

);


CREATE TABLE imagenroom (

    id BINARY(16) NOT NULL,

    roomid BINARY(16) NOT NULL,

    FOREIGN KEY (id) REFERENCES imagenflat(id) on delete cascade,

	FOREIGN KEY (roomid) REFERENCES room(id) on delete cascade

);


