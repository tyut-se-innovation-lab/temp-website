package tyut.selab.schedule.config.jdbc.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.WeekNo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Big_bai on 2022/10/2
 */
public class WeekNoHandler implements TypeHandler<WeekNo> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, WeekNo weekNo, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,weekNo.getId());
    }

    @Override
    public WeekNo getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public WeekNo getResult(ResultSet resultSet, int i) throws SQLException {
        return WeekNo.values()[resultSet.getInt(i)];
    }

    @Override
    public WeekNo getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
