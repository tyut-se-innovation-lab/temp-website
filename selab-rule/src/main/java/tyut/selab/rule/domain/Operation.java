package tyut.selab.rule.domain;

import lombok.Data;

@Data
public class Operation {
    Long ruleLogId;
    Long targetUserId;
    String reasonContent;
    String image;
    String createTime;
    String createUserId;
    String status;
    String updateTime;
    String updateUser;
    Integer scoreChange;
}
