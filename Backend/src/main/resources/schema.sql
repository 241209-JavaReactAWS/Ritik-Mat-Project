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
    f_rank int,
    e_rank int,
    d_rank int,,
    c_rank int,
    b_rank int,
    a_rank int,
    s_rank int,
    ss_rank int,
    PRIMARY KEY (id)
};

INSERT INTO users (username,password,admin) VALUES ("admin","admin",1);
