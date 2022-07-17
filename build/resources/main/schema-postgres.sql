DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users(id serial PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), password VARCHAR(255));
DROP TABLE IF EXISTS surveys CASCADE;
CREATE TABLE surveys(id serial PRIMARY KEY, user_id INT, question VARCHAR(255));
DROP TABLE IF EXISTS surveyanswers CASCADE;
CREATE TABLE surveyanswers(id serial PRIMARY KEY, survey_id INT, user_id INT, answer VARCHAR(255));

ALTER TABLE surveyanswers
ADD FOREIGN KEY (survey_id) REFERENCES surveys(id);
ALTER TABLE surveyanswers
ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE surveys
ADD FOREIGN KEY (user_id) REFERENCES users(id);