<template>
  <div class="brief">
    <BriefPart
      v-for="item in brief_data"
      :key="item.id"
      :brief="item"
    ></BriefPart>
  </div>
</template>

<script>
import BriefPart from "./brief_part.vue";
import { getVoteList } from "@/api/vote/join/brief.js";
export default {
  name: "brief",
  data() {
    return {
      type: [],
      brief_data: [
        {
          id: "", //投票id
          title: "开始投票", //title
          start: "2022.2.3", //开始时间
          end: "2022.3.2", //截止时间
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
        {
          id: "", //投票id
          title: "开始投票", //title
          start: "2022.2.3", //开始时间
          end: "2022.3.2", //截止时间
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
        {
          id: "", //投票id
          title: "开始投票", //title
          start: "2022.2.3", //开始时间
          end: "2022.3.2", //截止时间
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
      ],
      state: "进行中",
      stateType: ["进行中", "已截止"],
    };
  },
  components: {
    BriefPart,
  },
  methods: {
    //通过比对事件来得到当前投票的状态
    getVoteState() {
      let time = new Date();
    },
  },
  // beforeCreate() {
  //   getVoteList().then((response) => {
  //     this.brief_data = response;
  //   });
  // },
};
</script>

<style scoped>
.brief {
  width: 100%;
}
</style>