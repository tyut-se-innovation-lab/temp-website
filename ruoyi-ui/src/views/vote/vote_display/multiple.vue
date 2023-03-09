<template>
  <div class="multiple">
    <h3>{{ index + 1 }}.{{ question }}</h3>
    <div class="checkbox">
      <el-checkbox
        v-for="(item, index) in options"
        :key="index"
        v-model="options[index].isSelect"
        border
        >{{ item.content }}</el-checkbox
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "multiple",
  props: ["question", "options", "index"],
  data() {
    return {
      options_data: [],
    };
  },
  watch: {
    options_data: {
      handler(newValue, oldValue) {
        this.$emit("sendMultipleAnswer", newValue, this.index);
      },
      deep: true,
    },
  },
  mounted() {
    this.options_data = this.options;
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