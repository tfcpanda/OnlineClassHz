import request from '@/utils/request'

export default{

    //得到所有的课程信息
    addCourseInfo(courseInfo){
        return request({
            url: `/eduservice/edu-course/addCourse`,
            method: 'post',
            data:courseInfo
          })
    },

    //得到所有的课程信息
    findAllTeacher(){
        return request({
            url: `/eduservice/edu-teacher/findAll`,
            method: 'get',
      
          })
    },
    //一级分类的数据
    getAllSubject(){
        return request({
            url: `/eduservice/edu-subject/getAllSubject`,
            method: 'get',
          })
    },

}

