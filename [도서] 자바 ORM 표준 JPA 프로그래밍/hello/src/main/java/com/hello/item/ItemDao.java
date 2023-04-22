package com.hello.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDao {

    private final DataSource dataSource;

    public ItemDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Item item, String dtype) throws SQLException {
        String sql = "INSERT INTO item(itemId, name, price, dtype) VALUES(?, ?, ?, ?)";
        Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, item.getId());
        stmt.setString(2, item.getName());
        stmt.setInt(3, item.getPrice());
        stmt.setString(4, dtype);
        stmt.executeUpdate();

        sql = "INSERT INTO album(itemId, artist) VALUES(?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setLong(1, item.getId());
    }
}
