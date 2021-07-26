import request from '@/utils/request'

export default {
    getPageList(page, limit) {
        return request({
            url: `/eduservice/index/getFrontList/${page}/${limit}`,
            method: 'get'
        })
    },

    getById(teacherId) {
        return request({
            url: `/eduservice/index/getTeacher/${teacherId}`,
            method: 'get'
        })
    }
}