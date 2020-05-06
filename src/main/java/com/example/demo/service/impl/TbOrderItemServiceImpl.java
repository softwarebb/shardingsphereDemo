package com.example.demo.service.impl;

import com.example.demo.dao.TbOrderItemRepo;
import com.example.demo.dao.TbOrderRepo;
import com.example.demo.model.TbOrder;
import com.example.demo.model.TbOrderItem;
import com.example.demo.service.TbOrderItemService;
import com.example.demo.service.TbOrderService;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
@Service
@Slf4j
public class TbOrderItemServiceImpl implements TbOrderItemService {

    @Autowired
    TbOrderItemRepo repo;

    @Override
    public TbOrderItem save(TbOrderItem item) {
        return repo.save(item);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<TbOrderItem> saveAll(List<TbOrderItem> items) {
        return repo.saveAll(items);
    }

    @Override
    public TbOrderItem findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<TbOrderItem> findAll() {
        return repo.findAll();
    }

    @Override
    public List<TbOrderItem> findAll(Predicate predicate) {
        return Lists.newArrayList(repo.findAll(predicate));
    }
}
