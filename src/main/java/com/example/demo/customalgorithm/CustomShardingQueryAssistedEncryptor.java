package com.example.demo.customalgorithm;

import org.apache.shardingsphere.spi.encrypt.ShardingQueryAssistedEncryptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */

public class CustomShardingQueryAssistedEncryptor implements  ShardingQueryAssistedEncryptor {
    private Properties properties = new Properties();

    @Override
    public String queryAssistedEncrypt(String plaintext) {
        return "assistedEncryptValue";
    }

    @Override
    public void init() {

    }

    @Override
    public String encrypt(Object plaintext) {
        return "encryptValue"+System.currentTimeMillis();
    }

    @Override
    public Object decrypt(String ciphertext) {
        return "decryptValue";
    }

    @Override
    public String getType() {
        return "assisted";
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}