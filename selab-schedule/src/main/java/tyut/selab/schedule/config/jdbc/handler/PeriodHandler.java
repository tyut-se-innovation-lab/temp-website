package tyut.selab.schedule.config.jdbc.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Period;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Big_bai on 2022/9/30
 */
public class PeriodHandler implements TypeHandler<Period> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Period period, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, period.getId());
    }

    @Override
    public Period getResult(ResultSet resultSet, String s) throws SQLException {
        return Period.values()[resultSet.getInt(s)-1];
    }

    @Override
    public Period getResult(ResultSet resultSet, int i) throws SQLException {
        int anInt = resultSet.getInt(i);
        return Period.values()[anInt - 1];
    }

    @Override
    public Period getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
