import request from "../index";

export function addCustomer(data){
    return request({
        method:'POST',
        url:'/customer',
        data
    })
}