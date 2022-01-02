CREATE TABLE IF NOT EXISTS com.persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);
ALTER TABLE com.persistent_logins
    OWNER TO kuper;