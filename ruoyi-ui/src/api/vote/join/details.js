import request from '@/utils/request'

export function Details() {

}

Details.prototype = {
    /**
    * 获取投票详细信息-
    * @param {Number} id 
    * @returns 
    */
    getVoteDetails: function (id) {
        return request({
            url: '',
            method: 'get',
            data: id
        })
    },

    /**
    * 传输投票的选项
    * @param {Object} data 
    * @returns 
    */
    sendVoteSelect: function (data) {
        return request({
            url: '',
            method: 'post',
            params: data
        })
    },

    /**
     * 传递单选选项
     * @param {} select 
     * @param {*} id 
     */
    sendSingleSelect: function (select, id) {
        //清空
        for (let i = 0; i < this.vote_data.options[id].answer.length; i++) {
            console.log(this.vote_data.options[id].answer.length);
            this.vote_data.options[id].answer[i].select = "0";
        }
        //赋值选项
        this.vote_data.options[id].answer[select].select = "1";
    }
}