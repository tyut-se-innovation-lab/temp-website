<!-- 复选框 -->
<template>
  <div>
    <span>多选题</span>
    <div class="input" v-for="(item, index) in option" :key="index">
      <strong>{{ index + 1 + dataMsg }} 、</strong>
      <el-input v-model="item.queContent" placeholder="请输入题目" />

      <div
        class="option"
        v-for="(options, option_index) in item.options"
        :key="option_index"
      >
        <el-checkbox :label="option_index" >
          <el-input v-model="options.content" placeholder="请输入选项内容" />
          <el-button type="primary" @click="option_delete(index, option_index)"
            >删除选项</el-button
          >
        </el-checkbox>
      </div>
      <el-button class="buttonAdd" type="primary" @click="addOption(index)"
        >添加选项</el-button
      >

      <!-- 单选的的按钮 -->
      <div class="button">
        <el-button type="primary" @click="single_delete(index)">删除</el-button>
      </div>
 </div>
<!--    <div class="add">-->
<!--      <el-button type="primary" @click="addMultiple()">添加多选</el-button>-->
<!--    </div>-->


  </div>
</template>

<script>
export default {
  props: {
    dataMsg: {
      type: Number, // 这里你接收的值是什么类型就写什么类型
    }
  },
  data() {
    return {
      multipleType:"M",
      option: [
        {
          id: this.dataMsg, //题号
          queContent: "", //问题内容
          type: "M", //类型
          options: [
            {
              id: 0, //题号  从0开始   0，1，2，3，4……
              content: "", //内容
              isSelect: "", //选项内容  用户是否选择  0 未选, 1 选中
              type: "M", //类型和问题一样   如果需要文本框就改为文本框
              other: "",
            },
          ],
        },
      ],
    };
  },
  methods: {
    // 添加选项
    addOption(index) {
      let option_index = this.option[index].options.length - 1;
      this.option[index].options.push({
        id: option_index, //题号  从0开始   0，1，2，3，4……
        content: "", //内容
        isSelect: "", //选项内容  用户是否选择  0 未选, 1 选中
        type: this.multipleType, //类型和问题一样   如果需要文本框就改为文本框
        other: "",
      });
    },
    // 添加问题
    addMultiple() {
      let index = this.option.length - 1
      this.option.push({
        id: this.dataMsg + index, //题号
        queContent: "", //问题内容
        type: this.multipleType, //类型
        options: [
          {
            id: 0, //题号  从0开始   0，1，2，3，4……
            content: "", //内容
            isSelect: "", //选项内容  用户是否选择  0 未选, 1 选中
            type: this.multipleType, //类型和问题一样   如果需要文本框就改为文本框
            other: "",
          },
        ],
      });
    },
    //删除单选题
    single_delete(index) {
      this.option.splice(index, 1);
    },
    // 删除单选选项
    option_delete(index, option_index) {
      this.option[index].options.splice(option_index, 1);
    },

  },
  watch: {
		option: {
			handler(newVal, oldVal) {
        this.$emit("childMultiple",newVal);
			},
			deep: true,//深度监视
      immediate:true,//页面第一次渲染触监听器
		},
    dataMsg(newVal, oldVal){
    // newVal是新值，oldVal是旧值
    this.dataMsg = newVal
    // console.log(this.dataMsg);
    }
  }
}
</script>

<style scoped>
span {
  margin-left: 10px;
  font-weight: bolder;
  font-size: large;
}

.input {
  width: 80%;
  margin-left: 10px;
  margin-right: 20px;
  margin-top: 10px;

  padding-left: 40px;
  padding-top: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
  border: 2px solid #5550;
  background: #f1f7ff;
  margin-bottom: 10px;
}
.input >>> .el-button {
  margin-top: 10px;
}
.button {
  margin-top: 10px;
  text-align: right;
}
.input >>> .el-input {
  width: 90%;
}
.option {
  margin-left: 30px;
}
.option >>> .el-input {
  width: 100%;
}
.buttonAdd {
  margin-left: 30px;
}
.input >>> .el-input__inner {
  margin-top: 10px;
  display: inline-block;
  width: 80%;
}

.add {
  margin-left: 10px;
}
</style>
