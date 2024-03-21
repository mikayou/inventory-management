import request from "../index";

export function addSupplier(data){
    return request({
        method:'POST',
        url:'/supplier',
        data
    })
}
export function getAllSuppliers(){
    return request({
        method:'GET',
        url:'/supplier',
    })
}