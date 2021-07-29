import request from '@/utils/request'

export default {
    showChart(searchObj) {
        return request({
            url: `/staservice/statistics-daily/show-chart/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
            method: 'get'
        })
    }
}