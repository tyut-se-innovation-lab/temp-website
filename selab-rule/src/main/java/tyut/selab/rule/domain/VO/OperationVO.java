package tyut.selab.rule.domain.VO;

import lombok.Data;

@Data
public class OperationVO {
    //日志id
    Long ruleLogId;
    //奖惩原因
    String reasonContent;
    //奖惩时间
    String createTime;
    //奖惩人
    String createUserId;
    //图片路径
    String image;
    //操作的分数
    Integer scoreChange;
    //被操作的用户
    String targetUserId;
}
