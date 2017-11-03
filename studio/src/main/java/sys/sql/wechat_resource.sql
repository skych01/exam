/*
资源，主要是URL
 */
CREATE TABLE IF NOT EXISTS wechat_resource (
  resource_url  VARCHAR(200) NOT NULL,
  method_Name   VARCHAR(200) NOT NULL,
  method_path   VARCHAR(200) NOT NULL,
  remark        VARCHAR(200),
  create_date   DATETIME     NOT NULL,
  PRIMARY KEY (resource_url)
)
  DEFAULT CHARSET = utf8;