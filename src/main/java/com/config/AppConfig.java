package com.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;


@Configuration
@MapperScan("com.dao")
public class AppConfig  {


    @Bean
    public PooledDataSource dataSource(){
        PooledDataSource dataSource=new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.0.101:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong");
        dataSource.setUsername("root");
        dataSource.setPassword("fuduhui123");
        dataSource.setPoolMaximumActiveConnections(5);
        dataSource.setPoolMaximumIdleConnections(3);
        dataSource.setDefaultAutoCommit(false);
        return dataSource;

    }




    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sessionFactoryBean(){
        try{
            SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource());
            Resource[] mapperLocations=new PathMatchingResourcePatternResolver().getResources("classpath:mapper/testdb/*.xml");
            sessionFactoryBean.setMapperLocations(mapperLocations);
            return sessionFactoryBean;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;

    }


}