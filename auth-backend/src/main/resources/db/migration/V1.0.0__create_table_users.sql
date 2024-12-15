-- public.tbl_users definition

-- Drop table

-- DROP TABLE public.tbl_users;

CREATE TABLE public.tbl_users (
                                  "role" int4 NULL,
                                  created_at timestamp(6) NOT NULL,
                                  updated_at timestamp(6) NULL,
                                  email varchar(255) NULL,
                                  id varchar(255) NOT NULL,
                                  "password" varchar(255) NULL,
                                  username varchar(255) NULL,
                                  CONSTRAINT tbl_users_pkey PRIMARY KEY (id)
);
