/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: DealVoteServiceImpl
 * Author: 22932
 * Date: 2023/10/9 13:19:44
 * Description:
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.vote.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.mapper.DealVoteMapper;
import tyut.selab.vote.service.DealVoteService;
import tyut.selab.vote.tools.TimeDealTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DealVoteServiceImpl
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/9 13:19:44
 */
@Service
public class DealVoteServiceImpl implements DealVoteService {

    @Autowired
    private DealVoteMapper dealVoteMapper;


    @Override
    public Integer launchVote(VoteInfo voteInfo) {
        return null;
    }

    @Override
    public Integer withdrawVote(Long voteId) {
        return null;
    }

    @Override
    public Integer HandlingFrozenVote(Long voteId, Integer handel) {
        return null;
    }

    @Override
    public Integer deleteVote(Long voteId) {
        return null;
    }
}
