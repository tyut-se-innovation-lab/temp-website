<template>
  <div class="brief">
    <HistoryPart
      v-for="(item, index) in historyData"
      :key="index"
      :brief="item"
    ></HistoryPart>
  </div>
</template>

<script>
import History from "@/api/vote/history/index.js";
import HistoryPart from "../brief/part.vue";
export default {
  name: "history",
  data() {
    return {
      history: new History(),
      type: [],
      //数据大况
      historyData: [
        {
          id: "", //投票id
          title: "开始投票", //title
          start: "2022-04-05", //开始时间
          end: "2022-03-01", //截止时间
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
        {
          id: "", //投票id
          title: "开始投票", //title
          start: "2022-01-02", //开始时间
          end: "2022-03-02", //截止时间
          isJoin: true,
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
        {
          id: "", //投票id
          title: "开始投票", //title
          start: "2022-02-03", //开始时间
          end: "2022-02-12", //截止时间
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
      ],
    };
  },
  components: {
    HistoryPart,
  },
  methods: {
    init(data) {
      this.historyData = this.history.initHistory(data);
    },

    getHistoryList() {
      this.history.getHistoryList().then((res) => {
        this.init(res.data);
      });
    },
  },
  created() {
    this.getHistoryList();
  },
};
</script>

<style scoped>
.brief {
  width: 100%;
}
</style>