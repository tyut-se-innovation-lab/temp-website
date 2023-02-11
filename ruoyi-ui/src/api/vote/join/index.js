import request from '@/utils/request'

export default class Join {
    constructor() {

    }

    /**
    * 获取投票列表
    * @returns 
    */
    getBriefList() {
        return request({
            url: '/selab/vote/join/list',
            method: 'get',
        })
    }

    /**
     * 初始化参与投票
     * @param {Object} data 
     * @returns 
     */
    initJoin(data) {
        let tmpdata1 = this.sortbriefDataByDate(data);
        let tmpdata2 = this.fixDateData(tmpdata1);
        return this.sortbriefDataByIsjoin(tmpdata2);
    }

    /** 修正数据 */

    /**
     * 根据是否已经参与进行排序
     * @param {Object} data 要处理的data 
     * @returns 
     */
    sortbriefDataByIsjoin(data) {
        this.sortbriefDataByDate(data);
        let arr1 = this.filterJoined(data);
        let arr2 = this.filterUnjoined(data);
        return arr2.concat(arr1);
    }

    /**
     * 根据投票发起时间进行排序
     * @param {Object} data 要处理的data 
     * @returns 
     */
    sortbriefDataByDate(data) {
        return data.sort((a, b) => {
            let date1 = new Date(a.creatTime);

            let date2 = new Date(b.creatTime);
            return date1 - date2;
        });
    }

    /**
     * 过滤出参与过的投票
     * @param {*} data 
     * @returns 
     */
    filterJoined(data) {
        return data.filter((item) => {
            return item.join === true;
        });
    }

    /**
     * 过滤出未参与过的投票
     * @param {*} data 
     * @returns 
     */
    filterUnjoined(data) {
        return data.filter((item) => {
            return item.join === false;
        });
    }

    /**
     * 修正date数据
     * @param {Object} data 
     */
    fixDateData(data) {
        for (let i = 0; i < data.length; i++) {
            data[i].creatTime = this.dateToString(data[i].creatTime);
            data[i].deadline = this.dateToString(data[i].deadline);
        }
        console.log(data);
        return data;
    }

    /**
     * 输出指定字符串数据
     * @param {String} string 
     */
    dateToString(string) {
        let date = new Date(string);
        let datestringarr = date.toLocaleString().split(" ");
        return this.modfiString("/", "-", datestringarr[0]);
    }

    /**
     * 修正字符串
     * @param {String} oldval 旧字符
     * @param {String} newval 替换字符
     * @param {String} string 原始字符串
     * @returns 替换后字符串
     */
    modfiString(oldval, newval, string) {
        let stringarr = string.split(oldval);
        return stringarr.join(newval);
    }
}