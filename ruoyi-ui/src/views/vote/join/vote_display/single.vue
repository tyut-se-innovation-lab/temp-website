<template>
  <div class="single">
    <h3>{{ id + 1 }}.{{ question }}</h3>
    <el-radio-group v-model="select" class="radio">
      <el-radio
        v-for="(item, index) in answer"
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
  props: ["answer", "question", "id"],
  data() {
    return {
      select: -1,
    };
  },
  watch: {
    select(newValue, oldValue) {
      this.$emit("sendSingleAnswer", newValue, this.id);
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