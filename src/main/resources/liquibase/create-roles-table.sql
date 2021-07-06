create table roles(
    id serial primary key not null,
    name varchar unique not null ,
    status varchar(255) not null default 'ACTIVE',
    created date default CURRENT_DATE not null,
    updated date default CURRENT_DATE not null
)