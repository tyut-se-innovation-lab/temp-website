/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: VoteTypeHandler
 * Author: 22932
 * Date: 2023/10/11 19:06:58
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
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.enums.VoteType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: VoteTypeHandler
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/11 19:06:58
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(VoteType.class)
public class VoteTypeHandler implements TypeHandler<VoteType> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, VoteType voteType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,voteType.getId());
    }

    @Override
    public VoteType getResult(ResultSet resultSet, String s) throws SQLException {
        return VoteType.values()[resultSet.getInt(s)-1];
    }

    @Override
    public VoteType getResult(ResultSet resultSet, int i) throws SQLException {
        return VoteType.values()[resultSet.getInt(i)-1];
    }

    @Override
    public VoteType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}

