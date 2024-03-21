import request from "../index";

export function addPurchaseOrder(data){
    return request({
        method:'POST',
        url:'/rms/purchase-order',
        data
    })
}


export function getPurchaseOrdersByStatus(params){
    return request({
        method:'GET',
        url:'/rms/purchase-order',
        params
    })
}

export function updatePurchaseOrderStatus(id,params){
    return request({
        method:'PATCH',
        url:'/rms/purchase-order/'+id,
        params
    })
}
