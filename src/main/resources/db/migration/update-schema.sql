ALTER TABLE test_model
    ADD acc INT NULL;

ALTER TABLE test_model
    ADD `description` VARCHAR(255) NULL;

ALTER TABLE test_model
    MODIFY acc INT NOT NULL;

ALTER TABLE st_user
    MODIFY hours BIGINT NOT NULL;

ALTER TABLE st_user
    MODIFY rating DOUBLE NOT NULL;

ALTER TABLE st_user
    MODIFY user_type INT NULL;