create table user_friends(
    user_id int not null,
    friend_id int not null,
    foreign key (user_id) references users(id),
    foreign key (friend_id) references users(id),
    primary key (user_id, friend_id)
)