package tyut.selab.vote.config.jdbc;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import tyut.selab.vote.enums.VoteOptionType;
import tyut.selab.vote.enums.VoteStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @className: VoteTypeHandler
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 11:12
 * @version: 1.0
 */


@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = VoteOptionType.class)
public class VoteOptionTypeHandler implements TypeHandler<VoteOptionType> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, VoteOptionType voteOptionType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, voteOptionType.getSign());
    }

    @Override
    public VoteOptionType getResult(ResultSet resultSet, String s) throws SQLException {

        return VoteOptionType.values()[resultSet.getInt(s)-1];
    }

    @Override
    public VoteOptionType getResult(ResultSet resultSet, int i) throws SQLException {
        return VoteOptionType.values()[resultSet.getInt(i)-1];
    }

    @Override
    public VoteOptionType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
