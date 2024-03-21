import request from "../index";

export function getAllProducts(){
    return request({
        method:'GET',
        url:'/product',
    })
}

export function addProduct(data){
    return request({
        method:'POST',
        url:'/product',
        data
    })
}
