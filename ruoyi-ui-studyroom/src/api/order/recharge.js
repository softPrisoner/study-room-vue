import request from '@/utils/request'

// 查询充值订单列表
export function listRecharge(query) {
  return request({
    url: '/order/recharge/list',
    method: 'get',
    params: query
  })
}

// 查询充值订单详细
export function getRecharge(id) {
  return request({
    url: '/order/recharge/' + id,
    method: 'get'
  })
}

// 新增充值订单
export function addRecharge(data) {
  return request({
    url: '/order/recharge',
    method: 'post',
    data: data
  })
}

// 修改充值订单
export function updateRecharge(data) {
  return request({
    url: '/order/recharge',
    method: 'put',
    data: data
  })
}

// 删除充值订单
export function delRecharge(id) {
  return request({
    url: '/order/recharge/' + id,
    method: 'delete'
  })
}
