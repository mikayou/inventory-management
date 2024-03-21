import request from "../index";

export function getAllCatalogs(){
    return request({
        method:'GET',
        url:'/catalog',
    })
}

export function addCatalog(data){
    return request({
        method:'POST',
        url:'/catalog',
        data
    })
}