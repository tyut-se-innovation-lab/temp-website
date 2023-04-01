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
import { delay } from "lodash";
export default {
  name: "sign",
  data() {
    return {
      sign: new Sign(),
      time: "",
      isSignOut: false, //是否能签退
      isSignIn: true, //是否能签到
      timer: "",
      customTimes: {
        startTime: [19, 30], //开始时间
        delay: 2, //持续时间
      },
      timeInterval: [],
      minCountTime: 1,
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
     * 初始化
     */
    init() {
      let date1 = new Date();
      date1.setHours(19);
      date1.setMinutes(30);
    },

    // 设置当天指定时间
    setAssignTime(customTimes) {
      let date = new Date();
      date.setHours(customTimes.startTime[0]);
      date.setMinutes(customTimes.startTime[1]);
      this.timeInterval[0] = date;
      date.setHours(customTimes.startTime[0] + delay);
      this.timeInterval[1] = date;
    },

    /**
     * 自定义倒计时
     */
    setAssignCountDown(startTime, countTime = 1) {
      let current = new Date();
      //最小倒计时
      let currentToTargTime =
        (date.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) /
        (60 * 60 * 1000);
      if (currentToTargTime <= this.minCountTime) {
        this.countDown(startTime, this.minCountTime);
      } else {
        this.countDown(startTime, this.countTime);
      }
    },

    /**
     * 是否设置自定义倒计时
     */
    isSetAssignCountDown(startTime) {
      let current = new Date();
      let date = new Date(startTime);
      this.setAssignTime(this.customTimes);
      if (
        current.getTime() > this.timeInterval[0].getTime &&
        current.getTime() < this.timeInterval[1].getTime
      ) {
        this.setAssignCountDown(this.timeInterval[0], this.customTimes.delay);
      } else {
        this.countDown(date, 1);
      }
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
     * @param {Date} startTime 开始时间字符串
     * @param {String} countTime 倒计时间(h)
     */
    countDown(startTime, countTime) {
      let current = new Date();
      let hour = Math.floor(
        (startTime.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) /
          (1000 * 60 * 60)
      );
      let minute = Math.floor(
        (startTime.getTime() + countTime * 60 * 60 * 1000 - current.getTime()) /
          (1000 * 60)
      );
      let second = parseInt(
        ((startTime.getTime() +
          countTime * 60 * 60 * 1000 -
          current.getTime()) %
          (1000 * 60)) /
          1000
      );
      //修改位数
      let timer = setInterval(() => {
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