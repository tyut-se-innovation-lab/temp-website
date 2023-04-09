<template>
  <div class="page">
    <button :class="[prevDisable ? active : disabled]" :disabled="prevDisable">
      >
    </button>
    <button v-for="item in pageData" @click="pageButtonClick">
      {{ item }}
    </button>
    <button :class="[nextDisable ? active : disabled]" :disabled="nextDisable">
      >
    </button>
  </div>
</template>

<script>
export default {
  props: ["pageData"],
  data() {
    return {
      currentPage: 1,
    };
  },
  compute: {
    prevDisable() {
      return this.currentPage === 1;
    },
    nextDisable() {
      return this.currentPage === pageSize;
    },
  },
  methods: {
    pageButtonClick() {
      this.$emit("pageClick", currentPage);
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage -= 1;
      }
      this.pageButtonClick();
    },
    nextPage() {
      if (this.currentPage < this.pageNum) {
        this.currentPage += 1;
      }
      this.pageButtonClick();
    },
  },
};
</script>

<style scoped>
.page {
  display: flex;
  min-width: 500px;
  width: 70%;
  margin: 0 auto;
  justify-content: space-between;
}
.page button {
  width: 50px;
  height: 50px;
}
.active {
  background-color: white;
}
.disabled {
  background-color: rgba(128, 128, 128, 0.538);
}
</style>