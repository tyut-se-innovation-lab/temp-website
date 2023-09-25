<template>
  <div class="box" v-if="isShow">
    <div class="success">
      <svg width="200" height="200">
        <circle
          fill="none"
          stroke="#68E534"
          stroke-width="10"
          stroke-linecap="round"
          transfrom="rotate(-90 100 100)"
          cx="100"
          cy="100"
          r="95"
          class="circle"
        />
        <polyline
          fill="none"
          stroke="#68E534"
          stroke-width="12"
          points="44,107 86.5,142 152,69"
          stroke-linecap="round"
          stroke-linejoin="round"
          class="tick"
        />
      </svg>
      <h2>Thank you</h2>
      <div class="close" @click="switchShow">&times;</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "success",
  data() {
    return {
      isShow: false,
    };
  },
  methods: {
    switchShow() {
      this.isShow = !this.isShow;
    },
  },
};
</script>

<style scoped>
* {
  display: flex;
  align-items: center;
  flex-direction: column;
}

.box {
  width: 100%;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.235);
  position: absolute;
  z-index: 2;
}

.success {
  width: 500px;
  height: 400px;
  border-radius: 15px;
  background-color: white;
  position: absolute;
  justify-content: center;
  margin-top: 20vh;
}

h2 {
  font-family: Helvetica;
  font-size: 36px;
  margin-top: 40px;
  color: #333;
  animation: text 2s ease-in-out;
}

.circle {
  stroke-dasharray: 597;
  stroke-dashoffset: 597;
  animation: circle 1s ease-out;
  animation-fill-mode: forwards;
}

.tick {
  stroke-dasharray: 175;
  stroke-dashoffset: 175;
  animation: tick 0.8s ease-out;
  animation-fill-mode: forwards;
  animation-delay: 0.95s;
}

.close {
  font-size: 40px;
  font-weight: 500;
  color: rgba(0, 0, 0, 0.235);
  position: absolute;
  right: 10px;
  top: 5px;
  cursor: pointer;
}

.close:hover {
  color: rgba(0, 0, 0, 1);
}

@keyframes circle {
  from {
    stroke-dashoffset: 597;
  }
  to {
    stroke-dashoffset: 1194;
  }
}

@keyframes tick {
  from {
    stroke-dashoffset: 175;
  }
  to {
    stroke-dashoffset: 0;
  }
}

@keyframes text {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>