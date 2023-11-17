package tyut.selab.rule.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScoreRequestDTO {
    Long MenderId;//操作人
    Long userId;//被操作人
    Integer scoreChange;
    String image;
    String reason;
    LocalDateTime createTime;
}
