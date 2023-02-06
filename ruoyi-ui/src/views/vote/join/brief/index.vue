<template>
  <div class="brief">
    <Switcher
      :builder="switcher"
      @sortbriefDataByJoined="sortbriefDataByJoined"
      @filterJoined="filterJoined"
      @filterUnjoined="filterUnjoined"
    ></Switcher>
    <BriefPart
      v-for="(item, index) in tmpData"
      :key="index"
      :brief="item"
    ></BriefPart>
  </div>
</template>

<script>
import Switcher from "./switch.vue";
import BriefPart from "./part.vue";
export default {
  name: "brief",
  data() {
    return {
      type: [],
      tmpData: [],
      briefData: [
        {
          id: "", //投票id
          title: "开始投票", //title
          state: 0,
          start: "Tue Nov 15 09:52:46 CST 2022", //开始时间
          end: "Tue Nov 15 09:52:46 CST 2022", //截止时间
          isjoin: true,
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
        {
          id: "", //投票id
          title: "开始投票", //title
          state: 1,
          start: "2022.2.3", //开始时间
          end: "2022.3.2", //截止时间
          isjoin: true,
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
        {
          id: "", //投票id
          title: "开始投票", //title
          state: 2,
          start: "2022.2.3", //开始时间
          end: "2022.3.2", //截止时间
          isjoin: false,
          content:
            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介
        },
      ],
      switcher: [
        { text: "全部", methods: "sortbriefDataByJoined" },
        { text: "已参与", methods: "filterJoined" },
        { text: "未参与", methods: "filterUnjoined" },
      ],
    };
  },
  components: {
    BriefPart,
    Switcher,
  },
  methods: {
    sortbriefDataByJoined() {
      this.sortbriefDataByDate();
      let arr1 = this.filterJoined();
      let arr2 = this.filterUnjoined();
      this.tmpData = arr1.concat(arr2);
    },
    sortbriefDataByDate() {
      this.briefData.sort((a, b) => {
        let date1 = new Date(a.start);
        let date2 = new Date(b.start);
        return date1 - date2;
      });
      this.tmpData = this.briefData;
    },
    filterJoined() {
      this.tmpData = this.briefData.filter((item) => {
        return item.isjoin === true;
      });
      return this.tmpData;
    },
    filterUnjoined() {
      this.tmpData = this.briefData.filter((item) => {
        return item.isjoin === false;
      });
      return this.tmpData;
    },
  },
  mounted() {
    this.sortbriefDataByJoined();
  },
};
</script>

<style scoped>
.brief {
  width: 100%;
}
</style>