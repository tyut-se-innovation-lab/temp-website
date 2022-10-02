package tyut.selab.schedule.mapper.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;
import tyut.selab.schedule.enums.Status;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Big_bai on 2022/10/2
 */
@Component
public class StatusHandler implements TypeHandler<Status> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Status status, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,status.getId());
    }

    @Override
    public Status getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Status getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return Status.getStatusById(id);
    }

    @Override
    public Status getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return Status.getStatusById(id);
    }
}
