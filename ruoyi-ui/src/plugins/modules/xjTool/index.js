import { Notification } from "@/plugins/modules/xjTool/xj-notification";


const xjTool = {
  notification (option) {
    return new Notification(option)
  }
}

export default xjTool

