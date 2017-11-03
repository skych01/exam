CREATE TABLE questions_favorite(
  question_id INT ,
  favorite_id INT ,
  create_time DATETIME not NULL,
  update_time DATETIME,
  PRIMARY KEY (question_id,favorite_id)
)  DEFAULT CHARSET = utf8;

ALTER TABLE questions_favorite
  ADD CONSTRAINT FK_questions_favorite_1 FOREIGN KEY (question_id)
REFERENCES questions (question_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE questions_favorite
  ADD CONSTRAINT FK_questions_favorite_2 FOREIGN KEY (favorite_id)
REFERENCES favorite (favorite_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;