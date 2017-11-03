/*
角色-资源映射，多对多关系，一个角色可以配置多个资源，一个资源也可以属于多个角色
 */
CREATE TABLE IF NOT EXISTS wechat_role_resources (
  role_id      INT          NOT NULL,
  resource_url VARCHAR(200) NOT NULL,
  PRIMARY KEY (role_id, resource_url)
)
  DEFAULT CHARSET = utf8;

ALTER TABLE wechat_role_resources
  ADD CONSTRAINT FK_WechatRoleResources_1 FOREIGN KEY (role_id)
REFERENCES wechat_role (role_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE wechat_role_resources
  ADD CONSTRAINT FK_WechatRoleResources_2 FOREIGN KEY (resource_url)
REFERENCES wechat_resource (resource_url)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;