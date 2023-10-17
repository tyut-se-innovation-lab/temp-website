package tyut.selab.vote.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.VoteRangeVo;

import java.util.ArrayList;
import java.util.Map;

/**
 * @className: QueryVoteController
 * @author: lizhichao
 * @description: TODO  查询投票
 * @date: 2023/10/7 18:21
 * @version: 1.0
 */
@RequestMapping("/vote/query")
@RestController
public class QueryVoteController extends BaseController {


     /**
      *   查询我举报的投票
      * @return
      */
     @PostMapping("/myreport")
     public AjaxResult queryForMyReport(){
          return AjaxResult.success(new ArrayList<VoteRangeVo>());
     }

     /**
      *  查询我发起的投票
      * @return
      */
     @PostMapping("/mylaunch")
     public AjaxResult queryForMyHold(){
          return  AjaxResult.success(new ArrayList<VoteRangeVo>());
     }

     /**
      *   查看冻结投票
      * @return
      */
     @PostMapping("/untreated")
     public AjaxResult queryForUntreated(){
          return AjaxResult.success(new ArrayList<VoteRangeVo>());
     }


     /**
      *   查询 是否结束 是否参与 投票
      * @param params  isEnd: , isParticipate:   为空则查询全部
      * @return
      */
     public AjaxResult queryForHistory(Map<String,Integer> params){
          return AjaxResult.success(new ArrayList<VoteRangeVo>());
     }

     /**
      * 通过ID查询投票
      * @param voteId
      * @return
      */
     @GetMapping("/{voteId}")
     public AjaxResult queryById(@PathVariable("voteId") Long voteId){
          return AjaxResult.success(new VoteInfo());
     }
     }

