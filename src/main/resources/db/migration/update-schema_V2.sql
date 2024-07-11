ALTER TABLE test_model
DROP
COLUMN acc;

ALTER TABLE st_user
    MODIFY hours BIGINT NOT NULL;

ALTER TABLE st_user
    MODIFY rating DOUBLE NOT NULL;

ALTER TABLE st_user
    MODIFY user_type INT NULL;