import xjNotification from "@/store/modules/xjTool/xj-notification"
import doc from "@/components/RuoYi/Doc/index.vue";

export default {
  namespaced: true,
  actions: {
    ...xjNotification.actions
  }
}
