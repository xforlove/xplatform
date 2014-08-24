

insert into auth_user(id, name, login_id, login_pass,  stat_flag, email, phone, pass_error_cnt) 
 values(1, '系统管理员', 'sysadmin', '000000', 'NORMAL', 'admin@xplatform.com', '15221464792', 0);
insert into auth_user(id, name, login_id, login_pass,  stat_flag, email, phone, pass_error_cnt) 
 values(2, '西域春秋', 'rockey', '000000', 'NORMAL', '8765046@qq.com', '15200000000', 0);
 
insert into auth_role(id, code, name, stat_flag, descn)  values(1, 'admin', '管理员', 'NORMAL', '拥有平台所有权限');
 
insert into auth_user_role(user_id, role_id)  values(1, 1);

insert into app_func_group(id, name, stat_flag) values(1, '系统', 'NORMAL');
insert into app_func_group(id, name, stat_flag) values(2, '权限', 'NORMAL');
insert into app_func_group(id, name, stat_flag) values(3, '流程', 'NORMAL');
 
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(1, 'user:list', '用户列表', 1, '/auth/user-list', 'NORMAL', NULL, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(2, 'user:create', '用户新增', 2, '/auth/user-input', 'NORMAL', 1, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(3, 'user:edit', '用户编辑', 2, '/auth/user-input', 'NORMAL', 1, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(4, 'user:remove', '用户删除', 2, '/auth/user-remove', 'NORMAL', 1, 2);
 
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(5, 'role:list', '角色列表', 1, '/auth/role-list', 'NORMAL', NULL, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(6, 'role:create', '角色新增', 2, '/auth/role-input', 'NORMAL', 5, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(7, 'role:edit', '角色编辑', 2, '/auth/role-input', 'NORMAL', 5, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(8, 'role:remove', '角色删除', 2, '/auth/role-remove', 'NORMAL', 5, 2);

insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(9, 'funcgroup:list', '功能组列表', 2, '/app/funcgroup-list', 'NORMAL', NULL, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(10, 'funcgroup:create', '功能组新增', 2, '/app/funcgroup-input', 'NORMAL', 9, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(11, 'funcgroup:edit', '功能组编辑', 2, '/app/funcgroup-input', 'NORMAL', 9, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(12, 'funcgroup:remove', '功能组删除', 2, '/app/funcgroup-remove', 'NORMAL', 9, 2);

insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(13, 'function:list', '功能列表', 2, '/app/function-list', 'NORMAL', NULL, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(14, 'function:create', '功能新增', 2, '/app/function-input', 'NORMAL', 13, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(15, 'function:edit', '功能编辑', 2, '/app/function-input', 'NORMAL', 13, 2);
insert into app_function(id, code, name, level, action, stat_flag, parent_id, group_id) values(16, 'function:remove', '功能删除', 2, '/app/function-remove', 'NORMAL', 13, 2);

insert into auth_role_function(role_id, function_id) values(1, 1);
insert into auth_role_function(role_id, function_id) values(1, 2);
insert into auth_role_function(role_id, function_id) values(1, 3);
insert into auth_role_function(role_id, function_id) values(1, 4);
insert into auth_role_function(role_id, function_id) values(1, 5);
insert into auth_role_function(role_id, function_id) values(1, 6);
insert into auth_role_function(role_id, function_id) values(1, 7);
insert into auth_role_function(role_id, function_id) values(1, 8);
insert into auth_role_function(role_id, function_id) values(1, 9);
insert into auth_role_function(role_id, function_id) values(1, 10);
insert into auth_role_function(role_id, function_id) values(1, 11);
insert into auth_role_function(role_id, function_id) values(1, 12);
insert into auth_role_function(role_id, function_id) values(1, 13);
insert into auth_role_function(role_id, function_id) values(1, 14);
insert into auth_role_function(role_id, function_id) values(1, 15);
insert into auth_role_function(role_id, function_id) values(1, 16);