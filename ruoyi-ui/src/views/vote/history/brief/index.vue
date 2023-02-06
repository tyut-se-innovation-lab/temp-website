<template>
  <div class="brief">
    <HistoryPart
      v-for="item in historyData"
      :key="item.id"
      :brief="item"
    ></HistoryPart>
  </div>
</template>

<script>
import HistoryPart from "../brief/part.vue";
export default {
  name: "history",
  data() {
    return {
      type: [],
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
    sortbriefDataByDate() {
      this.historyData.sort((a, b) => {
        let date1 = new Date(a.start);
        let date2 = new Date(b.start);
        console.log(a, b);
        return date1 - date2;
      });
    },
  },
  created() {
    this.sortbriefDataByDate();
  },
};
</script>

<style scoped>
.brief {
  width: 100%;
}
</style>