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