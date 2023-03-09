<template>
  <div class="single">
    <h3>{{ index + 1 }}.{{ question }}</h3>
    <el-radio-group v-model="select" class="radio">
      <el-radio
        v-for="(item, index) in options"
        :key="index"
        :label="index"
        border
        >{{ item.content }}</el-radio
      >
    </el-radio-group>
  </div>
</template>

<script>
export default {
  name: "single",
  props: ["options", "question", "index"],
  data() {
    return {
      select: -1, //选中的选项下标
    };
  },
  watch: {
    select(newValue, oldValue) {
      this.$emit("sendSingleAnswer", newValue, this.index);
    },
  },
  mounted() {},
};
</script>

<style scoped>
.single {
  margin-top: 20px;
}
.radio {
  margin: 20px 20px 0px 20px;
}
</style>