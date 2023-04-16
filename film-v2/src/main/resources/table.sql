drop table if exists film CASCADE;

create table film (
    id bigint NOT NULL AUTO_INCREMENT,
    duration bigint not null,
    genre integer not null,
    film_name varchar(255),
    issue date not null,
    primary key (id)
);