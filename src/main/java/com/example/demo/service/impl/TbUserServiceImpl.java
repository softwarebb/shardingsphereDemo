package com.example.demo.service.impl;

import com.example.demo.dao.TbUserRepo;
import com.example.demo.model.TbUser;
import com.example.demo.service.TbUserService;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/29
 * @Desc
 */
@Slf4j
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    TbUserRepo repo;

    @Override
    public TbUser save(TbUser tbUser) {
        return repo.save(tbUser);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<TbUser> saveAll(List<TbUser> tbOrder) {
        return repo.saveAll(tbOrder);
    }

    @Override
    public TbUser findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<TbUser> findAll() {
        return repo.findAll();
    }

    @Override
    public List<TbUser> findAll(Predicate predicate) {
        return Lists.newArrayList(repo.findAll(predicate));
    }
}
