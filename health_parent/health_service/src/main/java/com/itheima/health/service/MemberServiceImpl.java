package com.itheima.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TALON WAT
 * @date 2019-11-19 20:36
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    /**
     * 通过list 月份 寻找 list count
     * @param list
     * @return
     */
    @Override
    public List<Integer> findMemberCountByMonth(List<String> list) {
        List<Integer> countList = new ArrayList<>();
        for (String month : list) {
            Integer count = memberDao.findMemberCountBeforeDate(month);
            countList.add(count);
        }
        return countList;
    }
}
