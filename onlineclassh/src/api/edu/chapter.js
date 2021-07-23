import request from '@/utils/request'

export default {

  //得到所有的章节信息
  getAllChapter(courseId) {
    return request({
      url: `/eduservice/edu-chapter/getAllCourse/` + courseId,
      method: 'get',

    })
  },

  //添加章节
  addChapter(chapter) {
    return request({
      url: `/eduservice/edu-chapter/addChapter`,
      method: 'post',
      data: chapter
    })
  },

  //根据id查询章节
  getChapterById(chapterId) {
    return request({
      url: `/eduservice/edu-chapter/getChapterInfo/${chapterId}`,
      method: 'get',

    })
  },

  //修改章节
  updateChapter(chapter) {
    return request({
      url: `/eduservice/edu-chapter/updateChapter`,
      method: 'post',
      data: chapter
    })
  },

  //删除章节
  delChapterById(chapterId) {
    return request({
      url: `/eduservice/edu-chapter/${chapterId}`,
      method: 'delete',

    })
  },

   //上传视频
   delChapterById(chapterId) {
    return request({
      url: `/eduservice/edu-chapter/${chapterId}`,
      method: 'delete',

    })
  },
}

