<template>
  <div>
    <div id="sign" v-if="show">
      <button v-if="isSignIn" :disabled="disabled">{{ title }}</button>
      <button
        @click="signOut"
        :disabled="!isSignOut"
        :class="{ disabled: !isSignOut }"
        v-if="!isSignIn"
      >
        {{ signOutText }}
      </button>
    </div>
  </div>
</template>

<script>
import {Sign} from "@/api/attendance/sign/index.js";

export default {
  name: "sign",
  data() {
    return {
      sign: new Sign(),
      time: "00: 00: 00",
      isSignOut: false, //是否能签退
      isSignIn: true, //是否能签到
      timer: "",

      title: "签到",
      disabled: false,

      show: false,

      minCountTime: 1, //最小签到时间=
    };
  },

  computed: {
    signOutText() {
      if (!this.isSignOut) {
        return this.time;
      } else {
        return "签退";
      }
    },
  },

  methods: {
    /**
     * 签到
     */
    signIn() {
      this.sign.signIn().then((res) => {
        this.$modal.msgSuccess(res.msg);
        this.couldSignOut();
      });
    },

    /**
     * 初始化
     */
    init() {
    },


    /**
     * 是否设置自定义倒计时
     * @param {Date} startTime 开始时间
     */
    isSetAssignCountDown(startTime) {
      // 现在的时间
      let current = new Date();
      // 签到的时间
      let date = new Date(startTime);

      this.countDown(date, this.minCountTime);  // 第一个是点击签到按钮的时间戳，第二个是1
    },

    /**
     * 签退
     */
    signOut() {
      this.sign.signOut().then((res) => {
        this.$modal.msgSuccess(res.msg);
        this.couldSignOut();

        this.isSignOut = false;
        this.disabled = true;
        this.title = "签退成功";
      });
    },

    /**
     * 倒计时
     * @param {Date} startTime 开始时间字符串
     * @param {String} countTime 倒计时间(h)
     */
    countDown(startTime, countTime) {
      let start = new Date(startTime);
      let current = new Date();
      let hour = Math.floor(
        (start.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) /
        (1000 * 60 * 60)
      );
      let minute = Math.floor(
        ((start.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) %
          (1000 * 60 * 60)) /
        (1000 * 60)
      );
      let second = parseInt(
        ((start.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) %
          (1000 * 60)) /
        1000
      );
      //修改位数
      let timer = setInterval(() => {
        if (second != 0) {
          second--;
        } else if (minute != 0) {
          minute--;
          second = 59;
        } else if (second === 0 && minute === 0) {
          hour--;
          minute = 59;
        }
        if (hour < 0 || (minute === 0 && second === 0 && hour === 0)) {
          this.couldSignOut();
          clearInterval(timer);
        }
        this.time = `
          ${hour <= 9 ? "0" + hour : hour}:
          ${minute <= 9 ? "0" + minute : minute}:
          ${second <= 9 ? "0" + second : second}`;
      }, 1000);
    },

    /**
     * 是否可以签退
     */
    couldSignOut() {
      this.sign.couldSignOut().then((res) => {
        if (res.data.couldSignOut) {
          this.isSignIn = false;
          this.isSignOut = true;
        } else {
          if (res.data.attStartTime) {
            this.isSignIn = false;
            this.isSignOut = false;
            this.isSetAssignCountDown(res.data.attStartTime);
          } else {
            this.isSignIn = true;
            this.isSignOut = false;
          }
        }
        this.show = true;
      });
    },
  },

  created() {
    this.couldSignOut();
  },

  mounted() {
    setTimeout(() => {
      "111"
    },1000)
  }

};
</script>

<style scoped>
#sign {
  width: 70%;
  min-width: 400px;
  margin: 0 auto;
  display: flex;
  vertical-align: middle;
  text-align: center;
  margin-top: 15%;
  justify-content: space-evenly;
}

#sign > button {
  width: 150px;
  height: 150px;
  font-size: 20px;
  border-radius: 10px;
  font-weight: 600;
  background-image: linear-gradient(
    141deg,
    #9fb8ad 0%,
    #1fc8db 51%,
    #2cb5e8 75%
  );
  color: white;
  border: none;
  cursor: pointer;
  transition: 1s all;
}

#sign > button:hover {
  background-image: linear-gradient(
    141deg,
    rgb(134, 239, 172),
    rgb(59, 130, 246),
    rgb(147, 51, 234)
  );
}

.disabled {
  background-image: linear-gradient(
    rgb(17, 24, 39),
    rgb(75, 85, 99)
  ) !important;
}
</style>
