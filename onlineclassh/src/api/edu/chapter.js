import request from '@/utils/request'

export default{

    //得到所有的章节信息
    getAllChapter(courseId){
        return request({
            url: `/eduservice/edu-chapter/getAllCourse/` + courseId,
            method: 'get',

          })
    },
}

