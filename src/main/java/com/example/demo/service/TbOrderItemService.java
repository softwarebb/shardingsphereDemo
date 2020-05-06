package com.example.demo.service;

import com.example.demo.model.TbOrderItem;
import com.querydsl.core.types.Predicate;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
public interface TbOrderItemService {
    TbOrderItem save(TbOrderItem item);

    void deleteById(Integer id);

    List<TbOrderItem> saveAll(List<TbOrderItem> items);

    TbOrderItem findById(Integer id);

    List<TbOrderItem> findAll();

    List<TbOrderItem> findAll(Predicate predicate);
}
