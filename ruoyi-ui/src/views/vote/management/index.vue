<template>
  <div class="management">
    <div v-for="(val, key, i) of nameType" :key="i" class="box">
      <p>{{ val }}:</p>
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
      nameType: {
        votemanager: "投票管理员",
        devleader: "开发组组长",
        csleader: "网安组组长",
        devmanager: "开发组管理员",
        csmanager: "网安组管理员",
        devmember: "开发组成员",
        csmember: "网安组成员",
      },
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