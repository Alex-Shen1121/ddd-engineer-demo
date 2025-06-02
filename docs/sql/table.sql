/*
 Navicat Premium Dump SQL

 Source Server         : 本机 Mysql
 Source Server Type    : MySQL
 Source Server Version : 80405 (8.4.5)
 Source Host           : localhost:3306
 Source Schema         : codingshen-saas

 Target Server Type    : MySQL
 Target Server Version : 80405 (8.4.5)
 File Encoding         : 65001

 Date: 02/06/2025 22:32:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
                           `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '账号ID',
                           `tenant_id` bigint unsigned NOT NULL COMMENT '所属租户ID',
                           `org_id` bigint unsigned NOT NULL COMMENT '主关联组织机构节点ID',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                           `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                           PRIMARY KEY (`id`),
                           KEY `idx_tenant` (`tenant_id`),
                           KEY `idx_org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='统一账号主表（支持多登录方式）';

-- ----------------------------
-- Table structure for account_login_method
-- ----------------------------
DROP TABLE IF EXISTS `account_login_method`;
CREATE TABLE `account_login_method` (
                                        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '登录方式ID',
                                        `account_id` bigint unsigned NOT NULL COMMENT '关联账号ID',
                                        `tenant_id` bigint unsigned NOT NULL COMMENT '继承自account的租户ID',
                                        `org_id` bigint unsigned NOT NULL COMMENT '继承自account的机构ID',
                                        `login_type` varchar(16) NOT NULL COMMENT '登录类型（wechat-微信，alipay-支付宝，github-Github，email-邮件，mobile-手机）',
                                        `identifier` varchar(255) NOT NULL COMMENT '登录标识（微信openId/手机/邮箱等）',
                                        `credential` varchar(255) DEFAULT NULL COMMENT '凭证（密码哈希/短信验证码等，可选）',
                                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                        `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `login_type` (`login_type`,`identifier`),
                                        KEY `idx_login_identifier` (`login_type`,`identifier`),
                                        KEY `idx_tenant` (`tenant_id`),
                                        KEY `idx_org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号多登录方式明细表';

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '员工ID',
                            `tenant_id` bigint unsigned NOT NULL COMMENT '所属租户ID',
                            `org_id` bigint unsigned NOT NULL COMMENT '所属组织机构节点ID',
                            `account_id` bigint unsigned NOT NULL COMMENT '关联账号ID',
                            `real_name` varchar(32) NOT NULL COMMENT '员工姓名',
                            `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
                            `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
                            `position` varchar(32) DEFAULT NULL COMMENT '岗位',
                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                            `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `mobile` (`mobile`),
                            UNIQUE KEY `email` (`email`),
                            KEY `idx_tenant` (`tenant_id`),
                            KEY `idx_org` (`org_id`),
                            KEY `idx_account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工与账号/组织机构绑定表';

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
                                 `employee_id` bigint unsigned NOT NULL COMMENT '员工ID',
                                 `role_id` bigint unsigned NOT NULL COMMENT '角色ID',
                                 `tenant_id` bigint unsigned NOT NULL COMMENT '继承自employee的租户ID',
                                 `org_id` bigint unsigned NOT NULL COMMENT '继承自employee的机构ID',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                 `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                                 PRIMARY KEY (`employee_id`,`role_id`),
                                 KEY `idx_tenant` (`tenant_id`),
                                 KEY `idx_org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工-角色绑定表';

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
                                `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '组织机构ID',
                                `tenant_id` bigint unsigned NOT NULL COMMENT '所属租户ID',
                                `org_id` bigint unsigned NOT NULL COMMENT '组织机构ID',
                                `parent_org_id` bigint unsigned DEFAULT '0' COMMENT '父级机构ID（0表示顶级）',
                                `org_level` tinyint DEFAULT '1' COMMENT '机构层级（总部为1级）',
                                `org_name` varchar(64) NOT NULL COMMENT '机构名称',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                                PRIMARY KEY (`id`),
                                KEY `idx_tenantId_orgId` (`tenant_id`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组织机构层级表（含总部）';

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
                              `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
                              `tenant_id` bigint unsigned NOT NULL COMMENT '所属租户ID',
                              `org_id` bigint unsigned DEFAULT NULL COMMENT '所属组织机构节点ID（系统级权限可空）',
                              `perm_name` varchar(64) NOT NULL COMMENT '权限名称（如：用户列表查看）',
                              `perm_code` varchar(64) NOT NULL COMMENT '权限编码（如：user:list:view）',
                              `perm_type` varchar(16) NOT NULL COMMENT '权限类型（menu-菜单，url-接口，button-按钮）',
                              `system_code` varchar(32) DEFAULT NULL COMMENT '所属系统编码（如：saas-platform）',
                              `module_code` varchar(32) DEFAULT NULL COMMENT '所属模块编码（如：user-management）',
                              `parent_perm_id` bigint unsigned DEFAULT '0' COMMENT '父权限ID（用于菜单层级）',
                              `sort` int DEFAULT '0' COMMENT '权限排序（控制菜单/按钮显示顺序，值越小越靠前）',
                              `icon` varchar(128) DEFAULT NULL COMMENT '菜单图标（存储图标类名或URL，如：el-icon-setting）',
                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                              `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `perm_code` (`perm_code`),
                              KEY `idx_tenant` (`tenant_id`),
                              KEY `idx_org` (`org_id`),
                              KEY `idx_perm_code` (`perm_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='细粒度权限定义表';

-- ----------------------------
-- Table structure for right_control
-- ----------------------------
DROP TABLE IF EXISTS `right_control`;
CREATE TABLE `right_control` (
                                 `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '权益ID',
                                 `tenant_id` bigint unsigned NOT NULL COMMENT '所属租户ID',
                                 `org_id` bigint unsigned NOT NULL COMMENT '所属组织机构节点ID',
                                 `hq_org_id` bigint unsigned DEFAULT NULL COMMENT '关联的总部ID（可选，总部直接发放时填写）',
                                 `subscription_id` bigint unsigned DEFAULT NULL COMMENT '关联的订购记录ID（通过服务包订购时必填）',
                                 `package_id` bigint unsigned DEFAULT NULL COMMENT '关联的服务包ID（冗余字段，便于查询）',
                                 `right_type` varchar(16) NOT NULL COMMENT '权益类型（service_package-服务包订购，direct-直接发放）',
                                 `target_code` varchar(64) NOT NULL COMMENT '权益目标编码（对应服务包编码或直接发放标识）',
                                 `effective_start` datetime NOT NULL COMMENT '生效开始时间',
                                 `effective_end` datetime NOT NULL COMMENT '生效结束时间',
                                 `description` text COMMENT '权益描述',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                 `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除（0-未删，1-已删）',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_tenant` (`tenant_id`),
                                 KEY `idx_org` (`org_id`),
                                 KEY `idx_subscription` (`subscription_id`),
                                 KEY `idx_package` (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组织机构权益表';

-- ----------------------------
-- Table structure for right_subscription
-- ----------------------------
DROP TABLE IF EXISTS `right_subscription`;
CREATE TABLE `right_subscription` (
                                      `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '订购记录ID',
                                      `org_id` bigint unsigned NOT NULL COMMENT '订购机构ID（关联organization.id）',
                                      `package_id` bigint unsigned NOT NULL COMMENT '订购的服务包ID（关联service_package.id）',
                                      `effective_start` datetime NOT NULL COMMENT '生效开始时间',
                                      `effective_end` datetime NOT NULL COMMENT '生效结束时间',
                                      `status` tinyint DEFAULT '1' COMMENT '状态（1-生效中，2-已过期，3-已取消）',
                                      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                      `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_org` (`org_id`),
                                      KEY `idx_package` (`package_id`),
                                      KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权益服务包订购记录表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                        `tenant_id` bigint unsigned NOT NULL COMMENT '所属租户ID',
                        `org_id` bigint unsigned DEFAULT NULL COMMENT '所属组织机构节点ID（租户级角色可空）',
                        `role_name` varchar(64) NOT NULL COMMENT '角色名称（如：系统管理员）',
                        `role_code` varchar(64) NOT NULL COMMENT '角色编码（如：sys_admin）',
                        `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                        `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `tenant_id` (`tenant_id`,`role_code`),
                        KEY `idx_tenant` (`tenant_id`),
                        KEY `idx_org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='RBAC角色表';

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
                                   `role_id` bigint unsigned NOT NULL COMMENT '角色ID',
                                   `perm_id` bigint unsigned NOT NULL COMMENT '权限ID',
                                   `tenant_id` bigint unsigned NOT NULL COMMENT '继承自role的租户ID',
                                   `org_id` bigint unsigned NOT NULL COMMENT '继承自role的机构ID',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                   `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                                   PRIMARY KEY (`role_id`,`perm_id`),
                                   KEY `idx_tenant` (`tenant_id`),
                                   KEY `idx_org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限绑定表';

-- ----------------------------
-- Table structure for service_package
-- ----------------------------
DROP TABLE IF EXISTS `service_package`;
CREATE TABLE `service_package` (
                                   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '服务包ID',
                                   `package_name` varchar(128) NOT NULL COMMENT '服务包名称（如：基础版、高级版）',
                                   `package_code` varchar(64) NOT NULL COMMENT '服务包唯一编码',
                                   `description` text COMMENT '服务包描述',
                                   `duration_months` tinyint DEFAULT '12' COMMENT '默认有效期（月数）',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                   `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除（0-未删，1-已删）',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `package_code` (`package_code`),
                                   KEY `idx_package_code` (`package_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务包定义表（权限集合）';

-- ----------------------------
-- Table structure for service_package_permission
-- ----------------------------
DROP TABLE IF EXISTS `service_package_permission`;
CREATE TABLE `service_package_permission` (
                                              `package_id` bigint unsigned NOT NULL COMMENT '服务包ID（关联service_package.id）',
                                              `perm_id` bigint unsigned NOT NULL COMMENT '权限ID（关联permission.id）',
                                              `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                              `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                              `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                                              `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除',
                                              PRIMARY KEY (`package_id`,`perm_id`),
                                              KEY `idx_package` (`package_id`),
                                              KEY `idx_permission` (`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务包与权限绑定表';

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                          `tenant_id` bigint unsigned NOT NULL COMMENT '租户ID',
                          `tenant_name` varchar(128) NOT NULL COMMENT '租户名称',
                          `contact_person` varchar(32) DEFAULT NULL COMMENT '租户联系人',
                          `contact_phone` varchar(16) DEFAULT NULL COMMENT '联系电话',
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          `operatorId` bigint unsigned DEFAULT NULL COMMENT '操作人ID（关联employee.id）',
                          `deleted` tinyint DEFAULT '0' COMMENT '逻辑删除（0-未删，1-已删）',
                          PRIMARY KEY (`id`),
                          KEY `idx_tenantId` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户基础信息表';

SET FOREIGN_KEY_CHECKS = 1;
