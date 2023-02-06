<template>
  <div class="progress">
    <p>{{ this.questionText }}</p>
    <el-progress
      v-for="item in data.options"
      :text-inside="true"
      :key="item.id"
      :stroke-width="24"
      :percentage="item.percentage"
      :format="format(item.id)"
      :color="color(item.id)"
      @mouseover.native="isShowPercentage"
      @mouseout.native="isShowPercentage"
      class="progress"
    ></el-progress>
  </div>
</template>

<script>
export default {
  name: "queprogress",
  props: ["data"],
  data() {
    return {
      queType: {
        S: "单选",
        M: "多选",
      },
      //决定展示文字还是百分比
      showPercentage: false,
      colorClub: ["#409eff", "#e6a23c", "#6969eb"],
    };
  },
  computed: {
    questionText() {
      return `${parseInt(this.data.id) + 1}.${this.data.queContent}（${
        this.queType[this.data.type]
      }）`;
    },
  },
  methods: {
    /**
     *
     */
    format(index) {
      let option = this.data.options[index];
      return function () {
        return this.showPercentage ? `${option.percentage}%` : option.content;
      }.bind(this);
    },

    isShowPercentage() {
      this.showPercentage = !this.showPercentage;
    },

    color(index) {
      return this.colorClub[index % 3];
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