<!-- 截止时间 -->
<template>
  <div class="title">
    <span>截止时间</span>
    <br />
    <div class="input">
      <el-date-picker
        v-model="endTime"
        value-format="yyyy-MM-dd HH:mm:ss"
        :picker-options="pickerOptions"
        type="date"
        format="yyyy - MM - dd "
      >
      </el-date-picker>
    </div>
  </div>
</template>

<script>
export default {
  name:'vote_deadline',
  data() {
    // 这里存放数据
    return {
      endTime:'',
      pickerOptions:{
          disabledDate (time) {
            //disabledDate 文档上：设置禁用状态，参数为当前日期，要求返回 Boolean
            return time.getTime() < Date.now()//选当前时间之后的时间
          }
      },
    }
  },
  watch:{
    'endTime':function(newValue){
      this.$emit('childDeadline', newValue);
    }
  }

};
</script>

<style scoped>
.title {
  margin-top: 20px;
  width: 100%;
  height: auto;
}
.input {
  width: 80%;
  display: inline-block;
  padding-left: 40px;
  padding-top: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
  border: 2px solid #5550;
  margin: 10px 20px;
}
span {
  margin-left: 10px;
  font-weight: bolder;
  font-size: large;
}
</style>
