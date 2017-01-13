package ua.nure.poliakov.dao.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection {

    public static ComboPooledDataSource getPool(){
        
        ComboPooledDataSource pooledDataSource = null;
        try {
            Context context = new InitialContext();
            pooledDataSource = (ComboPooledDataSource) context.lookup("java:/comp/env/jdbc/periodical");
        } catch (NamingException e) {
            System.err.println("Error obtains pool");
        }
        return pooledDataSource;
    }
}