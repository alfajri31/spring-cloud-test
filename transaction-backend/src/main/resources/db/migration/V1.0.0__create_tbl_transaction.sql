CREATE TABLE tbl_transaksi (
                                    employee_id int4 NOT NULL,
                                    amount BIGINT NULL,
                                    tgl_transaksi TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
