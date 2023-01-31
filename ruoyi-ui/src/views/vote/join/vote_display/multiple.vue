<template>
  <div class="multiple">
    <h3>{{ id + 1 }}.{{ question }}</h3>
    <div class="checkbox">
      <el-checkbox
        v-for="(item, index) in answer"
        :key="index"
        v-model="answer[index].select"
        border
        >{{ item.content }}</el-checkbox
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "multiple",
  props: ["question", "answer", "id"],
  data() {
    return {
      answer_data: [],
    };
  },
  watch: {
    answer_data: {
      handler(newValue, oldValue) {
        this.$emit("sendMultipleAnswer", newValue, this.id);
      },
      deep: true,
    },
  },
  mounted() {
    this.answer_data = this.answer;
  },
};
</script>

<style scoped>
.multiple {
  margin-top: 20px;
}
.checkbox {
  margin: 20px;
}
</style>