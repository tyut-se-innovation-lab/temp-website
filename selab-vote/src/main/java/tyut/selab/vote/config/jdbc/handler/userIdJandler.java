package tyut.selab.vote.config.jdbc.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import tyut.selab.vote.enums.User;
import tyut.selab.vote.tools.AnonymousControl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userIdJandler extends BaseTypeHandler<User>{

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, User user, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public User getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String decrypt = AnonymousControl.decrypt(resultSet.getString(s));
        return new User(decrypt);
    }

    @Override
    public User getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public User getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
