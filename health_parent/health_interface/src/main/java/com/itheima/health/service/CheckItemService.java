package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @author TALON WAT
 * @date 2019-11-13 22:34
 *      检查项服务接口
 */
public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void delete(Integer id);

    CheckItem findById(Integer id);

    void editUpdate(CheckItem checkItem);

    List<CheckItem> findAll();

}
