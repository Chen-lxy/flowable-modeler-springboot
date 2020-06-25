package com.chen.study.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//流程引擎配置类
@Configuration
public class ProcessEngineConfig {

    //引入数据源
    @Autowired
    private DataSource dataSource;

    // springProcessEngineConfiguration的启动需要用到事务管理类
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(){
        SpringProcessEngineConfiguration springProcessEngineConfiguration =
                new SpringProcessEngineConfiguration();
        springProcessEngineConfiguration.setDataSource(dataSource); // 设置数据源
        springProcessEngineConfiguration.setDatabaseSchemaUpdate("true");
        springProcessEngineConfiguration.setTransactionManager(dataSourceTransactionManager(dataSource)); // 设置事务管理类
        return springProcessEngineConfiguration;
    }

}
