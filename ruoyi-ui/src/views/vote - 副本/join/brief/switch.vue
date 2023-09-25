<template>
  <div class="switch">
    <ul class="ulist">
      <li v-for="(item, index) in builder" :key="index">
        <a @click="callBuilderMethod(index)" href="#">{{ item.text }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ["builder"],
  data() {
    return {};
  },
  methods: {
    callBuilderMethod(index) {
      this.$emit(this.builder[index].methods);
    },
  },
};
</script>

<style scoped>
.switch {
  width: 62vw;
  margin: 0 auto;
}
a {
  text-decoration: none;
}
.ulist li {
  margin-right: 20px;
  display: inline;
}
</style>