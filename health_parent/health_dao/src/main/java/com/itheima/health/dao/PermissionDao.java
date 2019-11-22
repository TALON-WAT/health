package com.itheima.health.dao;

import com.itheima.health.pojo.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author TALON WAT
 * @date 2019-11-21 20:21
 */
@Repository
public interface PermissionDao {

    Set<Permission> findPermissionsByRoleId(Integer roleId);

}
