/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteOptionTypeHandler
 * Author: 22932
 * Date: 2023/10/11 19:03:31
 * Description:
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.vote.config.jdbc.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import tyut.selab.vote.enums.VoteOptionType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: VoteOptionTypeHandler
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/11 19:03:31
 */
public class VoteOptionTypeHandler implements TypeHandler<VoteOptionType> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, VoteOptionType voteOptionType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,voteOptionType.getSign());
    }

    @Override
    public VoteOptionType getResult(ResultSet resultSet, String s) throws SQLException {
        return VoteOptionType.values()[resultSet.getInt(s)];
    }

    @Override
    public VoteOptionType getResult(ResultSet resultSet, int i) throws SQLException {
        return VoteOptionType.values()[resultSet.getInt(i)];
    }

    @Override
    public VoteOptionType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}

