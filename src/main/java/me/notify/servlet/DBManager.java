package me.notify.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by janne on 18.11.2015.
 */
public class DBManager {

    public static Connection getConnection() {
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/notifyme");
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
