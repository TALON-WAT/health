package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author TALON WAT
 * @date 2019-11-15 19:14
 */
@Repository
public interface CheckGroupDao {
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(@Param("checkGroupId") Integer checkGroupId,@Param("checkitemId") Integer checkitemId);

    Page<CheckGroup> selectByCondition(String queryString);

    void deleteCheckGroupAndCheckItem(Integer id);

    void deleteCheckGroup(Integer id);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup);

    List<CheckGroup> findAll();

    List<CheckGroup> findCheckGroupListById(Integer id);
}
