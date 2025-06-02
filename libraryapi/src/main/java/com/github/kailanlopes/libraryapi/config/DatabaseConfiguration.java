package com.github.kailanlopes.libraryapi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);
        ds.setDriverClassName(this.driver);
        return ds;
    }

    public DataSourceTransactionManager hikariDataSource(){
        HikariDataSource config = new HikariDataSource();
        config.setUsername(this.username);
        config.setPassword(this.password);
        config.setDriverClassName(this.driver);
        config.setJdbcUrl(this.url);

        config.setMaximumPoolSize(10); //Maximo de conexões liberadas
        config.setMinimumIdle(1); // Tamanho inicial do pool
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); // A conexao demora 10min para morrer
        config.setConnectionTimeout(100000); //Tempo para conseguir uma conexão
        config.setConnectionTestQuery("SELECT 1"); // Query de teste, para ver se realmente esta funcionando a conexão com o banco


        return new DataSourceTransactionManager(config);
    }



}
