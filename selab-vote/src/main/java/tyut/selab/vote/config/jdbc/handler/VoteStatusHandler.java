/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteStatusTypeHandler
 * Author: 22932
 * Date: 2023/10/11 19:05:20
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
import tyut.selab.vote.enums.VoteStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: VoteStatusTypeHandler
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/11 19:05:20
 */
public class VoteStatusHandler implements TypeHandler<VoteStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, VoteStatus voteStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,voteStatus.getId());
    }

    @Override
    public VoteStatus getResult(ResultSet resultSet, String s) throws SQLException {
        return VoteStatus.values()[resultSet.getInt(s)];
    }

    @Override
    public VoteStatus getResult(ResultSet resultSet, int i) throws SQLException {
        return VoteStatus.values()[resultSet.getInt(i)];
    }

    @Override
    public VoteStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}

