package com.spsvn.api.domain;

import com.spsvn.api.domain.type.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by npkhanh on 6/8/2018.
 */
@Configuration
public class RoutingDataSourceConfiguration {

    @Bean(name = "clientADatasource")
    @Autowired
    DataSource clientADatasource(
            @Value("${client.a.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.driver-class-name}") String driverClassName) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean(name = "clientBDatasource")
    @Autowired
    DataSource clientBDatasource(
            @Value("${client.b.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.driver-class-name}") String driverClassName) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean
    @Primary
    @Autowired
    public DataSource clientDatasource(
            @Lazy @Qualifier("clientADatasource") DataSource clientADatasource,
            @Lazy @Qualifier("clientBDatasource") DataSource clientBDatasource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(Client.CLIENT_A, clientADatasource);
        targetDataSources.put(Client.CLIENT_B, clientBDatasource);
        ClientDataSourceRouter clientRoutingDatasource = new ClientDataSourceRouter();
        clientRoutingDatasource.setTargetDataSources(targetDataSources);
        clientRoutingDatasource.setDefaultTargetDataSource(clientADatasource);
        return clientRoutingDatasource;
    }
}
