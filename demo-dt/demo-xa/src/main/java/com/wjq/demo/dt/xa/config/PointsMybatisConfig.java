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
@MapperScan(basePackages = "com.wjq.demo.dt.xa.mapper.points", sqlSessionTemplateRef = "pointsSessionTemplate")
public class PointsMybatisConfig {

    @Autowired
    private PointsDataConfig pointsDataConfig;

    @Bean("pointsDataSource")
    public DataSource templateDataSource() throws SQLException {
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(pointsDataConfig.getUrl());
        druidXADataSource.setPassword(pointsDataConfig.getPassword());
        druidXADataSource.setUsername(pointsDataConfig.getUsername());
        druidXADataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setUniqueResourceName("pointsDataSource");
        xaDataSource.setMinPoolSize(pointsDataConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(pointsDataConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(pointsDataConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(pointsDataConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(pointsDataConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(pointsDataConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(pointsDataConfig.getMaxIdleTime());
        xaDataSource.setXaDataSource(druidXADataSource);
        return xaDataSource;
    }

    @Bean(name = "pointsSimpleSessionFactory")
    public SqlSessionFactory templateSimpleSessionFactory(@Qualifier("pointsDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resourceResolver.getResources("classpath:mapper/points/*.xml"));
        bean.setTypeAliasesPackage("com.wjq.demo.dt.xa.pojo.*");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "pointsSessionTemplate")
    public SqlSessionTemplate pointsSessionTemplate(
            @Qualifier("pointsSimpleSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
