create table user_roles(
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references public.users(id),
    foreign key (role_id) references public.roles(id)
)