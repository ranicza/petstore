package io.swagger.configuration;

import com.azure.security.keyvault.secrets.SecretClient;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    @Value("${PETSTOREPETSERVICE_DB_URL}")
    private String dbUrlSecret;

    @Value("${PETSTOREPETSERVICE_DB_USERNAME}")
    private String dbUserNameSecret;

    @Value("${PETSTOREPETSERVICE_DB_PASSWORD}")
    private String dbPasswordSecret;

    private final SecretClient secretClient;

    @Bean
    public DataSource dataSource() {
        var dbUrl = getDbSecret(dbUrlSecret);
        var username = getDbSecret(dbUserNameSecret);
        var password = getDbSecret(dbPasswordSecret);

        var hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setJdbcUrl(dbUrl);
        return new HikariDataSource(hikariConfig);
    }

    // Parse the full secret url and retrieve secret name and version to access the secret value itself.
    private String getDbSecret(String secretUrl) {
        String[] segments = secretUrl.split("/");

        // Extract the secret name and version
        var secretName = segments[4];
        var secretVersion = segments[5];
        return secretClient.getSecret(secretName, secretVersion).getValue();
    }
}
