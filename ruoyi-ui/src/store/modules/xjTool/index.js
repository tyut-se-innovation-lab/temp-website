import xjNotification from "@/store/modules/xjTool/xj-notification"

export default {
  namespaced: true,
  actions: {
    ...xjNotification.actions
  }
}
