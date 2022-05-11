import request from '@/utils/request'

// 查询购卡管理列表
export function listOrderCard(query) {
  return request({
    url: '/order/orderCard/list',
    method: 'get',
    params: query
  })
}

// 查询购卡管理详细
export function getOrderCard(id) {
  return request({
    url: '/order/orderCard/' + id,
    method: 'get'
  })
}

// 新增购卡管理
export function addOrderCard(data) {
  return request({
    url: '/order/orderCard',
    method: 'post',
    data: data
  })
}

// 修改购卡管理
export function updateOrderCard(data) {
  return request({
    url: '/order/orderCard',
    method: 'put',
    data: data
  })
}

// 删除购卡管理
export function delOrderCard(id) {
  return request({
    url: '/order/orderCard/' + id,
    method: 'delete'
  })
}
