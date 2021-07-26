import request from '@/utils/request'
export default {
    getCourseList(page,limit,searchObj) {
        return request({
            url: `/eduservice/course/${page}/${limit}`,
            method: 'post',
            data:searchObj
        })
    },

    getAllSubject(){
        return request({
            url: `/eduservice/edu-subject/getAllSubject`,
            method: 'get'
        })
    }
}