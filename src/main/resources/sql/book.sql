-- Книги
CREATE SEQUENCE IF NOT EXISTS com.book_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    CYCLE;
ALTER TABLE com.book_id_seq
    OWNER TO kuper;

CREATE TABLE IF NOT EXISTS com.book
(
    id          BIGINT                      NOT NULL DEFAULT nextval('com.book_id_seq' :: REGCLASS),
    created     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (now() AT TIME ZONE 'utc'),
    updated     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (now() AT TIME ZONE 'utc'),

    title       CHARACTER VARYING(256)      NOT NULL,
    author      CHARACTER VARYING(128)               DEFAULT NULL,
    description CHARACTER VARYING(512)               DEFAULT NULL,
    content     CHARACTER VARYING(1024)              DEFAULT NULL,
    CONSTRAINT book_pk PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;
ALTER TABLE com.book
    OWNER TO kuper;