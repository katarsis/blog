package com.kapetingi.blog;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "blogEntityManager", basePackages = {"com.kapetingi.blog.entities","com.kapetingi.blog.repositories"})
public class PersistenceConfiguration {

    @Value("${com.kapetingi.blog.postgre.url}")
    private String jdbcUrl;

    @Value("${com.kapetingi.blog.postgre.password}")
    private String password;

    @Value("${com.kapetingi.blog.postgre.user}")
    private String user;

    @Primary
    @Bean(destroyMethod = "close", name = "blogDatasource")
    public HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setPassword(password);
        config.setUsername(user);

        config.setDriverClassName("org.postgresql.Driver");
        config.setAutoCommit(true);

        return new HikariDataSource(config);
    }

    @Primary
    @Bean (name = "blogEntityManager")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("blogDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.kapetingi.blog.entities");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        //jpaProperties.put("hibernate.show_sql","true");


        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }
}
