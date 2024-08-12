CREATE TABLE if not exists book
(
    id                        serial                   NOT NULL,
    title                     varchar(500)             NOT NULL,
    description               varchar(500),
    author                    varchar(150)             NOT NULL,
    pages_count               int,
    publisher                 varchar(200),
    printing_number           int,
    domain                    varchar(255),
    created_at                timestamp with time zone default current_timestamp,
    updated_at                timestamp with time zone default current_timestamp,
    created_by                varchar(150)             null,
    updated_by                varchar(150)             null,
    primary key (id)
);