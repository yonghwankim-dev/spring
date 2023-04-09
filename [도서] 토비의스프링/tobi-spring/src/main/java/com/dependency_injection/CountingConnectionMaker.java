package com.dependency_injection;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker {

    private int counter;
    private final ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.counter = 0;
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeConnection() throws SQLException, ClassNotFoundException {
        this.counter++;
        return realConnectionMaker.makeConnection();
    }

    public int getCounter() {
        return this.counter;
    }
}
