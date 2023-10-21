package tyut.selab.rule.service;

import tyut.selab.rule.domain.VO.OperationVO;

import java.util.List;

public interface LogService {

    int deleteLog(Long logId);

    List<OperationVO> selectAllLog();
}
