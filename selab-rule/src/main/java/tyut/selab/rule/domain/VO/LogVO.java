package tyut.selab.rule.domain.VO;

import lombok.Data;

import java.util.List;

@Data
public class LogVO {
    Long total;
    List<OperationVO> list;
}
