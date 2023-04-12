<template>
  <div class="page">
    <button
      :class="!prevDisable ? 'active' : 'disabled'"
      :disabled="prevDisable"
      @click="prevPage"
    >
      <
    </button>
    <button
      v-for="item in pageArr"
      class="active"
      @click="pageNumClick(item)"
      :disabled="item === '...'"
    >
      {{ item }}
    </button>
    <button
      :class="!nextDisable ? 'active' : 'disabled'"
      :disabled="nextDisable"
      @click="nextPage"
    >
      >
    </button>
  </div>
</template>

<script>
import { Pagination } from "@/api/extends/pages/index.js";
export default {
  props: {
    pageData: {
      type: Object,
      required: true,
      default: function () {
        return null;
      },
    },
  },
  props: ["pageData"],
  data() {
    return {
      currentPage: 1,
      pageArr: [],
      data: null,
      pagerCount: 0,
    };
  },
  computed: {
    prevDisable() {
      return this.currentPage === 1;
    },
    nextDisable() {
      return this.currentPage === this.pageData.totalPages;
    },
  },
  methods: {
    /**
     * 初始化
     */
    init() {
      this.currentPage = this.pageData.currentPage;
      this.pagerCount = this.pageData.pagerCount;
      this.page = new Pagination(this.pageData);
      this.pageArr = this.page.initPagination();
    },

    /**
     * 数字按钮按下
     * @param {Number} index 当前页的下标
     */
    pageNumClick(index) {
      this.currentPage = index;
      this.pageButtonClick();
    },

    /**
     * 按钮基础方法
     */
    pageButtonClick() {
      this.$emit("pageClick", this.currentPage);
    },

    /**
     * 上一页
     */
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage -= 1;
      }
      this.pageButtonClick();
    },

    /**
     * 下一页
     */
    nextPage() {
      if (this.currentPage < this.pagerCount) {
        this.currentPage += 1;
      }
      this.pageButtonClick();
    },
  },
  watch: {
    pageData: {
      handler(newVal, oldVal) {
        console.log(newVal, oldVal);
        // setTimeout(() => {
        console.log(this.pageData.totalPages);
        this.init();
        // }, 3000);
      },
      deep: true,
      // immediate: true,
    },
    // pageData(newVal, oldVal) {
    //   console.log(6666);
    // },
  },
  mounted() {
    this.init();
  },
};
</script>

<style scoped>
.page {
  display: flex;
  width: 550px;
  margin: 20px auto;
  justify-content: space-between;
}
button {
  width: 32px;
  height: 32px;
  background-color: white;
  border: 1px solid black;
  border-radius: 8px;
  transition: all 0.5s;
}

.active:hover {
  background-color: rgb(51, 197, 255);
}
.disabled {
  background-color: rgba(128, 128, 128, 0.538);
  pointer-events: none;
  color: black;
}
</style>