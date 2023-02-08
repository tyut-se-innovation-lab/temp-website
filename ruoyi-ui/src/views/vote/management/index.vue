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
        VoteManager: "投票管理员",
        DEVLeader: "开发组组长",
        CSLeader: "网安组组长",
        DEVManager: "开发组管理员",
        CSManager: "网安组管理员",
        DEVMember: "开发组成员",
        CSMember: "网安组成员",
      },
      nameValue: {
        VoteManager: "1",
        DEVLeader: "1",
        CSLeader: "1",
        DEVManager: "1",
        CSManager: "1",
        DEVMember: "1",
        CSMember: "1",
      },
      management: new Management(),
    };
  },
  components: {
    Submit,
  },
  methods: {
    getManagData() {
      this.management.getManageValue().then((res) => {
        console.log(res);
      });
    },
    submitManagData() {
      this.management.modifManageValue(this.data).then((res) => {
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