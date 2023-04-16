create table details (
    id bigint not null auto_increment,
    authors varchar(255),
    content varchar(255),
    film_id bigint not null,
    primary key (id)
)