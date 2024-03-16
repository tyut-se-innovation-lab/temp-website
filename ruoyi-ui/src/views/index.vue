<template>
  <div class="app-container home">
    <pre>
        _____  __   __  _   _   _____    ___     ___     _       ___     ___      _
       |_   _| \ \ / / | | | | |_   _|  / __|   | __|   | |     /   \   | _ )    | |
         | |    \ V /  | |_| |   | |    \__ \   | _|    | |__   | - |   | _ \    |_|
        _|_|_   _|_|_   \___/   _|_|_   |___/   |___|   |____|  |_|_|   |___/   _(_)_
      _|"""""|_| """ |_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_| """ |
      "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'
      </pre>
    <h3>别说丑，说就是你来写首页！</h3>

    <img src="../assets/images/suggest.png" alt="" srcset="" class="suggestion" @click="open">

    <div class="title"  @click="open">意见箱</div>
    <el-dialog title="意见箱" :visible.sync="dialogFormVisible"  :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="意见主题"  :rules="[
      { required: true, message: '意见主题不能为空'},
    ]" maxlength="20" placeholder="请输入不超过20个字符">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="提交方式">
          <el-col :span="8">
            <el-select v-model="form.region" placeholder="实名或者匿名">
              <el-option label="实名" value="shiming"></el-option>
              <el-option label="匿名" value="niming"></el-option>
            </el-select>
          </el-col>
          <el-col :span="15">
            <el-form-item label="信息" v-if="form.region === 'shiming'">
              <el-input v-model="form.relname" v-if="form.region === 'shiming'" placeholder="学号-姓名"></el-input>
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="意见内容"  :rules="[
      { required: true, message: '意见内容不能为空'},
    ]">
          <el-input type="textarea" v-model="form.desc" maxlength="300" placeholder="请输入不超过300个字符" :rows="15" scrollbar="true"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sumbitSuggest">提交建议</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { suggestion } from "@/api/suggest"
export default {

  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.3",
      dialogFormVisible: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px'

    };
  },
  mounted() {

    window.addEventListener("click", this.onClick, false);
  },

  methods: {
    open(){
      this.form.desc = '',
      this.form.name = '',
      this.form.region = '',
      this.dialogFormVisible = true
    },
    goTarget(href) {
      window.open(href, "_blank");
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    },
    sumbitSuggest() {
      this.$confirm('您确认提交？')
        .then(_ => {
          this.dialogFormVisible = false
          suggestion(this.form.desc,this.form.name).then((res)=>{
            this.$message.success(res.msg + '感谢您的意见')
          })
        })
        .catch(_ => { });
    }
  }
};
</script>

<style scoped lang="scss">
.title {
  position: absolute;
  bottom: 40px;
  right: 27px;
  width: 5px;
  font-weight: bolder;
}

.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans",
  "Helvetica Neue",
  Helvetica,
  Arial,
  sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}

.suggestion {
  position: absolute;
  bottom: 50px;
  right: 50px;
  height: 50px;
  width: 50px;
  animation: bounce 2.5s infinite 0.5s both;
  transform-origin: center bottom;
}

.suggestion:hover {
  filter: brightness(50%);
  cursor: pointer;
  animation-play-state: paused
}
.title:hover{
  cursor: pointer;
}
@keyframes bounce {

  0%,
  7%,
  25%,
  36%,
  45%,
  50% {
    animation-timing-function: ease-out;
    transform: translate3d(0, 0, 0);
  }

  15%,
  16% {
    animation-timing-function: ease-in;
    transform: translate3d(0, -30px, 0);
  }

  30% {
    animation-timing-function: ease-in;
    transform: translate3d(0, -20px, 0);
  }

  41% {
    animation-timing-function: ease-in;
    transform: translate3d(0, -10px, 0);
  }

  47% {
    animation-timing-function: ease-in;
    transform: translate3d(0, -3px, 0);
  }
}
</style>
