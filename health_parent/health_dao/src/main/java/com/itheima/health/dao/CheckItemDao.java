package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author TALON WAT
 * @date 2019-11-13 22:39
 */
@Repository
public interface CheckItemDao {
    public void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    void deleteById(Integer id);

    long findCountByCheckItemId(Integer id);

    CheckItem findById(Integer id);

    void editUpdate(CheckItem checkItem);

    List<CheckItem> findAll();

    List<CheckItem> findCheckItemListById(Integer id);

}
