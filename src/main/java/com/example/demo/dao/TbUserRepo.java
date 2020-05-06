package com.example.demo.dao;

import com.example.demo.model.TbOrder;
import com.example.demo.model.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
@Repository
public interface TbUserRepo extends JpaRepository<TbUser, Integer> , QuerydslPredicateExecutor<TbUser> {
}
