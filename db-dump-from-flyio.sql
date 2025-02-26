create table account
(
    id            bigserial
        primary key,
    balance       double precision not null,
    date_of_birth date             not null,
    email         varchar(255)     not null
        constraint uk_q0uja26qgu1atulenwup9rxyr
            unique,
    first_name    varchar(15)      not null,
    is_verified   boolean default true,
    last_name     varchar(255)     not null,
    phone_number  varchar(255)     not null
        constraint uk_g4b37bcdq6mmqdp3p67qoatc
            unique,
    pin           integer          not null
);

alter table account
    owner to money_transfer_api;

create table transfer
(
    id          bigserial
        primary key,
    amount      double precision not null,
    date        timestamp        not null,
    reason      text,
    status      varchar(10),
    uuid        varchar(255)     not null,
    receiver_id bigint           not null
        constraint fk7vy3banmwqotrq0v8kcdtn989
            references account,
    sender_id   bigint           not null
        constraint fkdwj52dnnulpcd39ywmnc3gnsb
            references account
);

alter table transfer
    owner to money_transfer_api;

