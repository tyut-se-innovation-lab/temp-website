//根据时间过滤
import { Filter } from './index.js';
class FilterDate extends Filter {
    constructor() {

    }

    /**
     * 时间过滤
     * @param {Array} dateGap 日期数组:两个元素
     * @param {Array} data 待过滤数据 
     * @param {String} name 要过滤数据的名字
     */
    filterByDate(dateGap, data, name) {
        let tmpData = data;
        return tmpData.filter((data) => {
            let compare;
            if (Array.isArray(data[name])) {
                compare = new Date(data[name][0]);
            } else {
                compare = new Date(data[name]);
            }

            return compare >= new Date(dateGap[0]) && compare <= new Date(dateGap[1]);
        });
    }
}

export {
    FilterDate
}