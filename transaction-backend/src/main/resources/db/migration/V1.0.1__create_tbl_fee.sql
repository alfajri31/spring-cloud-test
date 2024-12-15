CREATE TABLE tbl_fee (
                                    employee_id int4 NOT NULL,
                                    amount_fee BIGINT NULL,
                                    tgl_fee TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
