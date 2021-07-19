import request from '@/utils/request'

export default{

    //得到所有的课程信息
    getAllSubject(){
        return request({
            url: `/eduservice/edu-subject/getAllSubject`,
            method: 'get',
          })
    },



}

