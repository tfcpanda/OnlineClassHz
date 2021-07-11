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
    }
}

