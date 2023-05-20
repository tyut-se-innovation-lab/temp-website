package tyut.selab.vote.service;

import tyut.selab.vote.domain.vo.Questionnaire;

/**
 * @author Big-Bai
 **/
public interface ICommitVoteService {

    /**
     * 记录用户选择的选项
     * @param questionnaire 用户填写的问卷
     * @return 是否记录成功
     */
    Boolean recordSelection(Questionnaire questionnaire,Long user);
}
