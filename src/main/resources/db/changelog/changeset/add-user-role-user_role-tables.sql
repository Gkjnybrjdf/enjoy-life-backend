create table if not exists app_user(
                                id serial,
                                username varchar(50) NOT NULL,
                                created_at timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                password varchar(50) NOT NULL,
                                CONSTRAINT app_user_pkey PRIMARY KEY (id)
);
create table if not exists app_role(
                                id serial,
                                name varchar(100) NOT NULL,
                                created_at timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                CONSTRAINT app_role_pkey PRIMARY KEY (id)
);
create  table if not exists user_role(
                                      id serial,
                                      user_id int NOT NULL,
                                      role_id int NOT NULL,
                                      CONSTRAINT user_role_pkey PRIMARY KEY (id),
                                      CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES app_user (id),
                                      CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES app_role (id)
);