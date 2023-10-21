package tyut.selab.rule.service;

import tyut.selab.rule.domain.VO.OperationVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface LogService {

    List<OperationVO> selectAllLog();

    int deleteLog(Long userId,Long logId);
}
