/*
用户-角色关系映射，多对多
 */
CREATE TABLE IF NOT EXISTS wechat_user_roles (
  open_id VARCHAR(100) NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (open_id, role_id)
)
  DEFAULT CHARSET = utf8;

ALTER TABLE wechat_user_roles
  ADD CONSTRAINT FK_WechatUserRoles_1 FOREIGN KEY (open_id)
REFERENCES wechat_user (open_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE wechat_user_roles
  ADD CONSTRAINT FK_WechatUserRoles_2 FOREIGN KEY (role_id)
REFERENCES wechat_role (role_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;