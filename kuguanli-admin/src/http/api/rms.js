import request from "../index";

export function receving(data){
    return request({
        method:'POST',
        url:'/rms/receiving',
        data
    })
}
