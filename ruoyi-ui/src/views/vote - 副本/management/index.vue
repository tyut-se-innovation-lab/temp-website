<template>
  <div class="management">
    <div v-for="(val, key, i) of nameValue" :key="i" class="box">
      <p>{{ key }}:</p>
      <el-input
        v-model="nameValue[key]"
        placeholder="请输入内容"
        size="medium"
        class="input"
      ></el-input>
    </div>
    <Submit @submitMethod="submitManagData"></Submit>
  </div>
</template>

<script>
import Management from "@/api/vote/management/index.js";
import Submit from "../vote_display/submit.vue";
export default {
  data() {
    return {
      nameValue: {},
      management: new Management(),
    };
  },
  components: {
    Submit,
  },
  methods: {
    getManagData() {
      this.management.getManageValue().then((res) => {
        this.nameValue = res.data;
        console.log(res.data);
      });
    },
    submitManagData() {
      this.management.modifManageValue(this.nameValue).then((res) => {
        console.log(res);
      });
    },
  },
  created() {
    this.getManagData();
  },
};
</script>

<style scoped>
.management {
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
.box {
  display: inline-block;
}
.input {
  min-width: 200px;
}
</style>