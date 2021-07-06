create table users(
   id serial primary key not null,
   username varchar(255) not null unique,
   password varchar(255) not null,
   first_name varchar(255) not null,
   last_name varchar(255) not null,
   status varchar(255) not null default 'ACTIVE',
   created date default CURRENT_DATE not null,
   updated date default CURRENT_DATE not null
)
