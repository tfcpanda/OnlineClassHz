import request from '@/utils/request'
export default {
    //登录
    submitLoginFront(userInfo) {
        return request({
            url: `/ucenterservice/ucenter-member/login`,
            method: 'post',
            data: userInfo
        })
    },
    //根据token获取用户信息
    getLoginInfo() {
        return request({
            url: `/ucenterservice/ucenter-member/auth/getLoginInfo`,
            method: 'get',
            // headers: {'token': cookie.get('guli_token')}
        })
        //headers: {'token': cookie.get('guli_token')}
    }
}