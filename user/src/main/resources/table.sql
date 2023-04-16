create table utente (
	email varchar(255) not null,
	birthdate date,
	name varchar(255),
	nationality integer,
	surname varchar(255),
	primary key (email)
)