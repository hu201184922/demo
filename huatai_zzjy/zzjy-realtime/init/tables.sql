--------------------------------------------------
-- Export file for user EZT                     --
-- Created by jinchenfly on 2017/3/28, 11:08:39 --
--------------------------------------------------

--set define off
--spool tables.log

--prompt
--prompt Creating table JDP_DICT
--prompt =======================
--prompt
create table JDP_DICT
(
  cid        NUMBER(19) not null,
  name_      VARCHAR2(255 CHAR),
  parent_id  NUMBER(19),
  sort_index NUMBER(10),
  code_      VARCHAR2(255 CHAR),
  descript_  VARCHAR2(255 CHAR),
  distabled_ NUMBER(1),
  system_    NUMBER(1)
)
;
create index HUATAI3.FK_8QVCQCEKN4GE19PO7RP8AS237 on JDP_DICT (PARENT_ID);

--prompt
--prompt Creating table JDP_DICT_ITEM
--prompt ============================
--prompt
create table JDP_DICT_ITEM
(
  cid        NUMBER(19) not null,
  code_      VARCHAR2(255 CHAR),
  descript   VARCHAR2(255 CHAR),
  disabled_  NUMBER(1),
  name_      VARCHAR2(255 CHAR),
  pid        NUMBER(19),
  sort_index NUMBER(10),
  dict_id    NUMBER(19)
)
;
create index HUATAI3.FK_NP7SL0LG5F4XHJIQP6266AKMO on JDP_DICT_ITEM (DICT_ID);

--prompt
--prompt Creating table JDP_LOG
--prompt ======================
--prompt
create table JDP_LOG
(
  cid             NUMBER(19) not null,
  action          VARCHAR2(255 CHAR),
  browser_version VARCHAR2(255 CHAR),
  descript        VARCHAR2(2000 CHAR),
  error_msg       VARCHAR2(4000 CHAR),
  ip              VARCHAR2(255 CHAR),
  login_name      VARCHAR2(255 CHAR),
  method          VARCHAR2(255 CHAR),
  method_handler  VARCHAR2(1000 CHAR),
  os_version      VARCHAR2(255 CHAR),
  parameters_     VARCHAR2(2000 CHAR),
  refererurl      VARCHAR2(255 CHAR),
  server_ip       VARCHAR2(255 CHAR),
  session_id      VARCHAR2(255 CHAR),
  time            TIMESTAMP(6),
  url             VARCHAR2(255 CHAR)
)
;
alter table JDP_LOG
  add primary key (CID);

--prompt
--prompt Creating table JDP_MENU_ITEM
--prompt ============================
--prompt
create table JDP_MENU_ITEM
(
  cid          NUMBER(19) not null,
  name_        VARCHAR2(255 CHAR),
  parent_id    NUMBER(19),
  sort_index   NUMBER(10),
  description_ VARCHAR2(255 CHAR),
  disable_icon VARCHAR2(255 CHAR),
  enabled_     NUMBER(1),
  icon_        VARCHAR2(255 CHAR),
  meta1        VARCHAR2(255 CHAR),
  meta2        VARCHAR2(255 CHAR),
  meta3        VARCHAR2(255 CHAR),
  perm_string  VARCHAR2(255 CHAR),
  type_        NUMBER(10),
  url_         VARCHAR2(255 CHAR),
  visible_     NUMBER(1)
)
;

--prompt
--prompt Creating table JDP_MT_ROLE_RES
--prompt ==============================
--prompt
create table JDP_MT_ROLE_RES
(
  cid     NUMBER(19) not null,
  res_id  NUMBER(19),
  role_id NUMBER(19)
)
;
create index HUATAI3.FK_DKIKDF3EAOOA5QX01WE2PYPRU on JDP_MT_ROLE_RES (ROLE_ID);
create index HUATAI3.FK_TAQEC96PVTJWKYJK04SEFTU1B on JDP_MT_ROLE_RES (RES_ID);

--prompt
--prompt Creating table JDP_MT_USER_ROLE
--prompt ===============================
--prompt
create table JDP_MT_USER_ROLE
(
  cid     NUMBER(19) not null,
  role_id NUMBER(19),
  user_id NUMBER(19)
)
;
create index HUATAI3.FK_3A52FCEL5WMOV1F0CK9WYK107 on JDP_MT_USER_ROLE (ROLE_ID);
create index HUATAI3.FK_J268OX2IPQ4EV2GV8U0PF7CBW on JDP_MT_USER_ROLE (USER_ID);

--prompt
--prompt Creating table JDP_ORGANIZATION
--prompt ===============================
--prompt
create table JDP_ORGANIZATION
(
  orgid   NUMBER(20) not null,
  orgname VARCHAR2(100),
  levels  CHAR(1),
  porgid  NUMBER(20),
  orgcode VARCHAR2(32)
)
;
comment on column JDP_ORGANIZATION.orgid
  is '组织编号';
comment on column JDP_ORGANIZATION.orgname
  is '组织名称';
comment on column JDP_ORGANIZATION.levels
  is '组织级别';
comment on column JDP_ORGANIZATION.porgid
  is '上级编号';
comment on column JDP_ORGANIZATION.orgcode
  is '组织代码';

--prompt
--prompt Creating table JDP_RESOURCE
--prompt ===========================
--prompt
create table JDP_RESOURCE
(
  cid        NUMBER(19) not null,
  name_      VARCHAR2(255 CHAR),
  parent_id  NUMBER(19),
  sort_index NUMBER(10),
  descript_  VARCHAR2(255 CHAR),
  enabled_   NUMBER(1),
  per_string VARCHAR2(255 CHAR),
  res_string VARCHAR2(255 CHAR),
  res_type   VARCHAR2(255 CHAR)
)
;
create index HUATAI3.FK_D5M69H8XM922NL57QHTU6JJPW on JDP_RESOURCE (PARENT_ID);
alter table JDP_RESOURCE
  add primary key (CID);

--prompt
--prompt Creating table JDP_ROLE
--prompt =======================
--prompt
create table JDP_ROLE
(
  cid         NUMBER(19) not null,
  code_       VARCHAR2(255 CHAR),
  description VARCHAR2(255 CHAR),
  href        VARCHAR2(255 CHAR),
  rolename    VARCHAR2(255 CHAR),
  type        VARCHAR2(255 CHAR) default '00'
)
;
comment on column JDP_ROLE.type
  is '类型 00管理 01内勤 02外勤';
create index JDP_INX_CODE on JDP_ROLE (CODE_);
alter table JDP_ROLE
  add primary key (CID);

--prompt
--prompt Creating table JDP_USER
--prompt =======================
--prompt
create table JDP_USER
(
  cid                      NUMBER(19) not null,
  account                  VARCHAR2(32 CHAR) not null,
  accountstate             VARCHAR2(255 CHAR) default '01',
  channel                  VARCHAR2(255 CHAR),
  city                     VARCHAR2(24 CHAR),
  credentials_expired      NUMBER(1),
  credentials_expired_time TIMESTAMP(6),
  description              VARCHAR2(255 CHAR),
  email                    VARCHAR2(255 CHAR),
  enabled_                 NUMBER(1),
  errortrytime             TIMESTAMP(6),
  expired_                 NUMBER(1),
  expired_time             TIMESTAMP(6),
  first_logined            NUMBER(1),
  first_logined_time       TIMESTAMP(6),
  idno                     VARCHAR2(255 CHAR),
  is_admin                 NUMBER(1),
  istype                   VARCHAR2(255 CHAR) default '00',
  last_logined_time        TIMESTAMP(6),
  locked_                  NUMBER(1) default '0',
  locked_time              TIMESTAMP(6),
  loginnum                 NUMBER(19),
  org_code                 VARCHAR2(255 CHAR),
  password                 VARCHAR2(255 CHAR),
  phone                    VARCHAR2(255 CHAR),
  province                 VARCHAR2(10 CHAR),
  psw_change_date          TIMESTAMP(6),
  puser_id                 NUMBER(19),
  register_date            TIMESTAMP(6),
  role_id                  NUMBER(19),
  salt_                    VARCHAR2(255 CHAR),
  sex                      VARCHAR2(1 CHAR) default '0',
  username                 VARCHAR2(32 CHAR) not null,
  pic_path                 VARCHAR2(400),
  smcode                   VARCHAR2(12),
  smname                   VARCHAR2(60),
  amcdoe                   VARCHAR2(12),
  amname                   VARCHAR2(60),
  rmcode                   VARCHAR2(12),
  rmname                   VARCHAR2(60),
  org_grade                VARCHAR2(4),
  branchattr               VARCHAR2(20),
  modify_time              TIMESTAMP(6),
  modify_by                VARCHAR2(32 CHAR),
  smbrach                  VARCHAR2(20),
  ambrach                  VARCHAR2(20),
  rmbrach                  VARCHAR2(20),
  cus_update_time          TIMESTAMP(0),
  adname                   VARCHAR2(60),
  adcode                   VARCHAR2(20),
  adbrach                  VARCHAR2(20),
  org_short_name           VARCHAR2(255),
  org_name                 VARCHAR2(255)
)
;
comment on column JDP_USER.account
  is '账号';
comment on column JDP_USER.accountstate
  is '在职状态03：离职；01在职';
comment on column JDP_USER.channel
  is '通道 不用字段';
comment on column JDP_USER.city
  is '城市CODE 不用字段';
comment on column JDP_USER.description
  is '描述';
comment on column JDP_USER.email
  is '邮箱';
comment on column JDP_USER.enabled_
  is '1：有效；0：无效';
comment on column JDP_USER.expired_
  is '1：过期；0：未过期';
comment on column JDP_USER.expired_time
  is '过期时间';
comment on column JDP_USER.first_logined_time
  is '首次登陆时间';
comment on column JDP_USER.idno
  is '身份证号';
comment on column JDP_USER.is_admin
  is '是否为管理员 1是 NULL否';
comment on column JDP_USER.istype
  is '类型 00 管理 01：内勤；02：外勤';
comment on column JDP_USER.last_logined_time
  is '上一次登陆时间';
comment on column JDP_USER.locked_
  is '0:未锁 1:锁定';
comment on column JDP_USER.locked_time
  is '锁定时间';
comment on column JDP_USER.loginnum
  is '登陆次数';
comment on column JDP_USER.org_code
  is '组织CODE, 使用英文逗号分隔,前后没有逗号';
comment on column JDP_USER.password
  is '密码';
comment on column JDP_USER.phone
  is '手机';
comment on column JDP_USER.province
  is '省CODE 不用字段';
comment on column JDP_USER.puser_id
  is '父级ACCOUNT 不用字段';
comment on column JDP_USER.register_date
  is '注册日期';
comment on column JDP_USER.role_id
  is '权限id';
comment on column JDP_USER.salt_
  is '盐';
comment on column JDP_USER.sex
  is '性别 0男 1女';
comment on column JDP_USER.username
  is '用户名';
comment on column JDP_USER.pic_path
  is '头像';
comment on column JDP_USER.org_grade
  is '业务职级：CAO|AH1|AH2|DM|AD|AM|SM|LP 管理：SA';
comment on column JDP_USER.branchattr
  is '机构CODE';
comment on column JDP_USER.modify_time
  is '修改日期';
comment on column JDP_USER.modify_by
  is '修改人account';
comment on column JDP_USER.smbrach
  is '组号';
comment on column JDP_USER.ambrach
  is '处号';
comment on column JDP_USER.rmbrach
  is '区号';
comment on column JDP_USER.cus_update_time
  is '客户更新时间';
create index JDP_INX_LOGIN_NAME on JDP_USER (ACCOUNT);
alter table JDP_USER
  add primary key (CID);

--prompt
--prompt Creating table JDP_USER_HIS
--prompt ===========================
--prompt
create table JDP_USER_HIS
(
  cid          NUMBER(19) not null,
  account      VARCHAR2(32),
  username     VARCHAR2(32 CHAR),
  org_code     VARCHAR2(255 CHAR),
  org_grade    VARCHAR2(4),
  accountstate VARCHAR2(10),
  enabled_     NUMBER(1),
  expired_     NUMBER(1),
  locked_      NUMBER(1),
  istype       VARCHAR2(10),
  smcode       VARCHAR2(12),
  smname       VARCHAR2(60),
  amcode       VARCHAR2(12),
  amname       VARCHAR2(60),
  rmcode       VARCHAR2(12),
  rmname       VARCHAR2(60),
  smbrach      VARCHAR2(20),
  ambrach      VARCHAR2(20),
  rmbrach      VARCHAR2(20),
  branchattr   VARCHAR2(20),
  modify_time  TIMESTAMP(6),
  backup_time  DATE not null,
  type         VARCHAR2(4)
)
;
comment on column JDP_USER_HIS.type
  is '类型：0全量 1增量';

--prompt
--prompt Creating sequence SEQ_JDP_DICT
--prompt ==============================
--prompt
create sequence SEQ_JDP_DICT
minvalue 1
maxvalue 9999999999999999999999999999
start with 141
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_DICT_ITEM
--prompt ===================================
--prompt
create sequence SEQ_JDP_DICT_ITEM
minvalue 1
maxvalue 9999999999999999999999999999
start with 7061
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_LOG
--prompt =============================
--prompt
create sequence SEQ_JDP_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 56910
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_MENU_ITEM
--prompt ===================================
--prompt
create sequence SEQ_JDP_MENU_ITEM
minvalue 1
maxvalue 9999999999999999999999999999
start with 81
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_MT_ROLE_RES
--prompt =====================================
--prompt
create sequence SEQ_JDP_MT_ROLE_RES
minvalue 1
maxvalue 9999999999999999999999999999
start with 17661
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_MT_USER_ROLE
--prompt ======================================
--prompt
create sequence SEQ_JDP_MT_USER_ROLE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_ORGANIZATION
--prompt ======================================
--prompt
create sequence SEQ_JDP_ORGANIZATION
minvalue 1
maxvalue 9999999
start with 1
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_RESOURCE
--prompt ==================================
--prompt
create sequence SEQ_JDP_RESOURCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 761
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_ROLE
--prompt ==============================
--prompt
create sequence SEQ_JDP_ROLE
minvalue 1
maxvalue 9999999999999999999999999999
start with 81
increment by 1
cache 20;

--prompt
--prompt Creating sequence SEQ_JDP_USER
--prompt ==============================
--prompt
create sequence SEQ_JDP_USER
minvalue 1
maxvalue 9999999999999999999999999999
start with 10
increment by 1
cache 20;


--spool off
