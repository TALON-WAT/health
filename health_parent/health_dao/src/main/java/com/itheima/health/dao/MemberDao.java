package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Member;
import org.springframework.stereotype.Repository;

/**
 * @author TALON WAT
 * @date 2019-11-19 17:17
 */
@Repository
public interface MemberDao {
    Member findByTelephone(String phoneNumber);

    void add(Member member);

    void update(Member member);

    public void edit(Member member);

    public Integer findMemberCountBeforeDate(String date);

    public Integer findMemberCountByDate(String date);

    public Integer findMemberCountAfterDate(String date);

    public Integer findMemberTotalCount();

    public Page<Member> selectByCondition(String queryString);

    public void deleteById(Integer id);

    public Member findById(Integer id);


}
