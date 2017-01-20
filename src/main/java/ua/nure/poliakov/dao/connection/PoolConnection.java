package ua.nure.poliakov.dao.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.naming.*;

public class PoolConnection {

    public static ComboPooledDataSource getPool(){
        
        ComboPooledDataSource pooledDataSource = null;
        try {
            Context context = new InitialContext();
            pooledDataSource = (ComboPooledDataSource) context.lookup("java:/comp/env/jdbc/periodical");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return pooledDataSource;
    }
}