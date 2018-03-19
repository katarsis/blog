package com.kapetingi.blog;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "blogEntityManager", basePackages = {"com.kapetingi.blog.entities"})
public class PersistenceConfiguration {

    @Primary
    @Bean(destroyMethod = "close", name = "blogDatasource")
    public HikariDataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgres://gmuoygokkyqcvl:4198cb76c261f98d29f448aabca00b6fcb29f6466c0b32fc5897b789dfb11b35@ec2-54-247-111-19.eu-west-1.compute.amazonaws.com:5432/ddc6eem9uqs3sc");
        config.setDriverClassName("org.postgresql.Driver");
        config.setAutoCommit(true);

        return new HikariDataSource(config);
    }

    @Primary
    @Bean (name = "pgateEntityManager")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("blogDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.kapetingi.blog.entities");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        //jpaProperties.put("hibernate.show_sql","true");


        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }
}
