import request from "../index";

export function login(data){
    return request({
        method:'POST',
        url:'/user/login',
        data:data
    })
}

export function isLogin(){
    return request({
        method:'GET',
        url:'/user/isLogin',
    })
}