/*
角色
 */
CREATE TABLE IF NOT EXISTS wechat_role (
  role_id     INT         NOT NULL,
  role_name   VARCHAR(50) NOT NULL,
  PRIMARY KEY (role_id)
)
  DEFAULT CHARSET = utf8;