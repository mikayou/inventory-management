import request from "../index";

export function addWarehouse(data){
    return request({
        method:'POST',
        url:'/warehouse',
        data:data
    })
}

export function getAllWarehouses(){
    return request({
        method:'GET',
        url:'/warehouse',
    })
}