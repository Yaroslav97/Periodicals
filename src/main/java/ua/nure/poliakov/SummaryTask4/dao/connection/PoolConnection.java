package ua.nure.poliakov.SummaryTask4.dao.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.naming.*;

public class PoolConnection {

    public static ComboPooledDataSource getPool() {
        ComboPooledDataSource dataSource = null;
        try {
            Context context = new InitialContext();
            dataSource = (ComboPooledDataSource) context.lookup("java:/comp/env/jdbc/periodical");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
