package tyut.selab.vote.config.jdbc.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import tyut.selab.vote.enums.VoteReminderType;
import tyut.selab.vote.enums.VoteStatus;

import java.sql.*;

/**
 * @className: VoteReminderTypeHandler
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/13 21:44
 * @version: 1.0
 */
@MappedJdbcTypes(JdbcType.CHAR)
@MappedTypes(VoteReminderType.class)
public class VoteReminderTypeHandler implements TypeHandler<VoteReminderType> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, VoteReminderType VoteReminderType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,VoteReminderType.getSign());
    }

    @Override
    public VoteReminderType getResult(ResultSet resultSet, String s) throws SQLException {
        return VoteReminderType.values()[resultSet.getInt(s)];
    }

    @Override
    public VoteReminderType getResult(ResultSet resultSet, int i) throws SQLException {
        return VoteReminderType.values()[resultSet.getInt(i)];
    }

    @Override
    public VoteReminderType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
