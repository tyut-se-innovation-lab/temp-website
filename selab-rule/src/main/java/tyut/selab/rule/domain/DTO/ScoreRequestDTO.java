package tyut.selab.rule.domain.DTO;

import lombok.Data;

@Data
public class ScoreRequestDTO {
    Long userId;
    Integer scoreChange;
}
