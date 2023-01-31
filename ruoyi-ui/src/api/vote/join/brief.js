import request from '@/utils/request'

function Brief() {

}


/**
 * 获取投票列表
 * @returns 
 */
function getVoteList() {
    return request({
        url: '',
        method: 'get',
    })
}

// 获取投票列表
function withdrawVote(id) {
    return request({
        url: '',
        method: 'delete',
        query: id
    })
}