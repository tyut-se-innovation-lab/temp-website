<template>
  <div class="fill">
    <h3>{{ index + 1 }}.{{ question }}</h3>
    <textarea
      v-model="content_data"
      class="textarea"
      placeholder="请在此输入..."
    ></textarea>
  </div>
</template>

<script>
export default {
  name: "fill",
  props: ["question", "content", "index"],
  data() {
    return {
      content_data: "",
    };
  },
  watch: {
    content_data(newValue, oldValue) {
      this.$emit("sendContent", newValue, this.index);
    },
  },
  mounted() {
    this.content_data = this.content;
  },
};
</script>

<style scoped>
.fill {
  margin: 20px 0;
}
.textarea {
  display: block;
  width: 95%;
  height: 20vh;
  resize: none;
  margin: 20px auto;
  padding: 10px;
}
</style>