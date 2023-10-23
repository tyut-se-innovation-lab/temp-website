package tyut.selab.rule.service;

import tyut.selab.rule.domain.VO.LogVO;
import tyut.selab.rule.domain.VO.OperationVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface LogService {

    LogVO selectAllLog(Integer pageNum, Integer pageSize);

    int deleteLog(Long userId,Long logId);

    String selectUserById(Long userId);
}
