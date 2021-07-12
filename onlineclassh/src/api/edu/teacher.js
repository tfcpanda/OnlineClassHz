import request from '@/utils/request'

export default{

    //讲师列表
    //当前页，每页的记录数，条件的对象
    getTeacherListPage(current,limit,teacherQuery){
        return request({
            //url: '/table/list',
            url: `/eduservice/edu-teacher/pageTeacher/${current}/${limit}`,
            method: 'post',
            //条件的对象
            //data表示把对象转换成Json传递到接口中
            data:teacherQuery
          })
    },

    //根据ID删除教师
    deleteTeacherId(id){
        console.log("进入方法2")
        return request({
            
            //url: '/table/list',
            url: `/eduservice/edu-teacher/${id}`,
            method: 'delete',
     
          })
    },

    //添加讲师
    addTeacherId(teacher){

        return request({
            //url: '/table/list',
            url: `/eduservice/edu-teacher/getTeacher/${id}`,
            method: 'get',
            data:teacher
          })
    },

    //根据ID查询
    getTeacherInfo(id){
        return request({
            //url: '/table/list',
            url: `/eduservice/edu-teacher/getTeacher/${id}`,
            method: 'get',
          })
    },

    //修改讲师
    //updateTeacher()
}

