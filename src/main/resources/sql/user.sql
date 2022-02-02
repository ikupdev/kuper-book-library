-- Книги
    CREATE SEQUENCE IF NOT EXISTS com.user_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    CYCLE;
ALTER TABLE com.user_id_seq
    OWNER TO kuper;

CREATE TABLE IF NOT EXISTS com.user
(
    id          BIGINT                      NOT NULL DEFAULT nextval('com.user_id_seq' :: REGCLASS),
    first_name       CHARACTER VARYING(256)          DEFAULT NULL,
    last_name       CHARACTER VARYING(256)          DEFAULT NULL,
    login      CHARACTER VARYING(256)               DEFAULT NULL,
    hash_password CHARACTER VARYING(256)               DEFAULT NULL,
    role     CHARACTER VARYING(1024)              DEFAULT NULL,
    state     CHARACTER VARYING(1024)              DEFAULT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;
ALTER TABLE com.user
    OWNER TO kuper;