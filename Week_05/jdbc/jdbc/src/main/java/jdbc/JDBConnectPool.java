package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/18 10:27 上午
 */
public class JDBConnectPool {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/mybatis";
        final String username = "root";
        final String password = "123456";

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource dataSource = new HikariDataSource(config);

        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = dataSource.getConnection();
            //关闭自动提交
            connection.setAutoCommit(false);
            final String insertSql = "insert into jdbc(id,username) values (?,?),(?,?)";
            statement = connection.prepareStatement(insertSql);
            statement.setInt(1, 7);
            statement.setString(2, "yyx");
            statement.setInt(3,8);
            statement.setString(4,"rt");
            statement.execute();
            connection.commit();
            final String selectSql = "select * from jdbc";
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("username"));
            }
        }catch (Exception e){
            assert connection != null;
            connection.rollback();
            e.printStackTrace();
        }finally {
            assert connection != null;
            connection.close();
        }
    }
}
