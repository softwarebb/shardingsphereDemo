package com.example.demo.customalgorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */

public class OrderRangeShardingAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Integer> shardingValue) {
        Range<Integer> rangeValue = shardingValue.getValueRange();
        Range<Integer> open = Range.open(0, 10);
        if (rangeValue.encloses(open)) {
            return Lists.newArrayList("ds0");
        } else {
            return Lists.newArrayList("ds1");
        }
    }
}