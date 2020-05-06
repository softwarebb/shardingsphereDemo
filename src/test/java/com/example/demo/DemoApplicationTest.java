package com.example.demo;

import com.example.demo.customalgorithm.CustomShardingQueryAssistedEncryptor;
import com.example.demo.model.*;
import com.example.demo.service.TbOrderItemService;
import com.example.demo.service.TbOrderService;
import com.example.demo.service.TbUserService;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTest {

    @Autowired
    TbOrderService tbOrderService;
    @Autowired
    TbOrderItemService tbOrderItemService;
    @Autowired
    TbUserService tbUserService;

    @Autowired
    DataSource dataSource;

    /**
     * standard 策略和算法
     */
    @Test
    public void saveByMerchant() {
        List<String> merchantList = Lists.newArrayList("aliyun", "taobao", "tmall");
        // 测试20笔订单，且所属商户随机产生
        for (int i = 0; i < 20; i++) {
            TbOrder order = new TbOrder();
            order.setUserId(i);
            order.setOrderNo(System.currentTimeMillis());
            order.setOrderTime(new Date());
            order.setMerchant(merchantList.get(new Random().nextInt(merchantList.size())));
            tbOrderService.save(order);
        }
    }

    /**
     * inline 按user_id分库，order_no分表
     */
    @Test
    public void saveByOrderNo() {
        List<String> merchantList = Lists.newArrayList("aliyun", "taobao", "tmall");
        List<String> fruits = Lists.newArrayList("Apple", "Banana", "Orange", "Grape", "strawberry");
        // 测试20笔订单，且所属商户随机产生
        for (int i = 0; i < 20; i++) {
            long orderNo = System.currentTimeMillis();
            TbOrder order = new TbOrder();
            order.setUserId(i);
            order.setOrderNo(orderNo);
            order.setOrderTime(new Date());
            order.setMerchant(merchantList.get(new Random().nextInt(merchantList.size())));
            tbOrderService.save(order);
            TbOrderItem item = new TbOrderItem();
            item.setOrderNo(orderNo);
            item.setName(fruits.get(i / 4) + i);
            item.setPrice(i + 10);
            tbOrderItemService.save(item);
        }
    }

    /**
     * hint 强制分片 分布式主键
     */
    @Test
    public void saveHint() throws SQLException {
        String sql = "insert into tb_order (user_id,order_no,order_time,merchant) values (?,?,?,?)";
        try (HintManager hintManager = HintManager.getInstance();
             Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            hintManager.addDatabaseShardingValue("tb_order", 1L);
            hintManager.addTableShardingValue("tb_order", 1L);
            long orderNo = System.currentTimeMillis();
            statement.setInt(1, 11);
            statement.setLong(2, orderNo);
            statement.setTimestamp(3, new Timestamp(new Date().getTime()));
            statement.setString(4, "taobao");
            statement.execute();
        }


    }

    /**
     * 路由
     */
    @Test
    public void findByRoute() {
        QTbOrder qTbOrder = QTbOrder.tbOrder;
        // 带分片键 标准 直接路由(绑定表路由)
        Predicate standard = qTbOrder.item.orderNo.in(1588065451108L, 1588065449595L);

        // 带分片键 笛卡尔路由（非绑定表路由 binding-tables）
        Predicate dikaer = qTbOrder.item.orderNo.in(1588065451108L, 1588065449595L);


        // 不带分片键，全表路由
        Predicate apple0 = qTbOrder.item.name.eq("Apple0");

        List<TbOrder> all = tbOrderService.findAll(standard);
        log.info("findByRoute all:{}", all);
    }

    /**
     * 数据脱敏
     */
    @Test
    public void saveByEncrypt() {
        for (int i = 1; i <= 10; i++) {
            TbUser user = new TbUser();
            user.setUserName("tmy_encrypt_" + i);
            user.setPwd("tmy_pwd_encrypt_" + i);
            tbUserService.save(user);
        }
    }

    @Test
    public void findByCipherColumn() {
        QTbUser tbUser = QTbUser.tbUser;
        // 使用密文列查询 true .false
        Predicate test_encrypt_1 = tbUser.userName.eq("test_encrypt_1");
        List<TbUser> all = tbUserService.findAll(test_encrypt_1);
        log.info("findByCipherColumn all:{}", all);
    }
}