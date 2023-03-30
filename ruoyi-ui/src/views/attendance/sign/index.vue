<template>
  <div id="sign">
    <button @click="signIn">签到</button>
    <button
      @click="signOut"
      :disabled="disabled"
      :class="{ disabled: disabled }"
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
      disabled: false,
      time: "",
    };
  },

  computed: {
    signOutText() {
      return this.disabled ? "不允许签退(^_^)" : "签退";
    },
  },

  methods: {
    /**
     * 签到
     */
    signIn() {
      this.sign.signIn().then((res) => {
        this.$modal.msgSuccess(res.msg);
      });
    },

    /**
     * 签退
     */
    signOut() {
      this.sign.signOut().then((res) => {
        this.$modal.msgSuccess(res.msg);
        this.couldSignOut();
      });
    },

    /**
     * 倒计时
     */
    countDown() {
      let timer = setInterval(() => {
        if (this.time < 0) {
          clearInterval(timer);
        }
      }, 1000);
    },

    /**
     * 是否可以签退
     */
    couldSignOut() {
      this.sign.couldSignOut().then(
        (res) => {
          this.$modal.msgSuccess(res.msg);
          this.disabled = false;
        },
        (err) => {
          this.disabled = true;
        }
      );
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