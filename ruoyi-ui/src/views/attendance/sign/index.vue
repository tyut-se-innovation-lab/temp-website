<template>
  <div id="sign">
    <button @click="signIn">签到</button>
    <button @click="signOut" :disabled="this.disabled">签退</button>
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
    };
  },

  methods: {
    signIn() {
      this.sign.signIn().then((res) => {
        this.$modal.msgSuccess(res.msg);
      });
    },
    signOut() {
      console.log(666);
      this.sign.signOut().then((res) => {
        console.log(res);
        // $.modal.msgSuccess("签退成功");
      });
    },
    couldSignOut() {
      this.sign.couldSignOut().then((res) => {
        if (res) {
          this.$modal.msgSuccess(res.msg);
          this.disabled = res;
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
</style>