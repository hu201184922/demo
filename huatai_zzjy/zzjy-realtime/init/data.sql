--prompt PL/SQL Developer import file
--prompt Created on 2017年3月28日 by jinchenfly
--set feedback off
--set define off
--prompt Loading JDP_DICT...
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (125, '产品主险类型', 2, 0, 'product_type', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (21, '角色类型', 2, 0, 'crm.system.roletype', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (5, '证件类型', 2, 0, 'CERTTYPE_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (8, '收入', 2, 0, 'INCOME_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (7, '学历', 2, 0, 'EDUCATION_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (9, '客户来源', 2, 0, 'CUSTOMER_SOURCE_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (10, '生日自定义提醒设置', 2, 0, 'BIRTH_REMIND_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (11, '特殊纪念日自定义提醒设置', 2, 0, 'SPECIALDAY_REMIND_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (12, '任务提醒设置方式', 2, 0, 'TASK_REMIND_TYPE_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (13, '个人计划子类型', 2, 0, 'PERSON_PLAN_TYPE_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (14, '个人计划提醒设置类型', 2, 0, 'PERSON_PLAN_REMIND_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (15, '微信配置', 2, 0, 'wechat', '微信配置信息', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (41, '默认密码', 2, 0, 'default_password', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (43, '国籍', 2, null, 'COUNTRY_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (44, '性别', 2, null, 'SEX_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (2, '系统字典', null, 0, 'SYSTEM.DICT', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (3, '客户关系', 2, 0, 'CUSTOMER_RELATE_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (4, '任务阶段', 2, 0, 'TASK_STAGE_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (42, '地址', 2, 0, 'ADDRESS_CODE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (61, '上线配置', 2, 0, 'online-config', '上线配置', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (81, 'OP任务环节', 2, 0, 'OP', 'OP任务环节', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (82, 'PC任务环节', 2, 0, 'PC', 'PC任务环节', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (83, 'PD任务环节', 2, 0, 'PD', 'PD任务环节', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (101, '大都会E展通', 15, 0, 'localhost', '大都会E展通', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (102, 'localhost', 15, 0, 'wechat_domain', 'wechat_domain' || chr(10) || '', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (85, '知识类型', null, null, 'knowledgeType', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (122, 'Map学历', 2, 0, 'MAP_EDUCATION', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (123, 'MAP地址类型', 2, 0, 'MAP_ADDRESS_TYPE', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (121, 'Map客户类型', 2, 0, 'MAP_CUSTOMER_TYPE', 'MAP中的客户类型', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (124, 'MAP性别', 2, 0, 'MAP_GENDER', null, 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (111, 'Y', 15, 0, 'WECHAT_ANNIVERSARY_ONLY_FIRSTDAY_SEND', '微信端保单周年提醒', 0, null);
insert into JDP_DICT (cid, name_, parent_id, sort_index, code_, descript_, distabled_, system_)
values (1, '系统邮箱', 2, 0, 'system_email', '系统邮箱配置', 0, null);
commit;
--prompt 32 records loaded
--prompt Loading JDP_DICT_ITEM...
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6961, '1', null, 0, '健康', null, 1, 125);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6962, '2', null, 0, '意外', null, 2, 125);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6963, '3', null, 0, '人寿', null, 3, 125);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6964, '4', null, 0, '养老', null, 4, 125);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6965, '5', null, 0, '理财', null, 5, 125);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6827, 'online-time', '上线时间', 0, '2016-06-05 11:11:11', 0, 1, 61);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6841, 'OP', 'OP', 0, 'OP', null, 1, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6842, '1', '签单成功', 0, '签单成功', null, 1, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6843, '2', '见面成功,获得FF,预约PC' || chr(10) || '', 0, '见面成功,获得FF,预约PC', null, 2, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6844, '3', '见面成功,获得FF' || chr(10) || '', 0, '见面成功,获得FF', null, 3, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6845, '4', '见面成功,未获得FF' || chr(10) || '', 0, '见面成功,未获得FF', null, 4, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6846, '5', '未见改约' || chr(10) || '', 0, '未见改约', null, 5, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6847, '6', '拒绝见面' || chr(10) || '', 0, '拒绝见面', null, 6, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6848, '1', '签单成功' || chr(10) || '', 0, '签单成功', null, 1, 82);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6849, '2', '见面成功，客户延期' || chr(10) || '', 0, '见面成功，客户延期', null, 2, 82);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6850, '3', '见面成功，客户拒绝' || chr(10) || '', 0, '见面成功，客户拒绝', null, 3, 82);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6851, '4', '未见改约' || chr(10) || '', 0, '未见改约', null, 4, 82);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6852, '5', '拒绝见面' || chr(10) || '', 0, '拒绝见面', null, 5, 82);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6853, '1', '成功递送,回执签收' || chr(10) || '', 0, '成功递送,回执签收', null, 1, 83);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6854, '2', '成功见面,客户拒收' || chr(10) || '', 0, '成功见面,客户拒收', null, 2, 83);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6855, '3', '未见改约' || chr(10) || '', 0, '未见改约', null, 3, 83);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6856, '4', '撤单' || chr(10) || '', 0, '撤单', null, 4, 83);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6857, '1', '签单成功', 0, '签单成功', null, 1, 81);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6858, '2', '见面成功,获得FF,预约PC', 0, '见面成功,获得FF,预约PC', null, 2, 81);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6859, '3', '见面成功,获得FF', 0, '见面成功,获得FF', null, 3, 81);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6860, '4', '见面成功,未获得FF', 0, '见面成功,未获得FF', null, 4, 81);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6861, '5', '未见改约', 0, '未见改约', null, 5, 81);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6862, '6', '拒绝见面', 0, '拒绝见面', null, 6, 81);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (86, '02', null, 0, '外勤', null, 2, 21);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (85, '01', null, 0, '内勤', null, 1, 21);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (87, '00', null, 0, '管理', null, 0, 21);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (15, '1', null, 0, '身份证', null, 1, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (16, '2', null, 0, '军官证', null, 2, null);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (17, 'OP', null, 0, '初次晤谈', null, 1, 4);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (20, 'PC', null, 0, '建议书说明及促成', null, 4, 4);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (21, 'PD', null, 0, '保单递送', null, 5, 4);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (24, '1', null, 0, '博士', null, 1, 7);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (25, '2', null, 0, '硕士', null, 2, 7);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (26, '3', null, 0, '本科', null, 3, 7);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (27, '4', null, 0, '大专', null, 4, 7);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (28, '1', null, 0, '50万以上', null, 1, 8);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (29, '2', null, 0, '30万-50万', null, 2, 8);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (30, '3', null, 0, '20万-30万', null, 3, 8);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (31, '4', null, 0, '10万-20万', null, 4, 8);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (32, '5', null, 0, '5万-10万', null, 5, 8);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (33, '6', null, 0, '5万以下', null, 6, 8);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (34, '1', null, 0, '陌生', null, 1, 9);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (35, '2', null, 0, '缘故', null, 2, 9);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (36, '3', null, 0, '转介绍', null, 3, 9);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (37, '4', null, 0, '公司项目', null, 4, 9);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (47, '1', null, 0, '1小时', null, 2, 12);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (39, '2', null, 0, '提前一周', null, 2, 10);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (40, '3', null, 0, '提前三天', null, 3, 10);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (44, '2', null, 0, '提前一周', null, 2, 11);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (42, '1', null, 0, '提前两周', null, 1, 10);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (43, '1', null, 0, '提前两周', null, 1, 11);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (45, '3', null, 0, '提前三天', null, 3, 11);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (46, '0.5', null, 0, '0.5小时', null, 1, 12);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (48, '2', null, 0, '2小时', null, 3, 12);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (49, '1', null, 0, '会议', null, 1, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (50, '2', null, 0, '培训', null, 2, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (51, '3', null, 0, '电话约访', null, 3, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (52, '4', null, 0, 'BTP', null, 4, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (53, '5', null, 0, 'MID', null, 5, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (54, '6', null, 0, 'J/W', null, 6, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (55, '7', null, 0, 'R/P', null, 7, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (56, '8', null, 0, 'PRP', null, 8, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (57, '9', null, 0, 'OT', null, 9, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (58, '10', null, 0, 'Case Study', null, 10, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (59, '11', null, 0, 'ISPC', null, 11, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (60, '12', null, 0, '招募follow-up', null, 12, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (61, '13', null, 0, '招募REF', null, 13, 13);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (62, '0.5', null, 0, '0.5小时', null, 1, 14);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (63, '1', null, 0, '1小时', null, 2, 14);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (64, '2', null, 0, '2小时', null, 3, 14);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (102, 'default_password', null, 0, '111111a', null, null, 41);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (8, '4', '2_3', 0, '子女', null, 4, 3);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6, '2', '4', 0, '父亲', null, 2, 3);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (4, '1', '1', 0, '配偶', null, 1, 3);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (7, '3', '4', 0, '母亲', null, 3, 3);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6881, 'version', null, 0, '1', null, 0, 42);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3531, '01', null, 0, '中国', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3448, 'M', null, null, '男', null, 0, 44);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3533, '03', null, 0, '其他', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3532, '02', null, 0, '美国', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3449, 'F', null, null, '女', null, null, 44);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3534, 'AU', null, 0, '澳大利亚', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3535, 'CA', null, 0, '加拿大', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3536, 'CU', null, 0, '古巴', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3537, 'DE', null, 0, '德国', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3538, 'GB', null, 0, '英国', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3539, 'HK', null, 0, '中国香港', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3540, 'IR', null, 0, '伊朗', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3541, 'JP', null, 0, '日本', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3542, 'KP', null, 0, '朝鲜', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3543, 'KR', null, 0, '韩国', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3544, 'MO', null, 0, '中国澳门', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3545, 'MY', null, 0, '马来西亚', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3546, 'SD', null, 0, '苏丹', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3547, 'SG', null, 0, '新加坡', null, null, 43);
commit;
--prompt 100 records committed...
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3548, 'SY', null, 0, '叙利亚', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3549, 'TW', null, 0, '中国台湾', null, null, 43);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3519, '1', '01', 0, '身份证', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3520, '2', '03', 0, '护照', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3521, '3', '00', 0, '其他', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3522, '3', '01', 0, '其他（出生证）', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3523, '3', 'HK', 0, '其他（出生证）', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3524, '3', 'MO', 0, '其他（出生证）', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3525, '3', 'TW', 0, '其他（出生证）', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3526, '3', '03', 0, '其他（出生证）', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3527, '4', '01', 0, '军人证', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3528, '5', 'TW', 0, '台胞证', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3529, '6', 'HK', 0, '港澳通行证', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (3530, '6', 'MO', 0, '港澳通行证', null, null, 5);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6982, 'CorpID', '大都会E展通PROD环境CorpID', 0, 'wx9625b017e43f9bf0', null, 2, 101);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6984, 'agentid', '大都会E展通PROD环境agentid', 0, '11', null, null, 101);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6986, 'secret', '大都会E展通secret', 0, 'Z4yvBbz1l2hSU1WIaIoltJrI0R5fEyH6e-NVIXfe-QzE8i0HcNEIe3xCIP6PeNy5', null, null, 101);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6921, '3', null, 0, '3小时', null, 4, 12);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6941, '3', '3小时', 0, '3小时', null, 4, 14);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6942, 'F', '女', 0, '1', null, null, 124);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6943, '0', '潜在客户', 0, 'LC', null, null, 121);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6882, '竞赛发布', null, null, '竞赛发布', null, 1, 85);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6883, '产品解读', null, null, '产品解读', null, 2, 85);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6884, '会议信息', null, null, '会议信息', null, 3, 85);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6885, '活动通知', null, null, '活动通知', null, 4, 85);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6886, '其他', null, null, '其他', null, 5, 85);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6924, '1', '博士以上', 0, '0', null, null, 122);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6925, '2', '硕士', 0, '1', null, null, 122);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6926, '4', '大专', 0, '3', null, null, 122);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6927, '3', '大本', 0, '2', null, null, 122);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6929, '5', '中专', 0, '4', null, null, 122);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6930, '0', '家庭地址', 0, 'HA', null, null, 123);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6931, '2', '单位地址', 0, 'UA', null, null, 123);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6932, '1', '其他地址', 0, 'OA', null, null, 123);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6944, '1', '准客户', 0, 'PC', null, null, 121);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6945, '2', '承保客户', 0, 'AIC', null, null, 121);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (6946, 'M', '男', 0, '0', null, null, 124);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (65, '6', '高中', 0, '5', null, null, 122);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (66, '5', null, 0, '中专', null, 5, 7);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (67, '6', null, 0, '高中', null, 6, 7);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (110, 'sys_send_from', '发送人邮箱', null, 'jiangbingbin@fulan.com.cn', null, null, 1);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (111, 'sys_send_pwd', '发送人密码', null, '1111.ab', null, null, 1);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (112, 'sys_send_name', '发送人姓名', null, 'TASKJOB', null, null, 1);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (113, 'sys_send_stmp', '发送服务器', null, 'mail.fulan.com.cn', null, null, 1);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (114, 'sys_send_auth', '发送验证', null, 'true', null, null, 1);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (115, 'sys_send_to', '接收人邮箱(英文逗号分隔)', null, 'jiangbingbin@fulan.com.cn', null, null, 1);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (200, '6', null, 0, '教育', null, 6, 125);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (7046, 'access_token_start_time', '微信企业号AccessToken创建时间', 0, '1476088910274', 101, null, 101);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (7044, 'access_token', '微信企业号AccessToken', 0, 'ry1lzijDa4DtbFmpzQAWMlej4dWsdmdLSuReCm-lXtMDzgcGSxG9A4zMrS4VEslL', 101, null, 101);
insert into JDP_DICT_ITEM (cid, code_, descript, disabled_, name_, pid, sort_index, dict_id)
values (7045, 'access_token_expire_in', '微信企业号AccessToken有效期', 0, '10', 101, null, 101);
commit;
--prompt 150 records loaded
--prompt Loading JDP_MENU_ITEM...
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (1, '菜单管理', null, 3, 'admin.menu', null, 1, null, null, null, null, 'admin.menu', 0, 'admin/menu', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (2, '外勤用户管理', null, 6, null, null, 1, null, null, null, null, 'admin.sales.index', 0, 'admin/sales/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (3, '角色管理', null, 4, null, null, 1, null, null, null, null, 'admin.sec.role.index', 0, 'admin/sec/role/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (4, '资源管理', null, 0, null, null, 1, null, null, null, null, 'admin.sec.resource', 0, 'admin/sec/resource', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (5, '数据字典', null, 2, null, null, 1, null, null, null, null, 'admin.dict.index', 0, 'admin/dict/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (6, '组织架构管理', null, 1, null, null, 1, null, null, null, null, 'admin.org.index', 0, 'admin/org/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (7, '后台角色管理', null, 5, null, null, 1, null, null, null, null, 'admin.sec.adminRole.index', 0, 'admin/sec/adminRole/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (8, '用户管理', null, 7, null, null, 1, null, null, null, null, 'admin.user.index', 0, 'admin/user/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (9, '后台用户管理', null, 8, '后台用户管理', null, 0, null, null, null, null, 'admin.sec.user.index', 0, 'admin/sec/user/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (10, '首页', null, 0, '首页', null, 1, null, null, null, null, 'index', 0, 'index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (11, '客户清单', null, 1, null, null, 1, null, null, null, null, 'customer.index', 0, 'customer/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (12, '行事历', null, 3, null, null, 1, null, null, null, null, 'calendar.calendarList', 0, 'calendar/calendarList', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (13, '计划清单', null, 2, null, null, 1, null, null, null, null, 'plan.planList', 0, 'plan/planList', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (14, '我的提醒', null, 4, null, null, 1, null, null, null, null, 'remind.index', 0, 'remind/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (15, '个人报表', null, 5, null, null, 0, null, null, null, null, 'report.index', 0, 'report/index', 0);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (16, '目标管理', null, 7, null, null, 1, null, null, null, null, 'target.index', 0, 'target/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (17, '团队活动量报表', null, 6, null, null, 1, null, null, null, null, 'team.report.index', 0, 'team/report/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (21, '产品管理', null, null, null, null, 1, null, null, null, null, 'admin.product.index', 0, 'admin/product/index', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (41, '模板管理', null, 10, '模板管理', null, 1, null, null, null, null, 'admin.specialDateTemplate.toSpecialDateManage', 0, 'admin/specialDateTemplate/toSpecialDateManage', 1);
insert into JDP_MENU_ITEM (cid, name_, parent_id, sort_index, description_, disable_icon, enabled_, icon_, meta1, meta2, meta3, perm_string, type_, url_, visible_)
values (61, '定时任务', null, null, null, null, 0, null, null, null, null, 'admin.task.index', 0, 'admin/task/index', 1);
commit;
--prompt 20 records loaded
--prompt Loading JDP_RESOURCE...
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (405, 'ezt/web/sign/querydate', null, 0, null, 1, 'ezt.web.sign.querydate', 'ezt/web/sign/querydate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (131, '更新客户信息', 426, 0, null, 1, 'customer.updateCusInfo', 'customer/updateCusInfo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (132, '客户列表', 426, 0, null, 1, 'customer.detailedList', 'customer/detailedList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (133, '新建客户', 426, 0, null, 1, 'customer.insertCusInfo', 'customer/insertCusInfo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (134, 'ezt/customer/index', null, 0, null, 1, 'ezt.customer.index', 'ezt/customer/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (135, 'ezt/customer/mappolicypager', null, 0, null, 1, 'ezt.customer.mappolicypager', 'ezt/customer/mappolicypager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (136, 'ezt/customer/policynum', null, 0, null, 1, 'ezt.customer.policynum', 'ezt/customer/policynum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (137, 'ezt/customer/mappolicy', null, 0, null, 1, 'ezt.customer.mappolicy', 'ezt/customer/mappolicy', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (138, 'ezt/custormePlan/test1', null, 0, null, 1, 'ezt.custormePlan.test1', 'ezt/custormePlan/test1', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (139, 'ezt/custormePlan/test2', null, 0, null, 1, 'ezt.custormePlan.test2', 'ezt/custormePlan/test2', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (140, 'ezt/custormePlan/test3', null, 0, null, 1, 'ezt.custormePlan.test3', 'ezt/custormePlan/test3', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (149, 'ezt/familytree/index', null, 0, null, 1, 'ezt.familytree.index', 'ezt/familytree/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (150, 'ezt/familytree/saveRelate', null, 0, null, 1, 'ezt.familytree.saveRelate', 'ezt/familytree/saveRelate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (151, 'ezt/familytree/saveFamily', null, 0, null, 1, 'ezt.familytree.saveFamily', 'ezt/familytree/saveFamily', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (152, 'ezt/familytree/updateFamily', null, 0, null, 1, 'ezt.familytree.updateFamily', 'ezt/familytree/updateFamily', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (153, 'ezt/familytree/deleteFamily', null, 0, null, 1, 'ezt.familytree.deleteFamily', 'ezt/familytree/deleteFamily', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (154, 'ezt/group/list', null, 0, null, 1, 'ezt.group.list', 'ezt/group/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (155, 'ezt/group/index', null, 0, null, 1, 'ezt.group.index', 'ezt/group/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (156, 'ezt/group/updateGroup', null, 0, null, 1, 'ezt.group.updateGroup', 'ezt/group/updateGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (157, 'ezt/group/saveGroup', null, 0, null, 1, 'ezt.group.saveGroup', 'ezt/group/saveGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (158, 'ezt/group/deleteGroup', null, 0, null, 1, 'ezt.group.deleteGroup', 'ezt/group/deleteGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (159, 'ezt/planShare/test', null, 0, null, 1, 'ezt.planShare.test', 'ezt/planShare/test', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (160, 'echat/rank/list', null, 0, null, 1, 'echat.rank.list', 'echat/rank/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (161, 'wc/remind/index', null, 0, null, 1, 'wc.remind.index', 'wc/remind/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (162, 'ezt/report/ownercusnum', null, 0, null, 1, 'ezt.report.ownercusnum', 'ezt/report/ownercusnum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (163, 'ezt/report/ownercovercusnum', null, 0, null, 1, 'ezt.report.ownercovercusnum', 'ezt/report/ownercovercusnum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (164, 'ezt/report/myactivitylist', null, 0, null, 1, 'ezt.report.myactivitylist', 'ezt/report/myactivitylist', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (165, 'wc/signIn/index', null, 0, null, 1, 'wc.signIn.index', 'wc/signIn/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (166, 'message/textMsg', null, 0, null, 1, 'message.textMsg', 'message/textMsg', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (167, 'oauth2/oauth2Api', null, 0, null, 1, 'oauth2.oauth2Api', 'oauth2/oauth2Api', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (168, 'oauth2/oauth2Code', null, 0, null, 1, 'oauth2.oauth2Code', 'oauth2/oauth2Code', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (169, 'wechat/test', null, 0, null, 1, 'wechat.test', 'wechat/test', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (344, 'ezt/pascard/wechat/bigdata', null, 0, null, 1, 'ezt.pascard.wechat.bigdata', 'ezt/pascard/wechat/bigdata', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (345, 'ezt/pascard/wechat/mycalendar', null, 0, null, 1, 'ezt.pascard.wechat.mycalendar', 'ezt/pascard/wechat/mycalendar', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (346, 'ezt/pascard/wechat/saveImpressionLable', null, 0, null, 1, 'ezt.pascard.wechat.saveImpressionLable', 'ezt/pascard/wechat/saveImpressionLable', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (347, 'ezt/pascard/wechat/comrate', null, 0, null, 1, 'ezt.pascard.wechat.comrate', 'ezt/pascard/wechat/comrate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (348, 'ezt/pascard/wechat/nbs2', null, 0, null, 1, 'ezt.pascard.wechat.nbs2', 'ezt/pascard/wechat/nbs2', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (349, 'ezt/pascard/wechat/metlife', null, 0, null, 1, 'ezt.pascard.wechat.metlife', 'ezt/pascard/wechat/metlife', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (350, 'ezt/pascard/wechat/mycourse', null, 0, null, 1, 'ezt.pascard.wechat.mycourse', 'ezt/pascard/wechat/mycourse', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (351, 'ezt/pascard/wechat/archives', null, 0, null, 1, 'ezt.pascard.wechat.archives', 'ezt/pascard/wechat/archives', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (352, 'ezt/pascard/wechat/nbs', null, 0, null, 1, 'ezt.pascard.wechat.nbs', 'ezt/pascard/wechat/nbs', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (358, 'ezt/wechat/personplan/addPagers', null, 0, null, 1, 'ezt.wechat.personplan.addPagers', 'ezt/wechat/personplan/addPagers', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (359, 'ezt/wechat/personplan/checkPersonPlanTime', null, 0, null, 1, 'ezt.wechat.personplan.checkPersonPlanTime', 'ezt/wechat/personplan/checkPersonPlanTime', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (360, 'ezt/wechat/personplan/deletePersonPlanById', null, 0, null, 1, 'ezt.wechat.personplan.deletePersonPlanById', 'ezt/wechat/personplan/deletePersonPlanById', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (361, 'ezt/wechat/personplan/updatePersonPlans', null, 0, null, 1, 'ezt.wechat.personplan.updatePersonPlans', 'ezt/wechat/personplan/updatePersonPlans', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (362, 'ezt/wechat/personplan/savePersonPlans', null, 0, null, 1, 'ezt.wechat.personplan.savePersonPlans', 'ezt/wechat/personplan/savePersonPlans', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (363, 'ezt/wechat/personplan/checkPersonPlanS', null, 0, null, 1, 'ezt.wechat.personplan.checkPersonPlanS', 'ezt/wechat/personplan/checkPersonPlanS', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (364, '计划分页', 428, 0, '计划分页', 1, 'plan.getCalendarPlanPager', 'plan/getCalendarPlanPager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (365, '计划列表', 428, 0, '计划列表', 1, 'plan.planList', 'plan/planList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (366, 'ezt/wechat/plan/getPlanDetail', null, 0, null, 1, 'ezt.wechat.plan.getPlanDetail', 'ezt/wechat/plan/getPlanDetail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (367, 'ezt/wechat/plan/planList', null, 0, null, 1, 'ezt.wechat.plan.planList', 'ezt/wechat/plan/planList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (368, 'ezt/wechat/plan/planListPager', null, 0, null, 1, 'ezt.wechat.plan.planListPager', 'ezt/wechat/plan/planListPager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (442, '群组管理', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (443, '家庭树', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (481, '个人资源', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (427, 'ezt/web/remind/getPolicyRemind', null, 0, null, 1, 'ezt.web.remind.getPolicyRemind', 'ezt/web/remind/getPolicyRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (428, '计划清单', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (441, '行事历', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (462, '我的提醒', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (444, '提醒首页', 462, 0, '提醒首页', 1, 'remind.index', 'remind/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (445, '计划提醒', 462, 0, '计划提醒', 1, 'remind.planTrackAlerts', 'remind/planTrackAlerts', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (446, '保单周年提醒', 462, 0, '保单周年提醒', 1, 'remind.getPolicyRemind', 'remind/getPolicyRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (447, '取消纪念日提醒', 462, 0, '取消纪念日提醒', 1, 'remind.calSpecialDateRemind', 'remind/calSpecialDateRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (448, '特殊纪念日列表', 462, 0, '特殊纪念日列表', 1, 'remind.specialDateList', 'remind/specialDateList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (449, '轨迹详细', 462, 0, '轨迹详细', 1, 'remind.getMyPlanTrackDetail', 'remind/getMyPlanTrackDetail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (450, '获取分享列表', 462, 0, '获取分享列表', 1, 'remind.sharePlanList', 'remind/sharePlanList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (451, '不再提醒', 462, 0, '不再提醒', 1, 'remind.updateShare', 'remind/updateShare', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (452, '获取纪念日字典', 462, 0, '获取纪念日字典', 1, 'remind.getDictData', 'remind/getDictData', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (453, '更新纪念日', 462, 0, '更新纪念日', 1, 'remind.updateRemind', 'remind/updateRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (454, '不在提醒去除', 462, 0, '不在提醒去除', 1, 'remind.updateTask', 'remind/updateTask', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (455, '提醒列表', 462, 0, '提醒列表', 1, 'remind.planAlertList', 'remind/planAlertList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (456, 'ezt/wechat/remind/getPolicyRemind', null, 0, null, 1, 'ezt.wechat.remind.getPolicyRemind', 'ezt/wechat/remind/getPolicyRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (457, '个人计划添加', 428, 0, '个人计划添加', 1, 'plan.personplan.addPager', 'plan/personplan/addPager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (458, '删除个人计划', 428, 0, '删除个人计划', 1, 'plan.personplan.deletePersonPlanById', 'plan/personplan/deletePersonPlanById', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (459, '更新个人计划', 428, 0, '更新个人计划', 1, 'plan.personplan.updatePersonPlan', 'plan/personplan/updatePersonPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (460, '保存个人计划', 428, 0, '保存个人计划', 1, 'plan.personplan.savePersonPlan', 'plan/personplan/savePersonPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (461, '个人计划详情', 428, 0, '个人计划详情', 1, 'plan.personplan.updatePersonPlanPager', 'plan/personplan/updatePersonPlanPager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (407, 'ezt/wechat/signIn/sign', null, 0, null, 1, 'ezt.wechat.signIn.sign', 'ezt/wechat/signIn/sign', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (408, 'ezt/wechat/signIn/queryday', null, 0, null, 1, 'ezt.wechat.signIn.queryday', 'ezt/wechat/signIn/queryday', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (409, 'ezt/wechat/signIn/querydate', null, 0, null, 1, 'ezt.wechat.signIn.querydate', 'ezt/wechat/signIn/querydate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (406, 'ezt/wechat/signIn/index', null, 0, null, 1, 'ezt.wechat.signIn.index', 'ezt/wechat/signIn/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (181, 'city/getCity', null, 0, null, 1, 'city.getCity', 'city/getCity', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (182, 'city/getCitysByRole', null, 0, null, 1, 'city.getCitysByRole', 'city/getCitysByRole', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (183, 'ezt/calendar/pager', null, 0, null, 1, 'ezt.calendar.pager', 'ezt/calendar/pager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (184, 'ezt/calendar/weekCalendarList', null, 0, null, 1, 'ezt.calendar.weekCalendarList', 'ezt/calendar/weekCalendarList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (185, 'ezt/calendar/dayCalendarList', null, 0, null, 1, 'ezt.calendar.dayCalendarList', 'ezt/calendar/dayCalendarList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (186, 'ezt/policy/index', null, 0, null, 1, 'ezt.policy.index', 'ezt/policy/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (187, 'ezt/policy/wechat/wcppolicy', null, 0, null, 1, 'ezt.policy.wechat.wcppolicy', 'ezt/policy/wechat/wcppolicy', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (188, 'ezt/policy/mappolicypager', null, 0, null, 1, 'ezt.policy.mappolicypager', 'ezt/policy/mappolicypager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (189, 'ezt/policy/policynum', null, 0, null, 1, 'ezt.policy.policynum', 'ezt/policy/policynum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (190, 'WC获取客户计划任务阶段', null, 0, 'WC获取客户计划任务阶段', 1, 'ezt.wechat.customerPlan.taskStage', 'ezt/wechat/customerPlan/taskStage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (191, 'WC获取客户计划列表', null, 0, 'WC获取客户计划列表', 1, 'ezt.wechat.customerPlan.cusPlan', 'ezt/wechat/customerPlan/cusPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (192, 'WC获取推荐产品', null, 0, 'WC获取推荐产品', 1, 'ezt.wechat.customerPlan.product', 'ezt/wechat/customerPlan/product', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (193, 'WC获取计划提醒设置', null, 0, 'WC获取计划提醒设置', 1, 'ezt.wechat.customerPlan.timeSetting', 'ezt/wechat/customerPlan/timeSetting', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (194, 'WC添加客户计划', null, 0, 'WC添加客户计划', 1, 'ezt.wechat.customerPlan.addCusPlan', 'ezt/wechat/customerPlan/addCusPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (195, 'WC删除客户计划', null, 0, 'WC删除客户计划', 1, 'ezt.wechat.customerPlan.delCusPlan', 'ezt/wechat/customerPlan/delCusPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (200, 'ezt/group/wechat/index', null, 0, null, 1, 'ezt.group.wechat.index', 'ezt/group/wechat/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (201, 'ezt/group/wechat/saveGroup', null, 0, null, 1, 'ezt.group.wechat.saveGroup', 'ezt/group/wechat/saveGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (202, '个人名片', 481, 0, null, 1, 'person.pascard.index', 'person/pascard/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (203, 'echat/personplan/checkPersonPlanStates', null, 0, null, 1, 'echat.personplan.checkPersonPlanStates', 'echat/personplan/checkPersonPlanStates', 'U');
commit;
--prompt 100 records committed...
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (205, 'echat/personplan/deletePersonPlan', null, 0, null, 1, 'echat.personplan.deletePersonPlan', 'echat/personplan/deletePersonPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (206, 'echat/personplan/updatePersonPlan', null, 0, null, 1, 'echat.personplan.updatePersonPlan', 'echat/personplan/updatePersonPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (207, 'echat/personplan/savePersonPlan', null, 0, null, 1, 'echat.personplan.savePersonPlan', 'echat/personplan/savePersonPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (209, 'ezt/plan/getPlanDetail', null, 0, null, 1, 'ezt.plan.getPlanDetail', 'ezt/plan/getPlanDetail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (210, 'WC获取分享者列表', null, 0, 'WC获取分享者列表', 1, 'ezt.wechat.planShare.share_user', 'ezt/wechat/planShare/share_user', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (211, 'WC获取已分享者列表', null, 0, 'WC获取已分享者列表', 1, 'ezt.wechat.planShare.getSelectedUsers', 'ezt/wechat/planShare/getSelectedUsers', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (212, 'WC删除分享者', null, 0, 'WC删除分享者', 1, 'ezt.wechat.planShare.dealShare', 'ezt/wechat/planShare/dealShare', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (213, 'WC获取共享列表', null, 0, 'WC获取共享列表', 1, 'ezt.wechat.planShare.shareList', 'ezt/wechat/planShare/shareList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (214, 'wc/signIn/queryday', null, 0, null, 1, 'wc.signIn.queryday', 'wc/signIn/queryday', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (215, 'wc/signIn/sign', null, 0, null, 1, 'wc.signIn.sign', 'wc/signIn/sign', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (216, 'wc/signIn/querydate', null, 0, null, 1, 'wc.signIn.querydate', 'wc/signIn/querydate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (219, 'message/wechat/textMsg', null, 0, null, 1, 'message.wechat.textMsg', 'message/wechat/textMsg', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (220, 'wechat/map/index', null, 0, null, 1, 'wechat.map.index', 'wechat/map/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (221, 'wechat/map/selectAddress', null, 0, null, 1, 'wechat.map.selectAddress', 'wechat/map/selectAddress', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (241, 'admin/city/getCitysByRole', null, 0, null, 1, 'admin.city.getCitysByRole', 'admin/city/getCitysByRole', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (242, 'admin/city/getCity', null, 0, null, 1, 'admin.city.getCity', 'admin/city/getCity', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (243, 'admin/org/index', null, 0, null, 1, 'admin.org.index', 'admin/org/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (244, 'admin/org/tree', null, 0, null, 1, 'admin.org.tree', 'admin/org/tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (245, 'admin/sales/index', 682, 0, null, 1, 'admin.sales.index', 'admin/sales/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (246, 'admin/sales/tree', 682, 0, null, 1, 'admin.sales.tree', 'admin/sales/tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (247, 'admin/sales/list', 682, 0, null, 1, 'admin.sales.list', 'admin/sales/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (248, '行事历', 441, 0, '行事历', 1, 'calendar.calendarList', 'calendar/calendarList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (249, '获取日计划及纪念日数据', 441, 0, '获取日计划及纪念日数据', 1, 'calendar.getDayCalendarList', 'calendar/getDayCalendarList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (250, '获取月计划及周计划数据', 441, 0, '获取月计划及周计划数据', 1, 'calendar.getCalendarForJson', 'calendar/getCalendarForJson', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (251, 'ezt/wechat/calendar/calendarList', null, 0, null, 1, 'ezt.wechat.calendar.calendarList', 'ezt/wechat/calendar/calendarList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (252, 'ezt/wechat/calendar/getDayCalendarWechatList', null, 0, null, 1, 'ezt.wechat.calendar.getDayCalendarWechatList', 'ezt/wechat/calendar/getDayCalendarWechatList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (253, 'ezt/wechat/calendar/getBirthdayCardPage', null, 0, null, 1, 'ezt.wechat.calendar.getBirthdayCardPage', 'ezt/wechat/calendar/getBirthdayCardPage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (254, 'ezt/birthday/card', null, 0, null, 1, 'ezt.birthday.card', 'ezt/birthday/card', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (255, '获取当天计划', 424, 0, null, 1, '.getCurrDayPlan', '/getCurrDayPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (256, '/菜单拦截器中保存剪切图片', 424, 0, null, 1, '.uploadHeadImage', '/uploadHeadImage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (257, '首页行事历的列表', 424, 0, null, 1, '.indexTask', '/indexTask', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (258, '菜单拦截器中上传头像', 424, 0, null, 1, '.upImage', '/upImage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (259, '客户群组关系列表', 442, 0, null, 1, 'cusgroup.list', 'cusgroup/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (260, '客户分组首页', 442, 0, null, 1, 'cusgroup.index', 'cusgroup/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (261, '更新群组关系', 442, 0, null, 1, 'cusgroup.updateCusGroup', 'cusgroup/updateCusGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (262, '新增组成员列表', 442, 0, null, 1, 'cusgroup.addGroupMember', 'cusgroup/addGroupMember', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (263, '删除组成员', 442, 0, null, 1, 'cusgroup.removeGroupMember', 'cusgroup/removeGroupMember', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (264, '删除组成员列表', 442, 0, null, 1, 'cusgroup.removeMemberList', 'cusgroup/removeMemberList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (265, '保存组成员', 442, 0, null, 1, 'cusgroup.saveCusGroup', 'cusgroup/saveCusGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (266, 'ezt/cusgroup/wechat/list', null, 0, null, 1, 'ezt.cusgroup.wechat.list', 'ezt/cusgroup/wechat/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (267, 'ezt/cusgroup/wechat/index', null, 0, null, 1, 'ezt.cusgroup.wechat.index', 'ezt/cusgroup/wechat/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (268, 'ezt/cusgroup/wechat/deleteCusGroup', null, 0, null, 1, 'ezt.cusgroup.wechat.deleteCusGroup', 'ezt/cusgroup/wechat/deleteCusGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (269, 'ezt/cusgroup/wechat/saveCusGroup', null, 0, null, 1, 'ezt.cusgroup.wechat.saveCusGroup', 'ezt/cusgroup/wechat/saveCusGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (270, 'ezt/cusgroup/wechat/cusindex', null, 0, null, 1, 'ezt.cusgroup.wechat.cusindex', 'ezt/cusgroup/wechat/cusindex', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (271, 'ezt/cusgroup/wechat/cuslist', null, 0, null, 1, 'ezt.cusgroup.wechat.cuslist', 'ezt/cusgroup/wechat/cuslist', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (272, 'ezt/policy/policyInfo', null, 0, null, 1, 'ezt.policy.policyInfo', 'ezt/policy/policyInfo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (273, 'ezt/policy/wechat/policynum', null, 0, null, 1, 'ezt.policy.wechat.policynum', 'ezt/policy/wechat/policynum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (274, '客户清单首页', 426, 0, null, 1, 'customer.index', 'customer/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (275, '新建客户首页', 426, 0, null, 1, 'customer.input-your-clients', 'customer/input-your-clients', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (276, '修改客户', 426, 0, null, 1, 'customer.changeCustomer', 'customer/changeCustomer', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (277, '获取地址字典', 426, 0, null, 1, 'customer.getAddressData', 'customer/getAddressData', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (278, '查询客户', 426, 0, null, 1, 'customer.findCustomers', 'customer/findCustomers', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (279, '保存客户', 426, 0, null, 1, 'customer.saveCustomer', 'customer/saveCustomer', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (280, '上传头像', 426, 0, null, 1, 'customer.uploadImg', 'customer/uploadImg', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (281, '更新客户信息', 426, 0, null, 1, 'customer.infor-edit', 'customer/infor-edit', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (282, '获取客户信息', 426, 0, null, 1, 'customer.customer_detail', 'customer/customer_detail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (283, '获取客户信息', 426, 0, null, 1, 'customer.getCustomer', 'customer/getCustomer', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (284, 'ezt/wechat/customers/insertCusInfo', null, 0, null, 1, 'ezt.wechat.customers.insertCusInfo', 'ezt/wechat/customers/insertCusInfo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (285, 'ezt/wechat/customers/updateCusInfo', null, 0, null, 1, 'ezt.wechat.customers.updateCusInfo', 'ezt/wechat/customers/updateCusInfo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (286, 'ezt/wechat/customers/getAddressData', null, 0, null, 1, 'ezt.wechat.customers.getAddressData', 'ezt/wechat/customers/getAddressData', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (287, 'ezt/wechat/customers/updateCusInfoDO', null, 0, null, 1, 'ezt.wechat.customers.updateCusInfoDO', 'ezt/wechat/customers/updateCusInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (288, 'ezt/wechat/customers/insertCusInfoDO', null, 0, null, 1, 'ezt.wechat.customers.insertCusInfoDO', 'ezt/wechat/customers/insertCusInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (289, 'ezt/wechat/customers/deleteCusInfoDO', null, 0, null, 1, 'ezt.wechat.customers.deleteCusInfoDO', 'ezt/wechat/customers/deleteCusInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (290, 'ezt/wechat/customers/syncDetailedList', null, 0, null, 1, 'ezt.wechat.customers.syncDetailedList', 'ezt/wechat/customers/syncDetailedList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (291, 'ezt/wechat/customers/updateSyncCusExtInfo', null, 0, null, 1, 'ezt.wechat.customers.updateSyncCusExtInfo', 'ezt/wechat/customers/updateSyncCusExtInfo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (292, 'ezt/wechat/customers/updateSyncCusExtInfoDO', null, 0, null, 1, 'ezt.wechat.customers.updateSyncCusExtInfoDO', 'ezt/wechat/customers/updateSyncCusExtInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (293, 'ezt/wechat/customers/findCustomers', null, 0, null, 1, 'ezt.wechat.customers.findCustomers', 'ezt/wechat/customers/findCustomers', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (294, 'ezt/wechat/customers/customerDetails', null, 0, null, 1, 'ezt.wechat.customers.customerDetails', 'ezt/wechat/customers/customerDetails', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (295, 'ezt/wechat/customers/customerMapList', null, 0, null, 1, 'ezt.wechat.customers.customerMapList', 'ezt/wechat/customers/customerMapList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (296, 'ezt/wechat/customers/customerInsert', null, 0, null, 1, 'ezt.wechat.customers.customerInsert', 'ezt/wechat/customers/customerInsert', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (297, 'ezt/wechat/customers/customerSyncList', null, 0, null, 1, 'ezt.wechat.customers.customerSyncList', 'ezt/wechat/customers/customerSyncList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (298, 'ezt/wechat/customers/customerList', null, 0, null, 1, 'ezt.wechat.customers.customerList', 'ezt/wechat/customers/customerList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (299, 'ezt/wechat/customers/uploadImg', null, 0, null, 1, 'ezt.wechat.customers.uploadImg', 'ezt/wechat/customers/uploadImg', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (300, 'ezt/wechat/WeChat/getSign', null, 0, null, 1, 'ezt.wechat.WeChat.getSign', 'ezt/wechat/WeChat/getSign', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (301, 'wechat/report/index', null, 0, null, 1, 'wechat.report.index', 'wechat/report/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (302, 'PC客户计划编辑', 428, 0, 'PC客户计划编辑', 1, 'customer.plan.edit', 'customer/plan/edit', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (303, 'PC客户计划修改计划和任务', 428, 0, 'PC客户计划修改计划和任务', 1, 'customer.plan.modifyPlanAndTask', 'customer/plan/modifyPlanAndTask', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (304, 'PC获取客户计划提醒设置', 428, 0, 'PC获取客户计划提醒设置', 1, 'customer.plan.timeSetting', 'customer/plan/timeSetting', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (305, 'PC获取客户计划任务的阶段', 428, 0, 'PC获取客户计划任务的阶段', 1, 'customer.plan.taskStage', 'customer/plan/taskStage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (306, 'PC销售计划列表', 428, 0, 'PC销售计划列表', 1, 'customer.plan.saleTrackList', 'customer/plan/saleTrackList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (307, 'PC销售轨迹详情', 428, 0, 'PC销售轨迹详情', 1, 'customer.plan.salesTrack', 'customer/plan/salesTrack', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (309, 'PC客户计划列表', 428, 0, 'PC客户计划列表', 1, 'customer.plan.customers', 'customer/plan/customers', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (310, 'PC修改客户计划和任务', 428, 0, 'PC修改计划和任务', 1, 'customer.plan.modifyPlans', 'customer/plan/modifyPlans', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (311, 'PC添加客户计划', 428, 0, 'PC添加客户计划', 1, 'customer.plan.addCusPlan', 'customer/plan/addCusPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (312, 'PC删除客户计划', 428, 0, 'PC删除客户计划', 1, 'customer.plan.delCusPlan', 'customer/plan/delCusPlan', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (313, 'PC获取客户计划推荐产品', 428, 0, 'PC获取客户计划推荐产品', 1, 'customer.plan.product', 'customer/plan/product', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (314, 'WC修改客户计划和任务', null, 0, 'WC修改客户计划和任务', 1, 'ezt.wechat.customerPlan.modifyPlanAndTask', 'ezt/wechat/customerPlan/modifyPlanAndTask', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (315, 'WC获取销售轨迹列表', null, 0, 'WC获取销售轨迹列表', 1, 'ezt.wechat.customerPlan.saleTrackList', 'ezt/wechat/customerPlan/saleTrackList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (316, 'WC获取销售轨迹详情', null, 0, 'WC获取销售轨迹详情', 1, 'ezt.wechat.customerPlan.salesTrack', 'ezt/wechat/customerPlan/salesTrack', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (317, 'WC分页获取销售轨迹列表', null, 0, 'WC分页获取销售轨迹列表', 1, 'ezt.wechat.customerPlan.sales_trajectory', 'ezt/wechat/customerPlan/sales_trajectory', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (318, 'WC修改客户计划', null, 0, 'WC修改客户计划', 1, 'ezt.wechat.customerPlan.modifyPlans', 'ezt/wechat/customerPlan/modifyPlans', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (319, '家庭树首页', 443, 0, null, 1, 'familytree.index', 'familytree/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (320, '更新家庭树', 443, 0, null, 1, 'familytree.updateFamily', 'familytree/updateFamily', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (321, '保存家庭树', 443, 0, null, 1, 'familytree.saveFamily', 'familytree/saveFamily', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (322, '删除家庭树', 443, 0, null, 1, 'familytree.deleteFamily', 'familytree/deleteFamily', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (323, '保存家庭关系', 443, 0, null, 1, 'familytree.saveRelate', 'familytree/saveRelate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (324, 'ezt/familytree/wechat/index', null, 0, null, 1, 'ezt.familytree.wechat.index', 'ezt/familytree/wechat/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (325, 'ezt/familytree/wechat/cusindex', null, 0, null, 1, 'ezt.familytree.wechat.cusindex', 'ezt/familytree/wechat/cusindex', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (326, 'ezt/familytree/wechat/saveFamily', null, 0, null, 1, 'ezt.familytree.wechat.saveFamily', 'ezt/familytree/wechat/saveFamily', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (327, 'ezt/familytree/wechat/saveRelate', null, 0, null, 1, 'ezt.familytree.wechat.saveRelate', 'ezt/familytree/wechat/saveRelate', 'U');
commit;
--prompt 200 records committed...
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (328, 'ezt/familytree/wechat/cuslist', null, 0, null, 1, 'ezt.familytree.wechat.cuslist', 'ezt/familytree/wechat/cuslist', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (329, '群组列表', 442, 0, null, 1, 'group.list', 'group/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (330, '群组首页', 442, 0, null, 1, 'group.index', 'group/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (331, '更新群组', 442, 0, null, 1, 'group.updateGroup', 'group/updateGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (332, '删除群组', 442, 0, null, 1, 'group.deleteGroup', 'group/deleteGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (333, '保存群组', 442, 0, null, 1, 'group.saveGroup', 'group/saveGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (334, 'ezt/group/wechat/list', null, 0, null, 1, 'ezt.group.wechat.list', 'ezt/group/wechat/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (335, 'ezt/wechat/knowledge/index', null, 0, null, 1, 'ezt.wechat.knowledge.index', 'ezt/wechat/knowledge/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (336, 'ezt/wechat/knowledge/knowledgePager', null, 0, null, 1, 'ezt.wechat.knowledge.knowledgePager', 'ezt/wechat/knowledge/knowledgePager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (337, '个人名片X档案保存', 481, 0, null, 1, 'person.pascard.savepresoncard', 'person/pascard/savepresoncard', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (338, '个人名片照片保存', 481, 0, null, 1, 'person.pascard.uploadphoto.query', 'person/pascard/uploadphoto/query', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (339, '个人名片照片删除', 481, 0, null, 1, 'person.pascard.uploadphoto.delete', 'person/pascard/uploadphoto/delete', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (340, '个人名片大数据', 481, 0, null, 1, 'person.pascard.saveOrUpdateBigDate', 'person/pascard/saveOrUpdateBigDate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (342, '个人名片历程', 481, 0, null, 1, 'person.pascard.course', 'person/pascard/course', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (343, 'ezt/pascard/wechat/index', null, 0, null, 1, 'ezt.pascard.wechat.index', 'ezt/pascard/wechat/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (121, 'ezt/cusgroup/index', null, 0, null, 1, 'ezt.cusgroup.index', 'ezt/cusgroup/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (122, 'ezt/cusgroup/updateCusGroup', null, 0, null, 1, 'ezt.cusgroup.updateCusGroup', 'ezt/cusgroup/updateCusGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (123, 'ezt/cusgroup/saveCusGroup', null, 0, null, 1, 'ezt.cusgroup.saveCusGroup', 'ezt/cusgroup/saveCusGroup', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (124, 'customer/test', null, 0, null, 1, 'customer.test', 'customer/test', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (125, '更新客户信息', 426, 0, null, 1, 'customer.updateCusInfoDO', 'customer/updateCusInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (126, '插入客户信息', 426, 0, null, 1, 'customer.insertCusInfoDO', 'customer/insertCusInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (127, '删除客户信息', 426, 0, null, 1, 'customer.deleteCusInfoDO', 'customer/deleteCusInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (128, '客户列表', 426, 0, null, 1, 'customer.syncDetailedList', 'customer/syncDetailedList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (129, '更新客户扩展信息', 426, 0, null, 1, 'customer.updateSyncCusExtInfo', 'customer/updateSyncCusExtInfo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (130, '修改客户扩展信息', 426, 0, null, 1, 'customer.updateSyncCusExtInfoDO', 'customer/updateSyncCusExtInfoDO', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (2, '测试页面', 119, 0, null, 1, 'test.index', 'test/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (3, 'admin/cachemgmt/delete', null, 0, null, 1, 'admin.cachemgmt.delete', 'admin/cachemgmt/delete', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (4, 'admin/cachemgmt/deleteShiro', null, 0, null, 1, 'admin.cachemgmt.deleteShiro', 'admin/cachemgmt/deleteShiro', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (5, 'admin/dict/deleteItem', null, 0, null, 1, 'admin.dict.deleteItem', 'admin/dict/deleteItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (6, 'admin/dict/create', null, 0, null, 1, 'admin.dict.create', 'admin/dict/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (7, 'admin/dict/index', null, 0, null, 1, 'admin.dict.index', 'admin/dict/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (8, 'admin/dict/tree', null, 0, null, 1, 'admin.dict.tree', 'admin/dict/tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (9, 'admin/dict/getDictItem', null, 0, null, 1, 'admin.dict.getDictItem', 'admin/dict/getDictItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (10, 'admin/dict/del', null, 0, null, 1, 'admin.dict.del', 'admin/dict/del', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (11, 'admin/dict/createItem', null, 0, null, 1, 'admin.dict.createItem', 'admin/dict/createItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (12, 'admin/dict/updateItem', null, 0, null, 1, 'admin.dict.updateItem', 'admin/dict/updateItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (13, 'admin/dict/sys/create', null, 0, null, 1, 'admin.dict.sys.create', 'admin/dict/sys/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (14, 'admin/dict/sys/check', null, 0, null, 1, 'admin.dict.sys.check', 'admin/dict/sys/check', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (15, 'admin/dict/sys/update', null, 0, null, 1, 'admin.dict.sys.update', 'admin/dict/sys/update', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (16, 'admin/dict/sys/get-tree/sys', null, 0, null, 1, 'admin.dict.sys.get-tree.sys', 'admin/dict/sys/get-tree/sys', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (17, 'admin/dict/sys/updateDictionaryItem', null, 0, null, 1, 'admin.dict.sys.updateDictionaryItem', 'admin/dict/sys/updateDictionaryItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (18, 'admin/dict/sys/checkItem', null, 0, null, 1, 'admin.dict.sys.checkItem', 'admin/dict/sys/checkItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (19, 'admin/dict/sys/get-tree', null, 0, null, 1, 'admin.dict.sys.get-tree', 'admin/dict/sys/get-tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (20, 'admin/dict/sys/get-tree/condition', null, 0, null, 1, 'admin.dict.sys.get-tree.condition', 'admin/dict/sys/get-tree/condition', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (21, 'admin/dict/user/create', null, 0, null, 1, 'admin.dict.user.create', 'admin/dict/user/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (22, 'admin/dict/user/check', null, 0, null, 1, 'admin.dict.user.check', 'admin/dict/user/check', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (23, 'admin/dict/user/update', null, 0, null, 1, 'admin.dict.user.update', 'admin/dict/user/update', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (24, 'admin/dict/user/get-tree/user', null, 0, null, 1, 'admin.dict.user.get-tree.user', 'admin/dict/user/get-tree/user', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (25, 'admin/dict/user/updateDictionaryItem', null, 0, null, 1, 'admin.dict.user.updateDictionaryItem', 'admin/dict/user/updateDictionaryItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (26, 'admin/dict/user/checkItem', null, 0, null, 1, 'admin.dict.user.checkItem', 'admin/dict/user/checkItem', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (27, 'admin/dict/user/get-tree/condition', null, 0, null, 1, 'admin.dict.user.get-tree.condition', 'admin/dict/user/get-tree/condition', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (28, 'fileManager/index', null, 0, null, 1, 'fileManager.index', 'fileManager/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (29, 'fileManager/toUploadFile', null, 0, null, 1, 'fileManager.toUploadFile', 'fileManager/toUploadFile', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (30, 'fileManager/toIosAndAndroidUploadFile', null, 0, null, 1, 'fileManager.toIosAndAndroidUploadFile', 'fileManager/toIosAndAndroidUploadFile', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (31, 'fileManager/delAttachFile', null, 0, null, 1, 'fileManager.delAttachFile', 'fileManager/delAttachFile', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (32, 'admin/logqry/index', null, 0, null, 1, 'admin.logqry.index', 'admin/logqry/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (33, 'admin/logqry/logQuery', null, 0, null, 1, 'admin.logqry.logQuery', 'admin/logqry/logQuery', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (34, 'admin/logqry/logQueryReport', null, 0, null, 1, 'admin.logqry.logQueryReport', 'admin/logqry/logQueryReport', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (35, 'admin/logqry/runLog', null, 0, null, 1, 'admin.logqry.runLog', 'admin/logqry/runLog', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (36, 'admin/logqry/runLogQuery', null, 0, null, 1, 'admin.logqry.runLogQuery', 'admin/logqry/runLogQuery', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (37, 'admin/logqry/runLogReport', null, 0, null, 1, 'admin.logqry.runLogReport', 'admin/logqry/runLogReport', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (38, 'admin/desktop', 120, 0, null, 1, 'admin.desktop', 'admin/desktop', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (39, 'admin/datamodify/index', null, 0, null, 1, 'admin.datamodify.index', 'admin/datamodify/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (40, 'admin/datamodify/domodify', null, 0, null, 1, 'admin.datamodify.domodify', 'admin/datamodify/domodify', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (41, 'admin/menu/delete', null, 0, null, 1, 'admin.menu.delete', 'admin/menu/delete', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (42, 'admin/menu/create', null, 0, null, 1, 'admin.menu.create', 'admin/menu/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (43, 'admin/menu/update', null, 0, null, 1, 'admin.menu.update', 'admin/menu/update', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (44, 'admin/menu/list', null, 0, null, 1, 'admin.menu.list', 'admin/menu/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (45, 'admin/menu/get-tree', null, 0, null, 1, 'admin.menu.get-tree', 'admin/menu/get-tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (46, 'admin/menu/get-tree/condition', null, 0, null, 1, 'admin.menu.get-tree.condition', 'admin/menu/get-tree/condition', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (47, 'mobile/v1/menu/', null, 0, null, 1, 'mobile.v1.menu.', 'mobile/v1/menu/', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (48, 'admin/sec/permission/search-role', null, 0, null, 1, 'admin.sec.permission.search-role', 'admin/sec/permission/search-role', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (49, 'admin/sec/permission/get-tree-grid', null, 0, null, 1, 'admin.sec.permission.get-tree-grid', 'admin/sec/permission/get-tree-grid', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (50, 'admin/sec/permission/get-tree', null, 0, null, 1, 'admin.sec.permission.get-tree', 'admin/sec/permission/get-tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (51, 'admin/sec/permission/get-tree/condition', null, 0, null, 1, 'admin.sec.permission.get-tree.condition', 'admin/sec/permission/get-tree/condition', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (52, 'admin/profile/alterAdminPassword', null, 0, null, 1, 'admin.profile.alterAdminPassword', 'admin/profile/alterAdminPassword', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (53, 'admin/profile/alterAdminSetting', null, 0, null, 1, 'admin.profile.alterAdminSetting', 'admin/profile/alterAdminSetting', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (54, 'admin/profile/modifyPassword', null, 0, null, 1, 'admin.profile.modifyPassword', 'admin/profile/modifyPassword', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (55, 'admin/profile/settings/save', null, 0, null, 1, 'admin.profile.settings.save', 'admin/profile/settings/save', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (56, 'admin/register/checkLoginName', null, 0, null, 1, 'admin.register.checkLoginName', 'admin/register/checkLoginName', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (57, 'admin/sec/resource/create', null, 0, null, 1, 'admin.sec.resource.create', 'admin/sec/resource/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (58, 'admin/sec/resource/check', null, 0, null, 1, 'admin.sec.resource.check', 'admin/sec/resource/check', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (59, 'admin/sec/resource/tree', null, 0, null, 1, 'admin.sec.resource.tree', 'admin/sec/resource/tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (60, 'admin/sec/resource/list', null, 0, null, 1, 'admin.sec.resource.list', 'admin/sec/resource/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (61, 'admin/sec/resource/update', null, 0, null, 1, 'admin.sec.resource.update', 'admin/sec/resource/update', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (62, 'admin/sec/resource/urlbox', null, 0, null, 1, 'admin.sec.resource.urlbox', 'admin/sec/resource/urlbox', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (63, 'admin/sec/resource/urlbox/list', null, 0, null, 1, 'admin.sec.resource.urlbox.list', 'admin/sec/resource/urlbox/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (64, 'admin/sec/resource/saveUrls', null, 0, null, 1, 'admin.sec.resource.saveUrls', 'admin/sec/resource/saveUrls', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (65, 'admin/sec/resource/delete', null, 0, null, 1, 'admin.sec.resource.delete', 'admin/sec/resource/delete', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (66, 'admin/sec/resource/save-close', null, 0, null, 1, 'admin.sec.resource.save-close', 'admin/sec/resource/save-close', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (67, 'admin/sec/resource/checkRes', null, 0, null, 1, 'admin.sec.resource.checkRes', 'admin/sec/resource/checkRes', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (68, 'admin/sec/resource/checkPer', null, 0, null, 1, 'admin.sec.resource.checkPer', 'admin/sec/resource/checkPer', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (69, 'admin/sec/resource/scanner', null, 0, null, 1, 'admin.sec.resource.scanner', 'admin/sec/resource/scanner', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (70, 'admin/sec/resource/refresh/filters', null, 0, null, 1, 'admin.sec.resource.refresh.filters', 'admin/sec/resource/refresh/filters', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (71, 'admin/sec/resource/get-tree', null, 0, null, 1, 'admin.sec.resource.get-tree', 'admin/sec/resource/get-tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (72, 'admin/sec/resource/get-tree/condition', null, 0, null, 1, 'admin.sec.resource.get-tree.condition', 'admin/sec/resource/get-tree/condition', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (73, 'admin/sec/res/create', null, 0, null, 1, 'admin.sec.res.create', 'admin/sec/res/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (74, 'admin/sec/res/update', null, 0, null, 1, 'admin.sec.res.update', 'admin/sec/res/update', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (75, 'admin/sec/res/get-tree', null, 0, null, 1, 'admin.sec.res.get-tree', 'admin/sec/res/get-tree', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (76, 'admin/sec/res/get-tree/condition', null, 0, null, 1, 'admin.sec.res.get-tree.condition', 'admin/sec/res/get-tree/condition', 'U');
commit;
--prompt 300 records committed...
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (77, 'admin/sec/adminRole/list', null, 0, null, 1, 'admin.sec.adminRole.list', 'admin/sec/adminRole/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (78, 'admin/sec/adminRole/create', null, 0, null, 1, 'admin.sec.adminRole.create', 'admin/sec/adminRole/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (501, '目标管理', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (419, 'ezt/task/index', null, 0, null, 1, 'ezt.task.index', 'ezt/task/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (420, 'ezt/task/todo', null, 0, null, 1, 'ezt.task.todo', 'ezt/task/todo', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (421, 'ezt/task/download', null, 0, null, 1, 'ezt.task.download', 'ezt/task/download', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (422, 'wechat/jsconfig', null, 0, null, 1, 'wechat.jsconfig', 'wechat/jsconfig', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (423, 'wechat/share', null, 0, null, 1, 'wechat.share', 'wechat/share', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (424, '首页', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (425, '首页', 424, 0, null, 1, '.index', '/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (79, 'admin/sec/adminRole/check', null, 0, null, 1, 'admin.sec.adminRole.check', 'admin/sec/adminRole/check', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (80, 'admin/sec/adminRole/index', null, 0, null, 1, 'admin.sec.adminRole.index', 'admin/sec/adminRole/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (81, 'admin/sec/adminRole/createPage', null, 0, null, 1, 'admin.sec.adminRole.createPage', 'admin/sec/adminRole/createPage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (82, 'admin/sec/adminRole/checkCode', null, 0, null, 1, 'admin.sec.adminRole.checkCode', 'admin/sec/adminRole/checkCode', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (83, 'admin/sec/adminRole/getRoleArray', null, 0, null, 1, 'admin.sec.adminRole.getRoleArray', 'admin/sec/adminRole/getRoleArray', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (84, 'admin/sec/role/list', null, 0, null, 1, 'admin.sec.role.list', 'admin/sec/role/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (85, 'admin/sec/role/delete', null, 0, null, 1, 'admin.sec.role.delete', 'admin/sec/role/delete', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (86, 'admin/sec/role/editauthority', null, 0, null, 1, 'admin.sec.role.editauthority', 'admin/sec/role/editauthority', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (87, 'admin/sec/role/create', null, 0, null, 1, 'admin.sec.role.create', 'admin/sec/role/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (88, 'admin/sec/role/index', null, 0, null, 1, 'admin.sec.role.index', 'admin/sec/role/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (89, 'admin/sec/role/update', null, 0, null, 1, 'admin.sec.role.update', 'admin/sec/role/update', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (90, 'admin/sec/role/createPage/', null, 0, null, 1, 'admin.sec.role.createPage.', 'admin/sec/role/createPage/', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (91, 'admin/sec/role/updatePage', null, 0, null, 1, 'admin.sec.role.updatePage', 'admin/sec/role/updatePage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (92, 'admin/sec/role/saveauthority', null, 0, null, 1, 'admin.sec.role.saveauthority', 'admin/sec/role/saveauthority', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (93, 'admin/sec/role/addHref', null, 0, null, 1, 'admin.sec.role.addHref', 'admin/sec/role/addHref', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (94, 'admin/settings/save', null, 0, null, 1, 'admin.settings.save', 'admin/settings/save', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (95, 'admin/sec/user/add', null, 0, null, 1, 'admin.sec.user.add', 'admin/sec/user/add', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (96, 'admin/sec/user/create', null, 0, null, 1, 'admin.sec.user.create', 'admin/sec/user/create', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (97, 'admin/sec/user/check', null, 0, null, 1, 'admin.sec.user.check', 'admin/sec/user/check', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (98, 'admin/sec/user/index', null, 0, null, 1, 'admin.sec.user.index', 'admin/sec/user/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (99, 'admin/sec/user/update', null, 0, null, 1, 'admin.sec.user.update', 'admin/sec/user/update', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (100, 'admin/sec/user/list', null, 0, null, 1, 'admin.sec.user.list', 'admin/sec/user/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (101, 'admin/sec/user/modifypassword', null, 0, null, 1, 'admin.sec.user.modifypassword', 'admin/sec/user/modifypassword', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (102, 'admin/sec/user/reportExport', null, 0, null, 1, 'admin.sec.user.reportExport', 'admin/sec/user/reportExport', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (103, 'admin/sec/user/auth', null, 0, null, 1, 'admin.sec.user.auth', 'admin/sec/user/auth', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (104, '首次登入修改密码', 119, 0, null, 1, 'admin.sec.user.changepsw', 'admin/sec/user/changepsw', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (105, 'admin/sec/user/modifypwd', null, 0, null, 1, 'admin.sec.user.modifypwd', 'admin/sec/user/modifypwd', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (106, 'admin/sec/user/user/single', null, 0, null, 1, 'admin.sec.user.user.single', 'admin/sec/user/user/single', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (107, 'admin/sec/user/user/multi', null, 0, null, 1, 'admin.sec.user.user.multi', 'admin/sec/user/user/multi', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (108, 'admin/sec/user/selectRole', null, 0, null, 1, 'admin.sec.user.selectRole', 'admin/sec/user/selectRole', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (109, 'admin/user/add', 681, 0, null, 1, 'admin.user.add', 'admin/user/add', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (110, 'admin/user/save', 681, 0, null, 1, 'admin.user.save', 'admin/user/save', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (111, 'admin/user/index', 681, 0, null, 1, 'admin.user.index', 'admin/user/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (112, 'admin/user/list', 681, 0, null, 1, 'admin.user.list', 'admin/user/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (113, 'admin/user/delete', 681, 0, null, 1, 'admin.user.delete', 'admin/user/delete', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (114, 'admin/user/auth', 681, 0, null, 1, 'admin.user.auth', 'admin/user/auth', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (115, 'admin/user/resetPsw', 681, 0, null, 1, 'admin.user.resetPsw', 'admin/user/resetPsw', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (116, 'admin/user/updateauth', 681, 0, null, 1, 'admin.user.updateauth', 'admin/user/updateauth', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (117, 'admin/user/changeEnable', 681, 0, null, 1, 'admin.user.changeEnable', 'admin/user/changeEnable', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (118, 'admin/user/modifypsw', 681, 0, null, 1, 'admin.user.modifypsw', 'admin/user/modifypsw', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (119, '前台', null, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (120, '后台管理', null, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (369, 'PC获取已分享的用户列表', null, 0, 'PC获取已分享的用户列表', 1, 'plan.share.getSharedUsers', 'plan/share/getSharedUsers', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (370, 'PC获取收到的共享和发出的共享列表', null, 0, 'PC获取收到的共享和发出的共享列表', 1, 'plan.share.sharePager', 'plan/share/sharePager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (371, 'PC跳转收到的分享和发出的共享页面', null, 0, 'PC跳转收到的分享和发出的共享页面', 1, 'plan.share.planShare', 'plan/share/planShare', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (372, 'PC修改共享信息', null, 0, 'PC修改共享信息', 1, 'plan.share.modifyShare', 'plan/share/modifyShare', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (373, 'PC获取分享者的列表', null, 0, 'PC获取分享者的列表', 1, 'plan.share.users', 'plan/share/users', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (374, 'WC获取收到的共享和发出的共享', null, 0, 'WC获取收到的共享和发出的共享', 1, 'ezt.wechat.planShare.sharePager', 'ezt/wechat/planShare/sharePager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (375, 'ezt/wechat/rank/list', null, 0, null, 1, 'ezt.wechat.rank.list', 'ezt/wechat/rank/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (376, 'ezt/wechat/rank/rankPager', null, 0, null, 1, 'ezt.wechat.rank.rankPager', 'ezt/wechat/rank/rankPager', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (377, 'ezt/web/remind/index', null, 0, null, 1, 'ezt.web.remind.index', 'ezt/web/remind/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (378, 'ezt/web/remind/planAlertList', null, 0, null, 1, 'ezt.web.remind.planAlertList', 'ezt/web/remind/planAlertList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (379, 'ezt/web/remind/planTrackAlerts', null, 0, null, 1, 'ezt.web.remind.planTrackAlerts', 'ezt/web/remind/planTrackAlerts', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (380, 'ezt/web/remind/getMyPlanTrackDetail', null, 0, null, 1, 'ezt.web.remind.getMyPlanTrackDetail', 'ezt/web/remind/getMyPlanTrackDetail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (381, 'ezt/web/remind/sharePlanList', null, 0, null, 1, 'ezt.web.remind.sharePlanList', 'ezt/web/remind/sharePlanList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (382, 'ezt/web/remind/specialDateList', null, 0, null, 1, 'ezt.web.remind.specialDateList', 'ezt/web/remind/specialDateList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (383, 'ezt/web/remind/calSpecialDateRemind', null, 0, null, 1, 'ezt.web.remind.calSpecialDateRemind', 'ezt/web/remind/calSpecialDateRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (384, 'ezt/web/remind/updateTask', null, 0, null, 1, 'ezt.web.remind.updateTask', 'ezt/web/remind/updateTask', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (385, 'ezt/web/remind/updateShare', null, 0, null, 1, 'ezt.web.remind.updateShare', 'ezt/web/remind/updateShare', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (386, 'ezt/web/remind/getDictData', null, 0, null, 1, 'ezt.web.remind.getDictData', 'ezt/web/remind/getDictData', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (387, 'ezt/web/remind/updateRemind', null, 0, null, 1, 'ezt.web.remind.updateRemind', 'ezt/web/remind/updateRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (388, 'ezt/wechat/remind/index', null, 0, null, 1, 'ezt.wechat.remind.index', 'ezt/wechat/remind/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (389, 'ezt/wechat/remind/planAlertList', null, 0, null, 1, 'ezt.wechat.remind.planAlertList', 'ezt/wechat/remind/planAlertList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (390, 'ezt/wechat/remind/planTrackAlerts', null, 0, null, 1, 'ezt.wechat.remind.planTrackAlerts', 'ezt/wechat/remind/planTrackAlerts', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (391, 'ezt/wechat/remind/getMyPlanTrackDetail', null, 0, null, 1, 'ezt.wechat.remind.getMyPlanTrackDetail', 'ezt/wechat/remind/getMyPlanTrackDetail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (392, 'ezt/wechat/remind/sharePlanList', null, 0, null, 1, 'ezt.wechat.remind.sharePlanList', 'ezt/wechat/remind/sharePlanList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (393, 'ezt/wechat/remind/specialDateList', null, 0, null, 1, 'ezt.wechat.remind.specialDateList', 'ezt/wechat/remind/specialDateList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (394, 'ezt/wechat/remind/updateTask', null, 0, null, 1, 'ezt.wechat.remind.updateTask', 'ezt/wechat/remind/updateTask', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (395, 'ezt/wechat/remind/updateShare', null, 0, null, 1, 'ezt.wechat.remind.updateShare', 'ezt/wechat/remind/updateShare', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (396, 'ezt/report/index', null, 0, null, 1, 'ezt.report.index', 'ezt/report/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (397, 'ezt/report/ownercover', null, 0, null, 1, 'ezt.report.ownercover', 'ezt/report/ownercover', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (398, 'ezt/report/exportExcel', null, 0, null, 1, 'ezt.report.exportExcel', 'ezt/report/exportExcel', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (399, 'ezt/report/wechat/index', null, 0, null, 1, 'ezt.report.wechat.index', 'ezt/report/wechat/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (400, 'ezt/report/wechat/ownercusnum', null, 0, null, 1, 'ezt.report.wechat.ownercusnum', 'ezt/report/wechat/ownercusnum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (401, 'ezt/report/wechat/ownernum', null, 0, null, 1, 'ezt.report.wechat.ownernum', 'ezt/report/wechat/ownernum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (402, 'ezt/web/sign/index', null, 0, null, 1, 'ezt.web.sign.index', 'ezt/web/sign/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (403, 'ezt/web/sign/sign', null, 0, null, 1, 'ezt.web.sign.sign', 'ezt/web/sign/sign', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (404, 'ezt/web/sign/queryday', null, 0, null, 1, 'ezt.web.sign.queryday', 'ezt/web/sign/queryday', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (641, 'admin/specialDateTemplate/toSpecialDateManage', null, 0, null, 1, 'admin.specialDateTemplate.toSpecialDateManage', 'admin/specialDateTemplate/toSpecialDateManage', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (642, 'admin/specialDateTemplate/saveSpecialDateWish', null, 0, null, 1, 'admin.specialDateTemplate.saveSpecialDateWish', 'admin/specialDateTemplate/saveSpecialDateWish', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (643, 'admin/specialDateTemplate/toSpecialDateWishDetail', null, 0, null, 1, 'admin.specialDateTemplate.toSpecialDateWishDetail', 'admin/specialDateTemplate/toSpecialDateWishDetail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (644, 'admin/specialDateTemplate/updateSpecialDateWish', null, 0, null, 1, 'admin.specialDateTemplate.updateSpecialDateWish', 'admin/specialDateTemplate/updateSpecialDateWish', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (645, 'admin/specialDateTemplate/deleteSpecialDateWish', null, 0, null, 1, 'admin.specialDateTemplate.deleteSpecialDateWish', 'admin/specialDateTemplate/deleteSpecialDateWish', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (646, 'ezt/pascard/index1', null, 0, null, 1, 'ezt.pascard.index1', 'ezt/pascard/index1', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (647, 'ezt/pascard/wc/archives', null, 0, null, 1, 'ezt.pascard.wc.archives', 'ezt/pascard/wc/archives', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (648, 'ezt/pascard/wc/bigdata', null, 0, null, 1, 'ezt.pascard.wc.bigdata', 'ezt/pascard/wc/bigdata', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (649, 'ezt/pascard/wc/pic', null, 0, null, 1, 'ezt.pascard.wc.pic', 'ezt/pascard/wc/pic', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (650, 'ezt/pascard/wc/mycalendar', null, 0, null, 1, 'ezt.pascard.wc.mycalendar', 'ezt/pascard/wc/mycalendar', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (651, 'admin/product/index', null, 0, null, 1, 'admin.product.index', 'admin/product/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (652, 'admin/product/productType', null, 0, null, 1, 'admin.product.productType', 'admin/product/productType', 'U');
commit;
--prompt 400 records committed...
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (653, 'admin/product/modify', null, 0, null, 1, 'admin.product.modify', 'admin/product/modify', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (429, '行事历pdf导出', 441, 0, '行事历pdf导出', 1, 'calendar.toShow', 'calendar/toShow', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (426, '客户清单', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (430, 'customer/plan/sales_trajectory', 428, 0, null, 1, 'customer.plan.sales_trajectory', 'customer/plan/sales_trajectory', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (431, '个人名片审核', 481, 0, null, 1, 'person.pascard.approver', 'person/pascard/approver', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (432, 'ezt/pascard/wechat/pic', null, 0, null, 1, 'ezt.pascard.wechat.pic', 'ezt/pascard/wechat/pic', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (433, 'ezt/weixin/jsconfig', null, 0, null, 1, 'ezt.weixin.jsconfig', 'ezt/weixin/jsconfig', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (622, '个人名片照片上传', 481, 0, null, 1, 'person.pascard.uploadphoto.index', 'person/pascard/uploadphoto/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (502, 'admin/specialDateTemplate/toSpecialDateTemplate', null, 0, null, 1, 'admin.specialDateTemplate.toSpecialDateTemplate', 'admin/specialDateTemplate/toSpecialDateTemplate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (503, 'admin/specialDateTemplate/uploadSpecialDateTemplate', null, 0, null, 1, 'admin.specialDateTemplate.uploadSpecialDateTemplate', 'admin/specialDateTemplate/uploadSpecialDateTemplate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (504, 'admin/specialDateTemplate/deleteSpecialDateTemplate', null, 0, null, 1, 'admin.specialDateTemplate.deleteSpecialDateTemplate', 'admin/specialDateTemplate/deleteSpecialDateTemplate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (506, 'sign/queryday', 601, 0, null, 1, 'sign.queryday', 'sign/queryday', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (507, 'sign/sign', 601, 0, null, 1, 'sign.sign', 'sign/sign', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (508, 'sign/querydate', 601, 0, null, 1, 'sign.querydate', 'sign/querydate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (509, '检查客户名称', 426, 0, null, 1, 'customer.ckName', 'customer/ckName', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (510, 'customer/updateRemind', null, 0, null, 1, 'customer.updateRemind', 'customer/updateRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (511, 'customer/setEztMapServiceEnabled', null, 0, null, 1, 'customer.setEztMapServiceEnabled', 'customer/setEztMapServiceEnabled', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (512, 'ezt/wechat/customers/getCustomer', null, 0, null, 1, 'ezt.wechat.customers.getCustomer', 'ezt/wechat/customers/getCustomer', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (513, 'ezt/wechat/customers/checkCustomer', null, 0, null, 1, 'ezt.wechat.customers.checkCustomer', 'ezt/wechat/customers/checkCustomer', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (514, 'ezt/wechat/customers/getAddressData.js', null, 0, null, 1, 'ezt.wechat.customers.getAddressData.js', 'ezt/wechat/customers/getAddressData.js', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (515, 'person/pascard/uploadImg', null, 0, null, 1, 'person.pascard.uploadImg', 'person/pascard/uploadImg', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (516, 'approver/index', 481, 0, null, 1, 'approver.index', 'approver/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (517, 'approver/list', 481, 0, null, 1, 'approver.list', 'approver/list', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (518, 'approver/detail', 481, 0, null, 1, 'approver.detail', 'approver/detail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (519, 'approver/saveapprover', 481, 0, null, 1, 'approver.saveapprover', 'approver/saveapprover', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (520, 'changepsw/index', null, 0, null, 1, 'changepsw.index', 'changepsw/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (521, 'changepsw/findPsw', null, 0, null, 1, 'changepsw.findPsw', 'changepsw/findPsw', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (522, 'changepsw/confrimFindPsw', null, 0, null, 1, 'changepsw.confrimFindPsw', 'changepsw/confrimFindPsw', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (523, 'changepsw/save', null, 0, null, 1, 'changepsw.save', 'changepsw/save', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (524, '/upImage3', null, 0, null, 1, '.upImage3', '/upImage3', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (525, '/upImage2', 481, 0, null, 1, '.upImage2', '/upImage2', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (526, '月度数据', 424, 0, null, 1, '.monthData', '/monthData', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (527, 'task/index', null, 0, null, 1, 'task.index', 'task/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (528, 'task/log', null, 0, null, 1, 'task.log', 'task/log', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (529, 'task/file', null, 0, null, 1, 'task.file', 'task/file', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (530, 'task/download', null, 0, null, 1, 'task.download', 'task/download', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (531, 'task/taskInit', null, 0, null, 1, 'task.taskInit', 'task/taskInit', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (532, 'task/taskDestrory', null, 0, null, 1, 'task.taskDestrory', 'task/taskDestrory', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (533, 'task/taskVaild', null, 0, null, 1, 'task.taskVaild', 'task/taskVaild', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (534, 'task/taskInvaild', null, 0, null, 1, 'task.taskInvaild', 'task/taskInvaild', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (535, 'task/taskStart', null, 0, null, 1, 'task.taskStart', 'task/taskStart', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (536, 'task/taskStop', null, 0, null, 1, 'task.taskStop', 'task/taskStop', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (537, 'task/taskUpdate', null, 0, null, 1, 'task.taskUpdate', 'task/taskUpdate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (538, 'task/taskAdd', null, 0, null, 1, 'task.taskAdd', 'task/taskAdd', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (539, 'task/taskRunNow', null, 0, null, 1, 'task.taskRunNow', 'task/taskRunNow', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (540, 'ezt/wechat/calendar/toBirthdayCard', null, 0, null, 1, 'ezt.wechat.calendar.toBirthdayCard', 'ezt/wechat/calendar/toBirthdayCard', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (541, 'ezt/wechat/calendar/updateBirthdayCardImge', null, 0, null, 1, 'ezt.wechat.calendar.updateBirthdayCardImge', 'ezt/wechat/calendar/updateBirthdayCardImge', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (542, 'calendar/toSpecialDateTemplate', null, 0, null, 1, 'calendar.toSpecialDateTemplate', 'calendar/toSpecialDateTemplate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (543, 'calendar/uploadSpecialDateTemplate', null, 0, null, 1, 'calendar.uploadSpecialDateTemplate', 'calendar/uploadSpecialDateTemplate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (544, 'calendar/deleteSpecialDateTemplate', null, 0, null, 1, 'calendar.deleteSpecialDateTemplate', 'calendar/deleteSpecialDateTemplate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (559, 'ezt/targetmanager/wechat/index', null, 0, null, 1, 'ezt.targetmanager.wechat.index', 'ezt/targetmanager/wechat/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (560, 'ezt/targetmanager/wechat/targets', null, 0, null, 1, 'ezt.targetmanager.wechat.targets', 'ezt/targetmanager/wechat/targets', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (561, 'ezt/targetmanager/wechat/branchTargets', null, 0, null, 1, 'ezt.targetmanager.wechat.branchTargets', 'ezt/targetmanager/wechat/branchTargets', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (562, 'ezt/targetmanager/wechat/getYears', null, 0, null, 1, 'ezt.targetmanager.wechat.getYears', 'ezt/targetmanager/wechat/getYears', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (563, 'ezt/targetmanager/wechat/getMonthWeeks', null, 0, null, 1, 'ezt.targetmanager.wechat.getMonthWeeks', 'ezt/targetmanager/wechat/getMonthWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (564, 'ezt/targetmanager/wechat/settingIndex', null, 0, null, 1, 'ezt.targetmanager.wechat.settingIndex', 'ezt/targetmanager/wechat/settingIndex', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (565, 'ezt/targetmanager/wechat/setted', null, 0, null, 1, 'ezt.targetmanager.wechat.setted', 'ezt/targetmanager/wechat/setted', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (566, 'ezt/targetmanager/wechat/getAfterMonthWeeks', null, 0, null, 1, 'ezt.targetmanager.wechat.getAfterMonthWeeks', 'ezt/targetmanager/wechat/getAfterMonthWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (567, 'ezt/targetmanager/wechat/targetSetted', null, 0, null, 1, 'ezt.targetmanager.wechat.targetSetted', 'ezt/targetmanager/wechat/targetSetted', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (568, 'ezt/targetmanager/wechat/savetarget', null, 0, null, 1, 'ezt.targetmanager.wechat.savetarget', 'ezt/targetmanager/wechat/savetarget', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (569, 'ezt/targetmanager/wechat/months', null, 0, null, 1, 'ezt.targetmanager.wechat.months', 'ezt/targetmanager/wechat/months', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (570, 'ezt/targetmanager/wechat/weeks', null, 0, null, 1, 'ezt.targetmanager.wechat.weeks', 'ezt/targetmanager/wechat/weeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (571, 'ezt/targetmanager/wechat/online', null, 0, null, 1, 'ezt.targetmanager.wechat.online', 'ezt/targetmanager/wechat/online', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (572, 'ezt/wechat/remind/calSpecialDateRemind', null, 0, null, 1, 'ezt.wechat.remind.calSpecialDateRemind', 'ezt/wechat/remind/calSpecialDateRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (573, 'ezt/wechat/remind/getDictData', null, 0, null, 1, 'ezt.wechat.remind.getDictData', 'ezt/wechat/remind/getDictData', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (574, 'ezt/wechat/remind/updateRemind', null, 0, null, 1, 'ezt.wechat.remind.updateRemind', 'ezt/wechat/remind/updateRemind', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (575, 'plan/getPlanDetail', 428, 0, null, 1, 'plan.getPlanDetail', 'plan/getPlanDetail', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (576, 'ezt/wechat/knowledge/viewArticle', null, 0, null, 1, 'ezt.wechat.knowledge.viewArticle', 'ezt/wechat/knowledge/viewArticle', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (577, '团队报表首页', 593, 0, null, 1, 'teamReport.index', 'teamReport/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (578, '团队报表-获取下属2层', 593, 0, null, 1, 'teamReport.getChildsLv2', 'teamReport/getChildsLv2', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (579, '团队报表-获取工作月', 593, 0, null, 1, 'teamReport.getMonths', 'teamReport/getMonths', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (580, '团队报表-获取工作周', 593, 0, null, 1, 'teamReport.getWeeks', 'teamReport/getWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (581, '团队报表-查询下属', 593, 0, null, 1, 'teamReport.getChildsList', 'teamReport/getChildsList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (582, '团队报表-统计', 593, 0, null, 1, 'teamReport.queryPeort', 'teamReport/queryPeort', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (583, '团队报表-导出', 593, 0, null, 1, 'teamReport.exportExcel', 'teamReport/exportExcel', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (584, '团队报表-树状', 593, 0, null, 1, 'teamReport.getOrsNodes', 'teamReport/getOrsNodes', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (585, 'report/ownercusnum', null, 0, null, 1, 'report.ownercusnum', 'report/ownercusnum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (586, 'report/ownercover', null, 0, null, 1, 'report.ownercover', 'report/ownercover', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (587, 'report/ownercovercusnum', null, 0, null, 1, 'report.ownercovercusnum', 'report/ownercovercusnum', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (588, 'report/index', null, 0, null, 1, 'report.index', 'report/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (589, 'report/exportExcel', null, 0, null, 1, 'report.exportExcel', 'report/exportExcel', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (590, 'customer/plan/subStatus', 428, 0, null, 1, 'customer.plan.subStatus', 'customer/plan/subStatus', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (591, 'customer/plan/redirect', 428, 0, null, 1, 'customer.plan.redirect', 'customer/plan/redirect', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (592, 'ezt/wechat/customerPlan/subStatus', null, 0, null, 1, 'ezt.wechat.customerPlan.subStatus', 'ezt/wechat/customerPlan/subStatus', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (593, '团队报表', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (601, '签到', 119, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (602, 'admin/org/getOrsNodes', null, 0, null, 1, 'admin.org.getOrsNodes', 'admin/org/getOrsNodes', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (603, 'sign/index', 601, 0, null, 1, 'sign.index', 'sign/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (604, 'admin/task/index', null, 0, null, 1, 'admin.task.index', 'admin/task/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (605, 'admin/task/log', null, 0, null, 1, 'admin.task.log', 'admin/task/log', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (606, 'admin/task/file', null, 0, null, 1, 'admin.task.file', 'admin/task/file', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (607, 'admin/task/download', null, 0, null, 1, 'admin.task.download', 'admin/task/download', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (608, 'admin/task/taskInit', null, 0, null, 1, 'admin.task.taskInit', 'admin/task/taskInit', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (609, 'admin/task/taskDestrory', null, 0, null, 1, 'admin.task.taskDestrory', 'admin/task/taskDestrory', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (610, 'admin/task/taskVaild', null, 0, null, 1, 'admin.task.taskVaild', 'admin/task/taskVaild', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (611, 'admin/task/taskInvaild', null, 0, null, 1, 'admin.task.taskInvaild', 'admin/task/taskInvaild', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (612, 'admin/task/taskStart', null, 0, null, 1, 'admin.task.taskStart', 'admin/task/taskStart', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (613, 'admin/task/taskStop', null, 0, null, 1, 'admin.task.taskStop', 'admin/task/taskStop', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (614, 'admin/task/taskUpdate', null, 0, null, 1, 'admin.task.taskUpdate', 'admin/task/taskUpdate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (615, 'admin/task/taskAdd', null, 0, null, 1, 'admin.task.taskAdd', 'admin/task/taskAdd', 'U');
commit;
--prompt 500 records committed...
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (616, 'admin/task/taskRunNow', null, 0, null, 1, 'admin.task.taskRunNow', 'admin/task/taskRunNow', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (661, 'ezt/worker/info', null, 0, null, 1, 'ezt.worker.info', 'ezt/worker/info', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (662, 'ezt/wechat/customers/setEztMapServiceEnabled', null, 0, null, 1, 'ezt.wechat.customers.setEztMapServiceEnabled', 'ezt/wechat/customers/setEztMapServiceEnabled', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (663, 'ezt/pascard/wc/comrate', null, 0, null, 1, 'ezt.pascard.wc.comrate', 'ezt/pascard/wc/comrate', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (664, 'ezt/pascard/wc/metlife', null, 0, null, 1, 'ezt.pascard.wc.metlife', 'ezt/pascard/wc/metlife', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (665, 'ezt/pascard/wc/nbs', null, 0, null, 1, 'ezt.pascard.wc.nbs', 'ezt/pascard/wc/nbs', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (666, 'ezt/pascard/wc/nbs2', null, 0, null, 1, 'ezt.pascard.wc.nbs2', 'ezt/pascard/wc/nbs2', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (667, 'ezt/pascard/wc/saveImpressionLable', null, 0, null, 1, 'ezt.pascard.wc.saveImpressionLable', 'ezt/pascard/wc/saveImpressionLable', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (668, 'admin/task/delFiles', null, 0, null, 1, 'admin.task.delFiles', 'admin/task/delFiles', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (670, 'ezt/targetmanager/wechat/getAllWeeks', null, 0, null, 1, 'ezt.targetmanager.wechat.getAllWeeks', 'ezt/targetmanager/wechat/getAllWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (681, '用户管理', 120, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (682, '外勤用户管理', 120, 0, null, 1, null, null, 'G');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (741, 'target/manager/targets', null, 0, null, 1, 'target.manager.targets', 'target/manager/targets', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (742, 'target/manager/setMonth', null, 0, null, 1, 'target.manager.setMonth', 'target/manager/setMonth', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (743, 'target/manager/setWeek', null, 0, null, 1, 'target.manager.setWeek', 'target/manager/setWeek', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (744, 'target/manager/getYears', null, 0, null, 1, 'target.manager.getYears', 'target/manager/getYears', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (745, 'target/manager/online', null, 0, null, 1, 'target.manager.online', 'target/manager/online', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (746, 'target/manager/getMonthWeeks', null, 0, null, 1, 'target.manager.getMonthWeeks', 'target/manager/getMonthWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (747, 'target/manager/index', null, 0, null, 1, 'target.manager.index', 'target/manager/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (748, 'target/manager/search', null, 0, null, 1, 'target.manager.search', 'target/manager/search', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (749, 'target/manager/branchTargets', null, 0, null, 1, 'target.manager.branchTargets', 'target/manager/branchTargets', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (750, 'target/manager/getAfterMonthWeeks', null, 0, null, 1, 'target.manager.getAfterMonthWeeks', 'target/manager/getAfterMonthWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (751, 'target/manager/getAllWeeks', null, 0, null, 1, 'target.manager.getAllWeeks', 'target/manager/getAllWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (752, 'target/manager/targetSetted', null, 0, null, 1, 'target.manager.targetSetted', 'target/manager/targetSetted', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (753, 'target/manager/savetarget', null, 0, null, 1, 'target.manager.savetarget', 'target/manager/savetarget', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (754, 'target/manager/months', null, 0, null, 1, 'target.manager.months', 'target/manager/months', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (755, 'target/manager/weeks', null, 0, null, 1, 'target.manager.weeks', 'target/manager/weeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (701, 'admin/city/getAH', null, 0, null, 1, 'admin.city.getAH', 'admin/city/getAH', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (702, 'admin/city/getAH1', null, 0, null, 1, 'admin.city.getAH1', 'admin/city/getAH1', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (703, 'ezt/worker/policy', null, 0, null, 1, 'ezt.worker.policy', 'ezt/worker/policy', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (704, '目标管理', 501, 0, null, 1, 'target.index', 'target/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (705, '目标查询', 501, 0, null, 1, 'target.query', 'target/query', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (706, '查询数据', 501, 0, null, 1, 'target.targets', 'target/targets', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (707, '月目标设定页面', 501, 0, null, 1, 'target.month', 'target/month', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (708, '周目标设定页面', 501, 0, null, 1, 'target.week', 'target/week', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (709, '添加目标', 501, 0, null, 1, 'target.save', 'target/save', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (710, '获取已经设定的目标', 501, 0, null, 1, 'target.setted', 'target/setted', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (711, '获取年列表', 501, 0, null, 1, 'target.years', 'target/years', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (712, '获取月或周列表', 501, 0, null, 1, 'target.mowes', 'target/mowes', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (714, '获取月列表', 501, 0, null, 1, 'target.months', 'target/months', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (715, '获取周列表', 501, 0, null, 1, 'target.weeks', 'target/weeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (716, 'ezt/wechat/target/index', null, 0, null, 1, 'ezt.wechat.target.index', 'ezt/wechat/target/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (717, 'ezt/wechat/target/pre', null, 0, null, 1, 'ezt.wechat.target.pre', 'ezt/wechat/target/pre', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (718, 'ezt/wechat/target/set', null, 0, null, 1, 'ezt.wechat.target.set', 'ezt/wechat/target/set', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (719, 'ezt/wechat/target/query', null, 0, null, 1, 'ezt.wechat.target.query', 'ezt/wechat/target/query', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (720, 'ezt/wechat/target/targets', null, 0, null, 1, 'ezt.wechat.target.targets', 'ezt/wechat/target/targets', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (721, 'ezt/wechat/target/setted', null, 0, null, 1, 'ezt.wechat.target.setted', 'ezt/wechat/target/setted', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (722, 'ezt/wechat/target/months', null, 0, null, 1, 'ezt.wechat.target.months', 'ezt/wechat/target/months', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (723, 'ezt/wechat/target/weeks', null, 0, null, 1, 'ezt.wechat.target.weeks', 'ezt/wechat/target/weeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (724, 'ezt/wechat/target/save', null, 0, null, 1, 'ezt.wechat.target.save', 'ezt/wechat/target/save', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (725, 'ezt/wechat/target/years', null, 0, null, 1, 'ezt.wechat.target.years', 'ezt/wechat/target/years', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (726, 'ezt/wechat/target/mowes', null, 0, null, 1, 'ezt.wechat.target.mowes', 'ezt/wechat/target/mowes', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (727, '团队报表', 593, 0, null, 1, 'team.report.index', 'team/report/index', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (728, 'team/report/getChildsLv2', null, 0, null, 1, 'team.report.getChildsLv2', 'team/report/getChildsLv2', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (729, '获取月列表', 593, 0, null, 1, 'team.report.getMonths', 'team/report/getMonths', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (730, '获取周列表', 593, 0, null, 1, 'team.report.getWeeks', 'team/report/getWeeks', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (731, '获取职级用户', 593, 0, null, 1, 'team.report.getChildsList', 'team/report/getChildsList', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (732, '报表查询', 593, 0, null, 1, 'team.report.query', 'team/report/query', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (733, '报表导出', 593, 0, null, 1, 'team.report.exportExcel', 'team/report/exportExcel', 'U');
insert into JDP_RESOURCE (cid, name_, parent_id, sort_index, descript_, enabled_, per_string, res_string, res_type)
values (734, 'team/report/getOrsNodes', null, 0, null, 1, 'team.report.getOrsNodes', 'team/report/getOrsNodes', 'U');
commit;
--prompt 560 records loaded
--prompt Loading JDP_USER...
insert into JDP_USER (cid, account, accountstate, channel, city, credentials_expired, credentials_expired_time, description, email, enabled_, errortrytime, expired_, expired_time, first_logined, first_logined_time, idno, is_admin, istype, last_logined_time, locked_, locked_time, loginnum, org_code, password, phone, province, psw_change_date, puser_id, register_date, role_id, salt_, sex, username, pic_path, smcode, smname, amcdoe, amname, rmcode, rmname, org_grade, branchattr, modify_time, modify_by, smbrach, ambrach, rmbrach, cus_update_time, adname, adcode, adbrach, org_short_name, org_name)
values (2, 'admin', '01', null, '86', null, null, null, null, 1, to_timestamp('27-03-2017 14:49:40.266000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, null, 1, to_timestamp('26-10-2016 11:15:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, 1, 'undefined', null, 0, null, 1, '86', '0e653e66a400cac3678d52dca4542f1c45a7b776', null, null, null, null, to_timestamp('03-05-2016 00:00:00.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, 'd036422bee6612f8762cec70f684977b', '0', 'admin', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
insert into JDP_USER (cid, account, accountstate, channel, city, credentials_expired, credentials_expired_time, description, email, enabled_, errortrytime, expired_, expired_time, first_logined, first_logined_time, idno, is_admin, istype, last_logined_time, locked_, locked_time, loginnum, org_code, password, phone, province, psw_change_date, puser_id, register_date, role_id, salt_, sex, username, pic_path, smcode, smname, amcdoe, amname, rmcode, rmname, org_grade, branchattr, modify_time, modify_by, smbrach, ambrach, rmbrach, cus_update_time, adname, adcode, adbrach, org_short_name, org_name)
values (597515, '8601004545', '01', null, null, null, null, null, '8601004545@metlife.com.cn', 1, null, 0, null, 1, to_timestamp('26-10-2016 11:15:02.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '350403198211050065', null, '02', to_timestamp('27-03-2017 16:26:49.693000', 'dd-mm-yyyy hh24:mi:ss.ff'), 0, null, 0, '8601', 'a802a0c257dc322f28953e9c062c26eb5eb0b480', '15901414070', null, null, null, to_timestamp('27-09-2016 16:13:41.446402', 'dd-mm-yyyy hh24:mi:ss.ff'), 8, '409b143cf4e923f1d49be7eaa283bb96', '0', '张小娟', null, '8601003405', '王晓春', '8601001236', '冯冰', '8601000125', '刘玉峰', 'LP', '86010113017003', to_timestamp('10-10-2016 01:35:36.006039', 'dd-mm-yyyy hh24:mi:ss.ff'), 'TASKJOB', '86010113017003', '86010113017', null, to_timestamp('13-10-2016 14:16:36.', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null, '86010113', null, null);
commit;
--prompt 2 records loaded
--set feedback on
--set define on
--prompt Done.
