package tyut.selab.rule.domain.VO;

import lombok.Data;

@Data
public class OperationVO {
    //奖惩原因
    String reasonContent;
    //奖惩时间
    String createTime;
    //被执行者
    String createUserId;
    //图片路径
    String image;
}
