import request from '@/utils/request'

export default {
    createStatistics(day) {
        return request({
            url: `/staservice/statistics-daily/${day}`,
            method: 'post'
        })
    }
}