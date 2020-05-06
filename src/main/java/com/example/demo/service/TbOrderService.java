package com.example.demo.service;

import com.example.demo.model.TbOrder;
import com.querydsl.core.types.Predicate;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
public interface TbOrderService {
    TbOrder save(TbOrder tbOrder);

    void deleteById(Integer id);

    List<TbOrder> saveAll(List<TbOrder> tbOrder);

    TbOrder findById(Integer id);

    List<TbOrder> findAll();

    List<TbOrder> findAll(Predicate predicate);
}
