import request from '@/utils/request'

export default {

    //得到所有的课程信息
    addCourseInfo(courseInfo) {
        return request({
            url: `/eduservice/edu-course/addCourse`,
            method: 'post',
            data: courseInfo
        })
    },

    //得到所有的课程信息
    findAllTeacher() {
        return request({
            url: `/eduservice/edu-teacher/findAll`,
            method: 'get',

        })
    },
    //一级分类的数据
    getAllSubject() {
        return request({
            url: `/eduservice/edu-subject/getAllSubject`,
            method: 'get',
        })
    },

    //根据id值查询课程信息
    getCourseInfoById(id) {
        return request({
            url: `/eduservice/edu-course/getCourseInfo/${id}`,
            method: 'get',
        })
    },

    //修改信息
    updateCourseInfo(courseInfo) {
        return request({
            url: `/eduservice/edu-course/updateCourseInfo`,
            method: 'post',
            data:courseInfo
        })
    },

    //确认课程所有信息
    getPublishCourseInfo(id){
        return request({
            url: `/eduservice/edu-course/getPublishCourseInfo/${id}`,
            method: 'get',
        })
    },

     //发布课程
     publishCourse(id){
        return request({
            url: `/eduservice/edu-course/publishCourse/${id}`,
            method: 'post',
        })
    }

}

