create table users (
    id integer not null auto_increment,
    username varchar(100) not null,
    password varchar(255) not null,
    enable tinyint not null,

    primary key(id)
);