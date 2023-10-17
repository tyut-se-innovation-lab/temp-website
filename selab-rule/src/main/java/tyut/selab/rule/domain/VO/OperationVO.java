package tyut.selab.rule.domain.VO;

import lombok.Data;

@Data
public class OperationVO {
    //奖惩原因
    String reason_content;
    //奖惩时间
    String create_time;
    //被执行者
    String target_user_id;
    //图片路径
    String image;
}
