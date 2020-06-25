springboot整合flowabel modeler入门案例

1. 引入相关依赖
     <!-- https://mvnrepository.com/artifact/org.flowable/flowable-spring-boot-starter-process -->
            <dependency>
                <groupId>org.flowable</groupId>
                <artifactId>flowable-spring-boot-starter-process</artifactId>
                <version>6.4.0</version>
            </dependency>

            <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.1.12</version>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.liquibase</groupId>
                <artifactId>liquibase-core</artifactId>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-devtools</artifactId>
                <scope>runtime</scope>
                <optional>true</optional>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <scope>runtime</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
                <exclusions>
                    <exclusion>
                        <groupId>org.junit.vintage</groupId>
                        <artifactId>junit-vintage-engine</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
2. 配置文件
        # 配置端口号
        server:
          port: 9000

        # 配置数据库连接信息
        spring:
          datasource:
            type: com.alibaba.druid.pool.DruidDataSource
            url: jdbc:mysql://127.0.0.1:3306/flowable?characterEncoding=UTF-8
            username: root
            password: root
3. 编写流程引擎配置类
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

4. 解压flowable代码包 ， 将flowable modeler.war包里面WEB-INF下面的classes下面的static下面的所有的文件拷贝到我们项目中的static文件中

5. 修改script下面的app-cfg.js文件，将'contextRoot' 和webContextRoot' 的值变成""。