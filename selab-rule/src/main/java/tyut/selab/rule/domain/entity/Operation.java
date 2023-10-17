package tyut.selab.rule.domain.entity;

import lombok.Data;

@Data
public class Operation {
    String rule_log_id;
    String target_user_id;
    String reason_content;
    String image;
    String create_Time;
    String create_user_id;
    String status;
    String update_time;
    String update_user;
}
