package com.yixin.js.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.yixin.js.common.config.DruidConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.sql.SQLException;


/**
 * @author
 *
 *配置数据源
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.yixin.js.*.dao",},
                       repositoryImplementationPostfix = "Impl",
                       entityManagerFactoryRef="entityManagerFactory",
                       transactionManagerRef="transactionManager")
public class DatabaseConfiguration{

    private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.yixin.js.*.model";

    @Autowired
    DruidConfigEntity druidConfigEntity;

    @Bean
    public DruidDataSource dataSource(){

        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(druidConfigEntity.getDriverClassName());
        dataSource.setUrl(druidConfigEntity.getUrl());
        dataSource.setUsername(druidConfigEntity.getUsername());
        dataSource.setPassword(druidConfigEntity.getPassword());
        //配置初始化大小、最小、最大
        dataSource.setInitialSize(Integer.valueOf(druidConfigEntity.getInitialSize()));
        dataSource.setMinIdle(Integer.valueOf(druidConfigEntity.getMinIdle()));
        dataSource.setMaxActive(Integer.valueOf(druidConfigEntity.getMaxActive()));
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(Long.valueOf(druidConfigEntity.getMaxWait()));

        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        //打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);


        //配置监控统计拦截的filters
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

        return lef;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return hibernateJpaVendorAdapter;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject() );
        return transactionManager;

        /*HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;*/
    }

}
