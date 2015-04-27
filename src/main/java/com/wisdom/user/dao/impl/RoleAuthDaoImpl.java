package com.wisdom.user.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wisdom.common.model.AuthAction;
import com.wisdom.common.model.ObjectTypes;
import com.wisdom.common.model.Role;
import com.wisdom.common.model.RolePermission;
import com.wisdom.common.model.RolePrivate;
import com.wisdom.user.dao.IRoleAuthDao;
import com.wisdom.user.mapper.AuthActionMapper;
import com.wisdom.user.mapper.ObjectTypesMapper;
import com.wisdom.user.mapper.RoleMapper;
import com.wisdom.user.mapper.RolePermissionMapper;
import com.wisdom.user.mapper.RolePrivateMapper;

public class RoleAuthDaoImpl implements IRoleAuthDao {

	private static final Logger logger = LoggerFactory
			.getLogger(RoleAuthDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean addAuthAction(AuthAction authAction) {
		String sql = "insert into auth_action (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, authAction.getName(),
				authAction.getCreateTime());
		logger.debug("addAuthAction result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAuthAction(AuthAction authAction) {
		String sql = "delete from auth_action where id = ?";
		int affectedRows = jdbcTemplate.update(sql, authAction.getId());
		logger.debug("deleteAuthAction result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateAuthAction(AuthAction authAction) {
		String sql = "update auth_action set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, authAction.getName(),
				authAction.getId());
		logger.debug("updateAuthAction result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public AuthAction getAuthAction(long id) {
		String sql = "select * from auth_action where id = ?";
		AuthAction user = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new AuthActionMapper());
		return user;
	}

	@Override
	public boolean addObjectTypes(ObjectTypes objectTypes) {
		String sql = "insert into object_types (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, objectTypes.getName(),
				objectTypes.getCreateTime());
		logger.debug("addObjectTypes result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteObjectTypes(ObjectTypes objectTypes) {
		String sql = "delete from object_types where id = ?";
		int affectedRows = jdbcTemplate.update(sql, objectTypes.getId());
		logger.debug("deleteObjectTypes result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateObjectTypes(ObjectTypes objectTypes) {
		String sql = "update object_types set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, objectTypes.getName(),
				objectTypes.getId());
		logger.debug("updateObjectTypes result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public ObjectTypes getObjectTypes(long id) {
		String sql = "select * from object_types where id = ?";
		ObjectTypes objectTypes = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new ObjectTypesMapper());
		return objectTypes;
	}

	@Override
	public boolean addRole(Role role) {
		String sql = "insert into role (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, role.getName(),
				role.getCreateTime());
		logger.debug("addRole result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteRole(Role role) {
		String sql = "delete from role where id = ?";
		int affectedRows = jdbcTemplate.update(sql, role.getId());
		logger.debug("deleteRole result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateRole(Role role) {
		String sql = "update role set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, role.getName(),
				role.getId());
		logger.debug("updateRole result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public Role getRole(long id) {
		String sql = "select * from role where id = ?";
		Role role = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new RoleMapper());
		return role;
	}

	@Override
	public boolean addRolePermission(RolePermission rolePermission) {
		String sql = "insert into role_permission (object_type_id, auth_action_id, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, rolePermission.getObjectTypeId(),
				rolePermission.getAuthActionId(), rolePermission.getCreateTime());
		logger.debug("addRolePermission result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteRolePermission(RolePermission rolePermission) {
		String sql = "delete from role_permission where id = ?";
		int affectedRows = jdbcTemplate.update(sql, rolePermission.getId());
		logger.debug("deleteRolePermission result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateRolePermission(RolePermission rolePermission) {
		String sql = "update role_permission set object_type_id=?, auth_action=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, rolePermission.getObjectTypeId(),
				rolePermission.getAuthActionId(), rolePermission.getId());
		logger.debug("updateRolePermission result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public RolePermission getRolePermission(long id) {
		String sql = "select * from role_permission where id = ?";
		RolePermission rolePermission = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new RolePermissionMapper());
		return rolePermission;
	}

	@Override
	public boolean addRolePrivate(RolePrivate rolePrivate) {
		String sql = "insert into role_private (name, company_id, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, rolePrivate.getName(),
				rolePrivate.getCompanyId(), rolePrivate.getCreateTime());
		logger.debug("addRolePrivate result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteRolePrivate(RolePrivate rolePrivate) {
		String sql = "delete from role_private where id = ?";
		int affectedRows = jdbcTemplate.update(sql, rolePrivate.getId());
		logger.debug("deleteRolePrivate result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateRolePrivate(RolePrivate rolePrivate) {
		String sql = "update role_private set name=?, company_id=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, rolePrivate.getName(),
				rolePrivate.getCompanyId(), rolePrivate.getId());
		logger.debug("updateRolePrivate result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public RolePrivate getRolePrivate(long id) {
		String sql = "select * from role_private where id = ?";
		RolePrivate rolePrivate = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new RolePrivateMapper());
		return rolePrivate;
	}

}