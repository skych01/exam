CREATE TABLE answer(
  answer_id INT AUTO_INCREMENT,
  answer_name VARCHAR(100) not NULL ,
  is_correct  SMALLINT  NOT NULL DEFAULT 0,
  question_id INT,
  create_time DATETIME not NULL,
  update_time DATETIME,
  PRIMARY KEY (answer_id)
)  DEFAULT CHARSET = utf8;

ALTER TABLE answer
  ADD CONSTRAINT FK_answer_1 FOREIGN KEY (question_id)
REFERENCES questions (question_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;