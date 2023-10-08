<template>
  <div>
  <div class="title" :class="[isUse,voteTitle]">


      <div class="vote_title">
        <span class="vote_title_left"><p>标题：{{ childBriefly.title }}</p></span>
        <span class="vote_context">简介：{{ childBriefly.content }}</span>
        <span class="vote_title_right" :class="isConduct">{{ isMessage }}</span>
        <span class="vote_title_end">{{ isProceed }}</span>
      </div>
      <span class="vote_deadline">起始时间：{{ childBriefly.start }}</span>
      <span class="vote_deadline">截止时间：{{ childBriefly.end }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: ["childBriefly"],
  data() {
    return {}
  },
  mounted() {
    console.log(this.childBriefly);
  },
  methods: {},
  computed: {
    isProceed: function () {
      let end = new Date(this.childBriefly.end)
      let now = new Date()
      if (now > end) {
        return "已经结束了"
      } else {
        return "还在进行中"
      }
    },
    isMessage: function () {
      if (this.childBriefly.isjoin == true) {
        if (this.childBriefly.state == 2) {
          return ""
        } else {
          return "还没投票呢"
        }
      } else {
        return "已经投票啦"
      }
    },
    isConduct() {
      if (this.childBriefly.isjoin) {
        return "vote_title_right_now"
      } else
        return "vote_title_right_over"

    },
    voteTitle() {
      if (this.childBriefly.state == 2) {
        return "textDecoration"
      }
    },
    isUse() {
      if (this.childBriefly.state === 0) {
        return "Add"
      } else if (this.childBriefly.state === 1) {
        return "unAdd"
      }
    }
  }
}
</script>

<style scoped>
.title {
  height: 220px;
  width: 80%;
  margin: 0 auto;
  padding-left: 20px;
  padding-top: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
  border-bottom: 1px solid #5553;
  border-radius: 10px;

}

.textDecoration {
  text-decoration-line: line-through;
  text-decoration-color: #404040;
  text-decoration-thickness: 3px;
  background-color: rgba(0, 0, 0, 0.2);
  opacity: 0.5;
}

.vote_title {
  width: 100%;
  /*height: 100%;*/
}

.vote_context {
  padding-top: 10px;
  display: block;
  color: #777;
  font-size: large;
}

.vote_title_left {
  font-size: x-large;
  text-align: left;
  font-weight: 600;
  color: #444444;
}

.vote_deadline {
  display: inline-block;
  width: 100%;
  height: 15px;
  text-align: right;
  color: #655;
  font-size: 10px;
}

.vote_title_right {
  display: block;
  width: 100%;
  text-align: right;
  color: red;
}

.vote_title_right_now {
  display: block;
  width: 100%;
  text-align: right;
  color: green;
}

.vote_title_right_over {
  display: block;
  width: 100%;
  text-align: right;
  color: red;
}

.vote_title_right_delete {
  display: block;
  width: 100%;
  text-align: right;
  color: #9b9b9b;
}

.Add:hover {
  height: 225px;
  width: 82%;
  margin: 10px auto;
  background-color: #c9dcee;
  border-bottom: 1px solid #5553;
  box-shadow: 1px 1px 2px 2px blue;
  border-radius: 10px;
}

.unAdd:hover {
  height: 225px;
  width: 82%;
  margin: 10px auto;
  background: #e5c4c4;
  border-bottom: 1px solid #5553;
  box-shadow: 1px 1px 2px 2px red;
  border-radius: 10px;
}

.vote_title_end {
  display: block;
  width: 100%;
  text-align: right;
  color: gray;

}
</style>
