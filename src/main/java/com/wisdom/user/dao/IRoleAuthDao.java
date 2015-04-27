package com.wisdom.user.dao;

import com.wisdom.common.model.AuthAction;
import com.wisdom.common.model.ObjectTypes;
import com.wisdom.common.model.Role;
import com.wisdom.common.model.RolePermission;
import com.wisdom.common.model.RolePrivate;

public interface IRoleAuthDao {
	
	public boolean addAuthAction(AuthAction authAction);
	
	public boolean deleteAuthAction(AuthAction authAction);
	
	public boolean updateAuthAction(AuthAction authAction);
	
	public AuthAction getAuthAction(long id);
	
	public boolean addObjectTypes(ObjectTypes objectTypes);
	
	public boolean deleteObjectTypes(ObjectTypes objectTypes);
	
	public boolean updateObjectTypes(ObjectTypes objectTypes);
	
	public ObjectTypes getObjectTypes(long id);
	
	public boolean addRole(Role role);
	
	public boolean deleteRole(Role role);
	
	public boolean updateRole(Role role);
	
	public Role getRole(long id);
	
	public boolean addRolePermission(RolePermission rolePermission);
	
	public boolean deleteRolePermission(RolePermission rolePermission);
	
	public boolean updateRolePermission(RolePermission rolePermission);
	
	public RolePermission getRolePermission(long id);
	
	public boolean addRolePrivate(RolePrivate rolePrivate);
	
	public boolean deleteRolePrivate(RolePrivate rolePrivate);
	
	public boolean updateRolePrivate(RolePrivate rolePrivate);
	
	public RolePrivate getRolePrivate(long id);
	
}
