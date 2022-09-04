create database enjoy_life;
\c enjoy_life;

create table if not exists task(
                                id serial,
                                created_at timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP not null,
                                name varchar(100) not null,
                                description varchar(1000),
                                priority varchar(20) NOT NULL DEFAULT 'LOW',
                                is_easy bool,
                                is_active bool NOT NULL DEFAULT true,
                                active_modified_date timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                CONSTRAINT task_pkey PRIMARY KEY  (id)
);
create table if not exists category(
                                id serial,
                                created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
                                name varchar(100) not null,
                                description varchar(1000),
                                CONSTRAINT category_pkey PRIMARY KEY (id)
);
create table if not exists category_task(
                                            id serial,
                                            task_id int not null,
                                            category_id int not null,
                                            CONSTRAINT category_task_pkey PRIMARY KEY (id),
                                            CONSTRAINT category_task_task_id_fkey FOREIGN KEY (task_id) REFERENCES task (id),
                                            CONSTRAINT category_task_category_id_fkey FOREIGN KEY (category_id) REFERENCES category (id)
);