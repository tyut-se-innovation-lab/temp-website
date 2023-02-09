<!-- 标题 -->
<template>
  <div class="title">
    <span>投票标题</span>
    <br/>
    <div class="input">
      <el-input placeholder="请输入标题" v-model="title" />
    </div>
  </div>
</template>

<script>
export default {
  name:'voteTitle',
  data() {
    return {
      // 内容
      title: "",

    }
  },
  watch:{
    "title": function(newValue,oldValue){
      this.$emit("childTitle",newValue);
    }
  }
}
</script>

<style scoped>
.title{
  padding-top:20px ;
  width: 100%;
  height: auto;
}
.input{
  width: 80%;
  margin-left: 10px;
  margin-right: 20px;
  padding-left: 40px;
  padding-top: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
  border: 2px solid #5550;
  background: #f1f7ff;
  margin-bottom: 10px;
}
span{
  margin-left: 10px;
  font-weight: bolder;
  font-size: large;
}

</style>
