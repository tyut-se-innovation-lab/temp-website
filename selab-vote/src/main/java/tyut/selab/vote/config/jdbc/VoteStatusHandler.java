package tyut.selab.vote.config.jdbc;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import tyut.selab.vote.enums.VoteStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = VoteStatus.class)
public class VoteStatusHandler implements TypeHandler<VoteStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, VoteStatus voteStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, voteStatus.getId());
    }

    @Override
    public VoteStatus getResult(ResultSet resultSet, String s) throws SQLException {

        return VoteStatus.values()[resultSet.getInt(s)-1];
    }

    @Override
    public VoteStatus getResult(ResultSet resultSet, int i) throws SQLException {
        return VoteStatus.values()[resultSet.getInt(i)-1];
    }

    @Override
    public VoteStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
