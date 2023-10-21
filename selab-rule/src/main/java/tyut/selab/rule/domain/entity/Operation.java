package tyut.selab.rule.domain.entity;

import lombok.Data;

@Data
public class Operation {
    Long ruleLogId;
    String targetUserId;
    String reasonContent;
    String image;
    String createTime;
    String createUserId;
    String status;
    String updateTime;
    String updateUser;
    Integer scoreChange;
}
