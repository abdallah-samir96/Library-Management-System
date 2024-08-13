CREATE TABLE if not exists user_info
(
    id                        serial                   NOT NULL,
    first_name                varchar(500)             NOT NULL,
    last_name                 varchar(500)             Not null,
    email                     varchar(150)             NOT NULL UNIQUE,
    u_password                varchar(500)             not null,
    address                   varchar(200),
    job_title                 varchar(300),
    roles                     varchar(500),
    primary key (id)
);