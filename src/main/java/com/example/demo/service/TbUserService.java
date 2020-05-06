package com.example.demo.service;

import com.example.demo.model.TbUser;
import com.querydsl.core.types.Predicate;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
public interface TbUserService {
    TbUser save(TbUser tbUser);

    void deleteById(Integer id);

    List<TbUser> saveAll(List<TbUser> tbOrder);

    TbUser findById(Integer id);

    List<TbUser> findAll();

    List<TbUser> findAll(Predicate predicate);
}
