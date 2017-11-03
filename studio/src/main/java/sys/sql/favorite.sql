CREATE TABLE favorite(
  favorite_id INT AUTO_INCREMENT,
  favorite_name VARCHAR(100) not NULL ,
  open_id     VARCHAR(100)    NOT NULL,
  create_time DATETIME not NULL,
  update_time DATETIME,
  PRIMARY KEY (favorite_id)
)  DEFAULT CHARSET = utf8;

ALTER TABLE answer
  ADD CONSTRAINT FK_favorite_1 FOREIGN KEY (open_id)
REFERENCES wechat_user (open_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;