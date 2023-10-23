package tyut.selab.rule.domain.VO;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogVO {
    Integer total;
    List<OperationVO> list;
}
