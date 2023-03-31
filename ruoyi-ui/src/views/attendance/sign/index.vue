<template>
  <div id="sign">
    <button @click="signIn">签到</button>
    <button
      @click="signOut"
      :disabled="!isSignOut"
      :class="{ disabled: !isSignOut }"
    >
      {{ signOutText }}
    </button>
  </div>
</template>

<script>
import { Sign } from "@/api/attendance/sign/index.js";
export default {
  name: "sign",
  data() {
    return {
      sign: new Sign(),
      time: "",
      isSignOut: false, //是否能签退
      isSignIn: true, //是否能签到
      timer: "",
    };
  },

  computed: {
    signOutText() {
      if (!this.isSignOut) {
        return this.isSignIn ? "先签到(^_^)" : this.time;
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
     * 签退
     */
    signOut() {
      this.sign.signOut().then((res) => {
        this.$modal.msgSuccess(res.msg);
        this.couldSignOut();
        this.isSignOut = false;
      });
    },

    /**
     * 倒计时
     * @param {String} startTime 开始时间字符串
     * @param {String} countTime 倒计时间(h)
     */
    countDown(startTime, countTime) {
      let start = new Date(startTime);
      let current = new Date();
      let minute = Math.floor(
        (start.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) /
          (1000 * 60)
      );
      let second = parseInt(
        ((start.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) %
          (1000 * 60)) /
          1000
      );
      //修改位数
      let timer = setInterval(() => {
        console.log(minute, second);
        if (minute < 0 || (minute === 0 && second === 0)) {
          this.couldSignOut();
          clearInterval(timer);
        }
        if (second != 0) {
          second--;
        } else {
          minute--;
          second = 59;
        }
        this.time = `${minute <= 9 ? "0" + minute : minute}:${
          second <= 9 ? "0" + second : second
        }`;
      }, 1000);
    },

    shutCountDown() {
      clearInterval(this.timer);
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
            this.countDown(res.data.attStartTime, 1);
          } else {
            this.isSignIn = true;
            this.isSignOut = false;
          }
        }
      });
    },
  },

  created() {
    this.couldSignOut();
  },
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