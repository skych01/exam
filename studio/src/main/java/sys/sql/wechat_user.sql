/*
微信用户信息
 */
CREATE TABLE IF NOT EXISTS wechat_user (
  open_id     VARCHAR(100)    NOT NULL,
  nick_name   VARCHAR(100) CHARSET utf8mb4 NOT NULL,
  gender      SMALLINT        NOT NULL DEFAULT 0,
  language    VARCHAR(10)     NOT NULL,
  city        VARCHAR(50)     NOT NULL,
  province    VARCHAR(50)     NOT NULL,
  country     VARCHAR(50)     NOT NULL,
  avatar_url  VARCHAR(255),
  appid       VARCHAR(40),
  create_time DATETIME        NOT NULL,
  update_time DATETIME,
  PRIMARY KEY (open_id)
)
  DEFAULT CHARSET = utf8;