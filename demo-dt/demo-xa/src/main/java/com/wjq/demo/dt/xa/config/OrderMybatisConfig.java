package com.wjq.demo.dt.xa.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wjq
 * @since 2021-11-17
 */
@Configuration
@MapperScan(basePackages = "com.wjq.demo.dt.xa.mapper.order",sqlSessionTemplateRef = "lyjSessionTemplate")
public class OrderMybatisConfig {

    @Autowired
    private OrderDataConfig orderDataConfig;

    @Bean("orderDataSource")
    public DataSource orderDataSource() throws SQLException {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(orderDataConfig.getUrl());
        druidXADataSource.setPassword(orderDataConfig.getPassword());
        druidXADataSource.setUsername(orderDataConfig.getUsername());
        druidXADataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setUniqueResourceName("orderDataSource");
        xaDataSource.setMinPoolSize(orderDataConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(orderDataConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(orderDataConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(orderDataConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(orderDataConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(orderDataConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(orderDataConfig.getMaxIdleTime());
        xaDataSource.setXaDataSource(druidXADataSource);
        return xaDataSource;
    }

    @Bean(name = "orderSessionFactory")
    public SqlSessionFactory orderSessionFactory(@Qualifier("orderDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resourceResolver.getResources("classpath:mapper/order/*.xml"));
        bean.setTypeAliasesPackage("com.wjq.demo.dt.xa.pojo.*");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "orderSessionTemplate")
    public SqlSessionTemplate orderSessionTemplate(
            @Qualifier("orderSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
