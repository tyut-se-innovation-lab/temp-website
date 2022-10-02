package tyut.selab.schedule.mapper.handler;

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
@Component
public class PeriodHandler implements TypeHandler<Period> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Period period, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,period.getId());
    }

    @Override
    public Period getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Period getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return Period.getPeriodById(id);
    }

    @Override
    public Period getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return Period.getPeriodById(id);
    }
}
