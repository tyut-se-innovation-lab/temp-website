<script>
import { selectAllLog } from "@/api/rule/content";
export default {
  name: "index",
  data() {
    return {
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      //总条数
      total: 0,
      //用户表格数据
      tableData: null,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //获取所有用户的操作日志
    async getList() {
      await selectAllLog(
        this.queryParams.pageNum,
        this.queryParams.pageSize
      ).then((response) => {
        console.log(response);
        this.tableData = response.data;
        // this.total = response.total;
      });
    },
  },
};
</script>

<template>
  <div class="content">
    <el-card class="left box-card">
      <h1>奖惩内容</h1>
      <h2>前言</h2>
      1.这个规章制度建立在月份分数制度之上，选择向上加分，最终结果由组长根据个人表现酌情加减分（平时分）<br />
      2.组长有10分的平时分 <br />
      3.分数超过30分者，直接踢 <br />
      <hr />
      <h2>学习方面</h2>
      <h3>1.十五小时制度:</h3>
      <P style="text-indent: 2em">
        <li>晚自习无故不来者，一次加10分。</li>
        <br />
        <li>如果多次（两次及以上）未满足15小时，小组长根据情况可以直接踢。</li>
        <br />
        <li>迟到15分钟内：加5分迟到30分钟内：加8分</li>
        <br />
        <li>迟到1小时及以上：按旷工处理迟到时间均不计入15小时早退同迟到</li>
        <br />
        <li>
          因后续按照签到来进行统计时长，遇到网络问题无法正常签到者，及时同组长或管理者反应，并说明自己所到时间，未反应实际情况者，加5分
        </li>
        <br />
        <hr />
      </P>
      <h3>2.学习进度方面：</h3>
      <P style="text-indent: 2em">
        <li>
          由于小组长们定的学习进度是最低底线，未学习完的情况下，可视为该学习周期没有好好学习。
        </li>
        <br />
        <li>第一次：给予警告并加5分。之后：未完成一次加7分。</li>
        <br />
        <li>
          若有小组长考核后发现周报的学习进度与实际不符可根据情况加5~7分，并给予警告。
        </li>
        <br />
        <li>项目考试若发现造假，根据实际情况，最低加10分，最高直接踢</li>
      </P>
      <hr />
      <h3>3. 学校考试：</h3>
      <P style="text-indent: 2em">
        <li>挂满三科及以上的情况下，不考虑分数，直接踢。</li>
      </P>
      <hr />
      <h3>4. 项目规范：</h3>
      <P style="text-indent: 2em">
        <h4>(1).项目负责人：</h4>
        <li>
          未能阶段性（一般是一周）清晰项目的进度包括团队成员的完成度者，加10分
        </li>
        <br />
        <li>摆烂被举报者，管理者考察后酌情加分，5-15分</li>
        <h4>(2). 组长：</h4>
        <li>没有原因而摆烂者，加10分或直接踢</li>
        <br />
        <li>未定期向上汇报进度 - 第一次，予以警告 - 之后，加3分</li>
        <br />
        <li>
          因个人原因（包括统筹项目管理能力、没有合理解决已发生的问题，拒绝交流），导致项目出现严重返工，根据情节严重程度，加10分
        </li>
        <br />
        <h4>(3).组员：</h4>
        <li>
          未有特殊原因且负责的项目进度出现严重滞后，根据情节严重程度，加5-10分
        </li>
        <br />
        <li>
          出现敷衍项目等情况且在组长多次提醒下，仍无动于衷或者在项目期间，无故玩失踪，加20分或直接踢
        </li>
        <br />
        <li>
          对于组长的分工或指令不满意，未向组长或者管理者反应导致后续有矛盾发生，加10分
        </li>
        <br />
      </P>
      <hr />
      <h3>5.日常生活:</h3>
      <P style="text-indent: 2em">
        <li>发现实验室设备故障者，积极上报，预计相应的奖励</li>
        <br />
        <li>借书必须要和管理者知会一声，未说者，加2分</li>
        <br />
        <hr />
      </P>
      <h2>氛围方面</h2>
      <h3>1. 会议相关:</h3>
      <P style="text-indent: 2em">
        <li>完全由组长个人评判</li>
        <br />
        <hr
      /></P>
      <h3>2. 影响他人:</h3>
      <P style="text-indent: 2em">
        <li>
          自习以及上课时间时，不可大声喧哗，若经他人提醒后仍知错不改，加3分。
        </li>
        <br />
        <li>不可打游戏 - 平时发现，加2分</li>
        <br />
        <li>
          被视察的老师发现导致全体成员共同承担责任，加10分。（除团建活动）
        </li>
        <br />
        <li>吃东西味道太大（在a2门口圆桌上可以吃）第一次警告，之后加3分</li>
        <br />
        <hr />
      </P>
      <h3>3. 公物损坏:</h3>
      <P style="text-indent: 2em">
        <li>损坏公物，应原价赔偿并加5分</li>
        <br />
        <hr />
      </P>
      <h3>4. 矛盾冲突:</h3>
      <P style="text-indent: 2em">
        <li>视情况由组长决定</li>
        <br />
        <hr />
      </P>
      <h3>5. 环境方面:</h3>
      <P style="text-indent: 2em">
        <li>乱丢垃圾，加1分</li>
        <br />
        <li>其他视情况而定</li>
        <br />
        <hr />
      </P>
      <h3>6. 值班:</h3>
      <P style="text-indent: 2em">
        <li>未明确原因导致未按时到达值班的，加2分。</li>
        <br />
        <hr />
      </P>
      <h3>7. 社交媒体:</h3>
      <P style="text-indent: 2em">
        <li>发布不实言论诋毁实验室，经查实，直接踢。</li>
        <br />
        <hr />
      </P>
      <h2>管理方面</h2>
      <li>负责人独裁或者出现不良行为，视情况加分或者直接踢</li>
      <br />
      <li>
        负责人玩忽职守或者因个人原因导致实验室重大损失者，加10分，情节严重者，直接踢
      </li>
      <br />
      <h5>包括：</h5>
      <P style="text-indent: 2em">
        <li>项目负责人</li>
        <li>奖惩制度加减分负责人</li>
        <li>小组组长</li>
        <li>管理者</li>
        <li>投票负责人</li>
        <li>其他负责人</li>
        <br />
        <hr />
      </P>
      <h2>奖励规则</h2>
      <li>
        前言：奖励金额的额度应该根据实验室每年的情况进行相应调整，不能为了奖励而耗光所有实验室资金，导致实验室无法正常运转。
      </li>
      <br />
      <li>2023至2024学年，实验室奖金额度为 RMB and 项目的机会。</li>
      <br />
      <P style="text-indent: 2em">
        <li>
          1.言论自由，积极提建议者，根据提议的可建设性程度，进行相应的奖励
        </li>
        <br />
        <li>
          2.根据奖惩制度，举报者（提倡匿名），查实之后，根据其贡献程度，进行相应的奖励
        </li>
        <br />
        <li>
          3. 对实验室有重大创新、重大贡献者，实验室应该视情况，给予其相应奖励。
        </li>
        <br />
        <li>4. 早来开门和晚上最后走的人，进行相应的奖励</li>
        <br />
        <hr />
      </P>
      <h2>最后，每个人都提到了一点: 最终解释权归实验室开发所有</h2>
    </el-card>
    <el-card class="box-card right">
      <h1>公告栏</h1>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column
          prop="createTime"
          key="createTime"
          label="日期"
          width="100"
        >
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="70"> </el-table-column>
        <el-table-column prop="reasonContent" key="reasonContent" label="原因">
        </el-table-column>
      </el-table>
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-card>
  </div>
</template>

<style scoped>
.content {
  width: 100%;
  height: 100%;
  display: flex;
}

.left {
  width: 75%;
}

.right {
  width: 35%;
}
</style>
