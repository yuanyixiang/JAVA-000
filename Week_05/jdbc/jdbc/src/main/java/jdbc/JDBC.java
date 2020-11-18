package jdbc;

import java.sql.*;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/18 9:57 上午
 */
public class JDBC {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String url = "jdbc:mysql://localhost:3306/mybatis";
            final String username = "root";
            final String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

            final String insertSql = "insert into jdbc(id,username) values (?,?),(?,?)";
            statement = connection.prepareStatement(insertSql);
            statement.setInt(1, 5);
            statement.setString(2, "yyx");
            statement.setInt(3,6);
            statement.setString(4,"rt");
            statement.execute();
            connection.commit();

            final String selectSql = "select * from jdbc";
            ResultSet resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("username"));
            }
        } catch (Exception e) {
            assert connection != null;
            connection.rollback();
            e.printStackTrace();
        }finally {
            assert statement != null;
            statement.close();
        }
    }
}
