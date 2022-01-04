-- Книги
CREATE SEQUENCE IF NOT EXISTS com.token_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    CYCLE;
ALTER TABLE com.token_id_seq
    OWNER TO kuper;

CREATE TABLE IF NOT EXISTS com.token
(
    id          BIGINT                 NOT NULL DEFAULT nextval('com.token_id_seq' :: REGCLASS),
    token_value CHARACTER VARYING(256) NOT NULL,
    user_id     BIGINT                 NOT NULL,
    CONSTRAINT token_pk PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;
ALTER TABLE com.token
    OWNER TO kuper;