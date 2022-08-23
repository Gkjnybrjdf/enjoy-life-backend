create database enjoy_life;
\c enjoy_life;

create table if not exists to_do_list(
                                        id serial,
                                        created_at timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP not null,
                                        name varchar(100) not null,
                                        description varchar(1000),
                                        CONSTRAINT to_do_list_id PRIMARY KEY  (id)
);
create table if not exists category(
                                id serial,
                                created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
                                name varchar(100) not null,
                                description varchar(1000),
                                CONSTRAINT category_id PRIMARY KEY (id)
);
create table if not exists category_to_do_list(
                                            id serial,
                                            to_do_list_id int not null,
                                            category_id int not null,
                                            CONSTRAINT category_to_do_list_id PRIMARY KEY (id),
                                            FOREIGN KEY (to_do_list_id) REFERENCES to_do_list (id),
                                            FOREIGN KEY (category_id) REFERENCES category (id)
);