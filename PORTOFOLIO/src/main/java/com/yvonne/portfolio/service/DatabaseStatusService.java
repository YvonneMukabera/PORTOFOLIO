package com.yvonne.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;

@Service
public class DatabaseStatusService {

    private final Environment environment;
    private final String serverUrl;
    private final String databaseName;
    private final String username;
    private final String password;

    public DatabaseStatusService(
            Environment environment,
            @Value("${portfolio.mysql.server-url:}") String serverUrl,
            @Value("${portfolio.mysql.database:}") String databaseName,
            @Value("${portfolio.mysql.username:}") String username,
            @Value("${portfolio.mysql.password:}") String password
    ) {
        this.environment = environment;
        this.serverUrl = serverUrl;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    public Map<String, Object> status() {
        boolean xamppProfileActive = Arrays.asList(environment.getActiveProfiles()).contains("xampp");
        if (!xamppProfileActive) {
            return Map.of(
                    "connected", false,
                    "profile", "default",
                    "message", "Database is not active. Run with the xampp profile to use XAMPP MySQL.",
                    "persistedTables", "contact_messages only"
            );
        }

        try (Connection connection = DriverManager.getConnection(serverUrl + databaseName, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM contact_messages")) {
            resultSet.next();
            return Map.of(
                    "connected", true,
                    "profile", "xampp",
                    "database", databaseName,
                    "serverUrl", serverUrl,
                    "contactMessageCount", resultSet.getInt(1),
                    "persistedTables", "contact_messages only"
            );
        } catch (Exception exception) {
            return Map.of(
                    "connected", false,
                    "profile", "xampp",
                    "database", databaseName,
                    "serverUrl", serverUrl,
                    "message", exception.getMessage(),
                    "persistedTables", "contact_messages only"
            );
        }
    }
}
