package tyut.selab.rule.domain.VO;

import lombok.Data;

@Data
public class OperationVO {
    //奖惩原因
    String reason;
    //奖惩时间
    String time;
    //被执行者
    String user;
    //图片路径
    String image;
}
