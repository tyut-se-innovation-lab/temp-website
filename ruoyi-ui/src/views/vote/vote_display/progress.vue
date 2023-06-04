<template>
  <div class="progress">
    <p>{{ this.questionText }}</p>
    <el-progress
      v-for="(item, index) in data.options"
      :text-color="textColor"
      :text-inside="true"
      :key="item.id"
      :stroke-width="24"
      :percentage="forPercentage(item.percentage)"
      :format="format(index)"
      :color="color(index)"
      @mouseover.native="isShowPercentage"
      @mouseout.native="isShowPercentage"
      class="progress"
    ></el-progress>
  </div>
</template>

<script>
export default {
  name: "queprogress",
  props: ["data", "index"],
  data() {
    return {
      queType: {
        S: "单选",
        M: "多选",
      },
      //决定展示文字还是百分比
      showPercentage: false,
      colorClub: ["#409eff", "#e6a23c", "#6969eb"],
      textColor: "#ffffff",
    };
  },
  computed: {
    questionText() {
      return `${parseInt(this.index) + 1}.${this.data.queContent}（${
        this.queType[this.data.type]
      }）`;
    },
  },
  methods: {
    /**
     * 返回选项内容还是百分比
     * @param {Number} index 当前元素下标
     */
    format(index) {
      let option = this.data.options[index];
      return function () {
        return this.showPercentage
          ? `${this.forPercentage(option.percentage)}%`
          : option.content;
      }.bind(this);
    },

    isShowPercentage() {
      this.showPercentage = !this.showPercentage;
    },

    color(index) {
      return this.colorClub[index % 3];
    },

    forPercentage(percentage) {
      if (percentage !== null) {
        return parseFloat(percentage);
      } else {
        this.textColor = "#000000";
        return 0;
      }
    },
  },
  mounted() {
    console.log(this.data);
  },
};
</script>

<style scoped>
/* .progress {
  display: flex;
} */

/* p {
  width: 100px;
} */

.progress {
  margin: 10px;
}
</style>