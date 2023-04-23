<!--<<<<<<< HEAD-->
<!--<template>-->
<!--  <div class="brief">-->
<!--    <Switcher-->
<!--      :builder="switcher"-->
<!--      @sortbriefDataByJoined="sortbriefDataByJoined"-->
<!--      @filterJoined="filterJoined"-->
<!--      @filterUnjoined="filterUnjoined"-->
<!--    ></Switcher>-->
<!--    <BriefPart-->
<!--      v-for="(item, index) in tmpData"-->
<!--      :key="index"-->
<!--      :brief="item"-->
<!--    ></BriefPart>-->
<!--    <router-view />-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import Join from "@/api/vote/join/index.js";-->
<!--import Switcher from "./switch.vue";-->
<!--import BriefPart from "./part.vue";-->
<!--export default {-->
<!--  name: "brief",-->
<!--  data() {-->
<!--    return {-->
<!--      type: [],-->
<!--      join: new Join(),-->
<!--      tmpData: [],-->
<!--      briefData: [-->
<!--        {-->
<!--          id: "1", //投票id-->
<!--          title: "开始投票", //title-->
<!--          state: 0,-->
<!--          createdTime: "Tue Nov 15 09:52:46 CST 2022", //开始时间-->
<!--          deadline: "Tue Nov 15 09:52:46 CST 2022", //截止时间-->
<!--          isjoin: true,-->
<!--          content:-->
<!--            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介-->
<!--        },-->
<!--        {-->
<!--          id: "2", //投票id-->
<!--          title: "开始投票", //title-->
<!--          state: 1,-->
<!--          createdTime: "2022.2.3", //开始时间-->
<!--          deadline: "2022.3.24", //截止时间-->
<!--          isjoin: true,-->
<!--          content:-->
<!--            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介-->
<!--        },-->
<!--        {-->
<!--          id: "3", //投票id-->
<!--          title: "开始投票", //title-->
<!--          state: 2,-->
<!--          createdTime: "2022.2.3", //开始时间-->
<!--          deadline: "2022.3.2", //截止时间-->
<!--          isjoin: false,-->
<!--          content:-->
<!--            "什么，额流量费拉拉肥就覅房价反免费咖啡1积分卡积分卡看风景放假啊较为开放假按揭开发放假啊开发酒啊就放假啊积分卡咖啡机卡卡放假啊开发金卡金卡放假咯放假啊开发框架放假咯我发卡机开发接口啦啦啦", //简介-->
<!--        },-->
<!--      ],-->
<!--      switcher: [-->
<!--        { text: "全部", methods: "sortbriefDataByJoined" },-->
<!--        { text: "已参与", methods: "filterJoined" },-->
<!--        { text: "未参与", methods: "filterUnjoined" },-->
<!--      ],-->
<!--    };-->
<!--  },-->
<!--  components: {-->
<!--    BriefPart,-->
<!--    Switcher,-->
<!--  },-->
<!--  methods: {-->
<!--    sortbriefDataByJoined() {-->
<!--      this.sortbriefDataByDate();-->
<!--      let arr1 = this.filterJoined();-->
<!--      let arr2 = this.filterUnjoined();-->
<!--      this.tmpData = arr1.concat(arr2);-->
<!--    },-->
<!--    sortbriefDataByDate() {-->
<!--      this.briefData.sort((a, b) => {-->
<!--        let date1 = new Date(a.start);-->
<!--        let date2 = new Date(b.start);-->
<!--        return date1 - date2;-->
<!--      });-->
<!--      this.tmpData = this.briefData;-->
<!--    },-->
<!--    filterJoined() {-->
<!--      this.tmpData = this.briefData.filter((item) => {-->
<!--        return item.isjoin === true;-->
<!--      });-->
<!--      return this.tmpData;-->
<!--    },-->
<!--    filterUnjoined() {-->
<!--      this.tmpData = this.briefData.filter((item) => {-->
<!--        return item.isjoin === false;-->
<!--      });-->
<!--      return this.tmpData;-->
<!--    },-->
<!--    getBriefList() {-->
<!--      this.join.getBriefList().then((res) => {-->
<!--        console.log(res);-->
<!--      });-->
<!--    },-->
<!--  },-->
<!--  mounted() {-->
<!--    this.sortbriefDataByJoined();-->
<!--  },-->
<!--  created() {-->
<!--    this.getBriefList();-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.brief {-->
<!--  width: 100%;-->
<!--}-->
<!--=======-->
<template>
  <div class="brief">
    <Switcher
      :builder="switcher"
      @allData="allData"
      @filterJoined="filterJoined"
      @filterUnjoined="filterUnjoined"
    ></Switcher>
    <BriefPart
      v-for="(item, index) in tmpData"
      :key="index"
      :brief="item"
    ></BriefPart>
    <router-view />
  </div>
</template>

<script>
import Join from "@/api/vote/join/index.js";
import Switcher from "./switch.vue";
import BriefPart from "./part.vue";
export default {
  name: "brief",
  data() {
    return {
      type: [],
      join: new Join(),
      tmpData: [],
      briefData: [],
      switcher: [
        { text: "全部", methods: "allData" },
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
    init(data) {
      this.briefData = this.join.initJoin(data);
      this.tmpData = this.briefData;
    },

    allData() {
      this.tmpData = this.briefData;
    },

    filterJoined() {
      this.tmpData = this.join.filterJoined(this.briefData);
    },

    filterUnjoined() {
      this.tmpData = this.join.filterUnjoined(this.briefData);
    },

    getBriefList() {
      this.join.getBriefList().then((res) => {
        this.init(res.data);
      });
    },
  },
  created() {
    this.getBriefList();
  },
};
</script>

<style scoped>
.brief {
  width: 100%;
}

</style>
