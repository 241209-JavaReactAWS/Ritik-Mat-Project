CREATE TABLE users{
    id int,
    username varchar(100) UNIQUE,
    password varchar(100) NOT NULL,
    bank_account double DEFAULT 0.0,
    backpack_space int DEFAULT 6,
    admin int DEFAULT 0,
    PRIMARY KEY (id)
};

CREATE TABLE ducks{
    id int,
    referenceId int,
    rank varchar(5) NOT NULL,
    nickname varchar(25) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (reference_id) REFERENCES users(id) ON DELETE CASCADE
};

CREATE TABLE worlds{
    id int,
    f_rank int DEFAULT 0,
    e_rank int DEFAULT 0,
    d_rank int DEFAULT 0,
    c_rank int DEFAULT 0,
    b_rank int DEFAULT 0,
    a_rank int DEFAULT 0,
    s_rank int DEFAULT 0,
    ss_rank int DEFAULT 0,
    PRIMARY KEY (id)
};


