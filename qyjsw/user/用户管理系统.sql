/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/3 16:27:30                            */
/*==============================================================*/


drop table if exists t_dept;

drop table if exists t_dept_user_rel;

drop table if exists t_filedname;

drop table if exists t_filedvalue;

drop table if exists t_function;

drop table if exists t_menu;

drop table if exists t_page;

drop table if exists t_right;

drop table if exists t_role;

drop table if exists t_role_right_rel;

drop table if exists t_user;

drop table if exists t_user_role_rel;

/*==============================================================*/
/* Table: t_dept                                                */
/*==============================================================*/
create table t_dept
(
   dept_id              bigint(20) not null auto_increment comment '部门内码',
   dept_name            varchar(64) not null comment '部门名称',
   dept_introduce       varchar(100) comment '部门职能',
   dept_add             varchar(100) not null comment '办公地址',
   dept_head            varchar(64) not null comment '负责人',
   dept_tel             varchar(20) not null comment '联系电话',
   primary key (dept_id)
);

alter table t_dept comment '部门表';

/*==============================================================*/
/* Table: t_dept_user_rel                                       */
/*==============================================================*/
create table t_dept_user_rel
(
   du_id                bigint(20) not null auto_increment,
   dept_id              bigint(20),
   user_id              bigint(20),
   primary key (du_id)
);

/*==============================================================*/
/* Table: t_filedname                                           */
/*==============================================================*/
create table t_filedname
(
   field_id             bigint(20) not null auto_increment comment '唯一标识',
   table_name           varchar(64) not null comment '表名',
   field_name           varchar(64) not null comment '表字段名',
   fname_zh             varchar(64) comment '字段中文名',
   fname_en             varchar(64) comment '字段英文名',
   primary key (field_id)
);

alter table t_filedname comment '数据字典';

/*==============================================================*/
/* Table: t_filedvalue                                          */
/*==============================================================*/
create table t_filedvalue
(
   value_id             bigint(20) not null auto_increment comment '唯一标识',
   field_id             bigint(20) not null comment 'fk',
   field_name           varchar(64) not null comment '表字段名',
   field_value          varchar(64) not null comment '原生字段值',
   fvalue_zh            varchar(64) comment '中文值',
   fvalue_en            varchar(64) comment '英文值',
   primary key (value_id)
);

alter table t_filedvalue comment '数据字典';

/*==============================================================*/
/* Table: t_function                                            */
/*==============================================================*/
create table t_function
(
   fun_id               bigint(20) not null auto_increment comment '功能id',
   fun_subsystem_name   varchar(64) not null comment '子系统名称',
   fun_name             varchar(64) not null comment '功能名称（增，删，改，查）',
   primary key (fun_id)
);

alter table t_function comment '功能表';

/*==============================================================*/
/* Table: t_menu                                                */
/*==============================================================*/
create table t_menu
(
   menu_id              bigint(20) not null auto_increment comment '菜单id',
   menu_name            varchar(64) not null comment '菜单名称',
   menu_parent          bigint(20) comment '父级菜单id',
   primary key (menu_id)
);

alter table t_menu comment '菜单表';

/*==============================================================*/
/* Table: t_page                                                */
/*==============================================================*/
create table t_page
(
   page_id              bigint(20) not null auto_increment comment '页面id',
   page_url             varchar(64) not null comment '页面url',
   page_desc            varchar(200) comment '页面功能',
   primary key (page_id)
);

alter table t_page comment '页面表';

/*==============================================================*/
/* Table: t_right                                               */
/*==============================================================*/
create table t_right
(
   right_id             bigint(20) not null auto_increment comment '权限编号',
   right_name           varchar(64) not null comment '权限名称（可见，不可见，可操作，不可操作）',
   right_type           int(2) not null comment '权限类型（1:功能权限，2:页面，3:菜单，4:资源）',
   primary key (right_id)
);

alter table t_right comment '权限表';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   role_id              bigint(20) not null auto_increment comment '角色编号',
   role_name            varchar(64) not null comment '角色名称',
   primary key (role_id)
);

alter table t_role comment '角色表';

/*==============================================================*/
/* Table: t_role_right_rel                                      */
/*==============================================================*/
create table t_role_right_rel
(
   rr_id                bigint(20) not null auto_increment comment '标识',
   role_id              bigint(20) not null comment '角色id',
   ele_type             int(2) not null comment '要素类型（1::功能，2:菜单，3:页面，4:资源）',
   right_id             bigint(20) not null comment '权限id',
   primary key (rr_id)
);

alter table t_role_right_rel comment '角色权限表';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   user_id              bigint(20) not null auto_increment comment '用户编号',
   user_acc             varchar(36) not null comment '账号',
   user_pwd             varchar(36) not null comment '密码',
   user_nickname        varchar(36) comment '昵称',
   user_name            varbinary(64) not null,
   user_age             int(3) comment '年龄',
   user_gender          varchar(4) comment '性别',
   user_tel             varchar(20) not null comment '联系电话',
   user_email           varchar(36) comment '邮箱',
   user_add             varchar(64) comment '地址',
   user_position        varchar(64) not null comment '职务',
   user_note            text comment '备注',
   primary key (user_id)
);

alter table t_user comment '用户表';

/*==============================================================*/
/* Table: t_user_role_rel                                       */
/*==============================================================*/
create table t_user_role_rel
(
   ur_id                bigint(20) not null auto_increment comment '标识',
   user_id              bigint(20) not null comment '用户id',
   role_id              bigint(20) not null comment '角色id',
   primary key (ur_id)
);

alter table t_user_role_rel comment '用户角色表';

alter table t_dept_user_rel add constraint FK_Reference_1 foreign key (dept_id)
      references t_dept (dept_id) on delete restrict on update restrict;

alter table t_dept_user_rel add constraint FK_Reference_2 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_filedvalue add constraint FK_Reference_10 foreign key (field_id)
      references t_filedname (field_id) on delete restrict on update restrict;

alter table t_role_right_rel add constraint FK_Reference_5 foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

alter table t_role_right_rel add constraint FK_Reference_6 foreign key (right_id)
      references t_right (right_id) on delete restrict on update restrict;

alter table t_user_role_rel add constraint FK_Reference_3 foreign key (user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_user_role_rel add constraint FK_Reference_4 foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

