package com.ayang.website.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author shy
 * @date 2023/12/5
 * @description DataSourceConfig
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan({"com.ayang.website.**.mapper"})
public class DataSourceConfig {
}
