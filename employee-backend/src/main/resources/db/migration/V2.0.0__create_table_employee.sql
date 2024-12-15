-- public.myemployees definition

-- Drop table

-- DROP TABLE public.myemployees;

CREATE TABLE public.tbl_employee (
                                    employee_id int4 NOT NULL PRIMARY KEY ,
                                    employee_name varchar(50) NULL,
                                    employee_manager_id int4 NULL
);
