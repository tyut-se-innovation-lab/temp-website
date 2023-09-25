<!--文本框-->                                                                                                                                                               !&#45;&#45; 文本框 &ndash;&gt;-->
<template>
  <div class="title">
    <span>文本输入</span>
    <br />
    <div class="input" v-for="(items,index) in fill" :key="index">
      <strong>{{ index + 1 + Msg }} 、</strong>
      <el-input
        type="input"
        placeholder="请输入问题"
        v-model="items.queContent"
      /><br /><br />
      <el-input type="textarea" placeholder="请输入内容" disabled v-model="items.content" />
      <div class="button">
        <el-button type="danger" @click="fill_delete(index)">删除</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    Msg: {
      type: Number,// 这里你接收的值是什么类型就写什么类型
    }
  },
  data() {
    return {
      fillType:"T",
      // 内容
      fill: [
        {
          queContent: "",
          id: this.Msg, //题号
          type: "T", // 类型 ----文本框
          options: [], //文本内容
        },
      ],
    };
  },
  methods:{
    // 删除文本框
    fill_delete(index){
      this.fill.splice(index,1);
    },
    addFill(){
      let index = this.fill.length - 1;
      this.fill.push(
        {
          queContent: "",
          id: this.Msg, //题号
          type: "T", // 类型 ----文本框
          options: [], //文本内容
        },
      )
    }
  },
  watch: {
    fill: {
      handler(newValue) {
        this.$emit("childFill", newValue);
      },
      deep: true,//深度监视
      immediate:true,//页面第一次渲染触监听器
    },
    Msg(newVal){
      // newVal是新值，oldVal是旧值
      this.Msg = newVal
    }
  },
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
.input >>> .el-input {
  width: 90%;
}
.input >>> .el-textarea__inner{
  width: 90%;
  margin-left: 39px;
}
span {
  margin-left: 10px;
  font-weight: bolder;
  font-size: large;
}

.button {
  margin-top: 10px;
  text-align: right;
}
</style>
