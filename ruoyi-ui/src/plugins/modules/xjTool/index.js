import { Notification } from "@/plugins/modules/xjTool/xj-notification";
import Loading from './xj-loading'

const xjTool = {
  /**
   * notification 通知弹窗
   * @param {Object} option
   * @option {string} title 标题 选填
   * @option {string} message 信息 必填
   * @option {string} type 类型 选填 默认normal   success warning error
   * @option {string} location 位置 选填 默认left-bottom   left-top right-top right-bottom left-bottom-1...
   * @option {number} duration 持续时间 默认3000 如果是0 则不会自动关闭 除 0 外 最小值为 1000
   * @option {boolean} showClose 是否可以手动关闭 默认可以 开启时，鼠标移入会停止自动关闭计时
   * @option {boolean} userSelect 是否限制文本选中 默认 true 禁止选中文本
   * @option {Function | null} callback 点击通知框时运行的回调函数，callback 接收一个 notification 位置的参数
   */
  notification (option) {
    return new Notification(option)
  },
  /**
   * @param {HTMLDivElement} div 要添加加载的节点
   * @param {string} color
   */
  loading (div, color) {
    return new Loading(div, color)
  }
}

export default xjTool

