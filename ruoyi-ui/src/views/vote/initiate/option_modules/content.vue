<!-- 投票简介 -->
<template>
  <div class="title">
    <span>投票简介</span>
    <br/>
    <div class="input">
      <el-input   type="textarea" :rows="2" placeholder="请输入内容" v-model="vote_content" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 内容
      vote_content: "",
    }
  },
  watch:{
    "vote_content":function(newValue){
      this.$emit("childContent",newValue);
    }
  }
}
</script>

<style scoped>
.title{
  margin-top: 20px;
  width: 100%;
  height: auto;
}
.input{
  width: 80%;
  margin-left: 10px;
  margin-right: 20px;
  margin-top: 10px;
  display: inline-block;
  padding-left: 40px;
  padding-top: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
  border: 2px solid #5550;
  margin-bottom: 10px;
}
span{
  margin-left: 10px;
  font-weight: bolder;
  font-size: large;
}

</style>
