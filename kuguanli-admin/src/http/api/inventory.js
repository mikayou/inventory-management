import request from "../index";

export function getAllInventoryOfWarehouse(params){
    return request({
        method:'GET',
        url:'/inventory',
        params
    })
}