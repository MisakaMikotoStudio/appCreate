package com.misaka.yuban.dao;

import com.misaka.yuban.common.constant.Leokeys;
import com.misaka.yuban.common.util.LeoUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource testDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(LeoUtils.getStringProperty(Leokeys.DATASOURCE_DRIVER));
        dataSourceBuilder.url(LeoUtils.getStringProperty(Leokeys.DATASOURCE_URL));
        dataSourceBuilder.username(LeoUtils.getStringProperty(Leokeys.DATASOURCE_USERNAME));
        dataSourceBuilder.password(LeoUtils.getStringProperty(Leokeys.DATASOURCE_PASSWORD));
        return dataSourceBuilder.build();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        sqlSessionFactoryBean.setConfigurationProperties(properties);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis/config.xml"));
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.misaka.yuban.dao.mapper");
        configurer.setAnnotationClass(org.springframework.stereotype.Component.class);
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return configurer;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}