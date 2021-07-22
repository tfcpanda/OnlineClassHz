import request from '@/utils/request'

export default {

    //添加章节
    addVideo(video) {
        return request({
            url: `/eduservice/edu-video/addVideo`,
            method: 'post',
            data: video
        })
    },

    //删除小节
    delVideoById(videoId) {
        return request({
            url: `/eduservice/edu-video/${videoId}`,
            method: 'delete',

        })
    },

}

