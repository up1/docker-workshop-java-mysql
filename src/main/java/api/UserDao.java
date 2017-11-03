package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public User getUserBy(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            String sql = "SELECT id, name FROM USER WHERE id=?";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection =
                    DriverManager.getConnection(System.getenv("DATABASE_URL"));
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Data not found");
    }
}
