package io.github.vicen621.blogmensajes.persistance;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SqlDataSource {
    private static MysqlDataSource dataSource = null;

    private SqlDataSource() {}

    public static MysqlDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/mensajes?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        }

        return dataSource;
    }
}
