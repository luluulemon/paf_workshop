create table user(

    user_id char(8) not null
    username varchar(256) not null,
    name    varchar(256)

    primary key(user_id)
)


create table task(

    task_id int auto_increment not null,
    description text not null,
    priority varchar(64) not null,
    due_date date not null,
    user_id char(8) not null,

    primary key(task_id),
    constraint fk_user_id foreign key(user_id) references user(user_id)
)