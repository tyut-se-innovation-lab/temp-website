package tyut.selab.schedule.mapper.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Big_bai on 2022/10/2
 */
@Component
public class WeekHandler implements TypeHandler<Week> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Week week, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,week.getId());
    }

    @Override
    public Week getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Week getResult(ResultSet resultSet, int i) throws SQLException {
        return Week.values()[resultSet.getInt(i)];
    }

    @Override
    public Week getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
