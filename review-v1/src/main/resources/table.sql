drop table if exists review CASCADE;

create table review (
    id bigint NOT NULL AUTO_INCREMENT,
    content VARCHAR(255),
    details_id bigint not null,
    title VARCHAR(255),
    PRIMARY KEY (id)
);