package testjdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/12/1 4:59 下午
 */
public class InsertData {

    public static void main(String[] args) throws SQLException {
        long startTime = System.currentTimeMillis();
        poolJdbc();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);;
    }

    private static void  commonJdbc() throws SQLException {
        System.out.println("正在运行");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String url = "jdbc:mysql://localhost:3306/test";
            final String username = "root";
            final String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            final String sql = "insert into t_order(order_id,user_id,goods_id,"
                    +"order_price,order_discount,order_actualPrice,order_status,"
                    +"order_createTime,order_updateTime)values(?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            final int n = 1000000;
            for(int i = 0;i< n;i++){
                statement.setInt(1,i+1);
                statement.setInt(2,1);
                statement.setInt(3,2);
                statement.setDouble(4,50.0);
                statement.setDouble(5,20.0);
                statement.setDouble(6,30.0);
                statement.setDouble(7,1.0);
                statement.setInt(8,520);
                statement.setInt(9,520);
                statement.addBatch();
            }
            final int[] ints = statement.executeBatch();
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            assert connection != null;
            connection.rollback();
        }finally {
            assert statement != null;
            statement.close();
            connection.close();
            System.out.println("运行完成");
        }
    }

    private static void poolJdbc() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/test";
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
            final String sql = "insert into t_order(order_id,user_id,goods_id,"
                    +"order_price,order_discount,order_actualPrice,order_status,"
                    +"order_createTime,order_updateTime)values(?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            final int n = 1000000;
            for(int i = 0;i< n;i++){
                statement.setInt(1,i+1);
                statement.setInt(2,1);
                statement.setInt(3,2);
                statement.setDouble(4,50.0);
                statement.setDouble(5,20.0);
                statement.setDouble(6,30.0);
                statement.setDouble(7,1.0);
                statement.setInt(8,520);
                statement.setInt(9,520);
                statement.addBatch();
            }
            final int[] ints = statement.executeBatch();
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            assert false;
            statement.close();
            assert connection != null;
            connection.close();
        }
    }
}
