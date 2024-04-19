package com;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
@Slf4j
public class MySqlRunner implements ApplicationRunner {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        try(Connection connection = dataSource.getConnection()){
            log.info(String.valueOf(dataSource.getClass()));
            log.info(connection.getMetaData().getURL());
            log.info(connection.getMetaData().getUserName());

            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE USERS (ID INTEGER NOT NULL, name VARCHAR(255),  PRIMARY KEY (ID))";
            stmt.executeUpdate(sql);
        }catch (SQLException e){
            log.info("SQLException 발생 : " + e);
        }

        jdbcTemplate.execute("INSERT INTO USERS VALUES(1, 'yonghwan')");
    }
}
