CREATE TABLE questions(
  question_id INT AUTO_INCREMENT,
  question_name VARCHAR(100) not NULL ,
  create_time DATETIME not NULL,
  update_time DATETIME,
  PRIMARY KEY (question_id)
)  DEFAULT CHARSET = utf8;