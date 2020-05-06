package com.example.demo.customalgorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */

public class OrderNoShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<Long> shardingValue) {
        int index = shardingValue.getValue() % 2 == 0 ? 0 : 1;
        String targetDataBase = "ds" + index;
        if (availableTargetNames.contains(targetDataBase)){
            return targetDataBase;
        }

        throw new UnsupportedOperationException("无法判定的值: " + shardingValue.getValue());
    }
}