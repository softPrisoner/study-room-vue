import request from '@/utils/request'

// 查询优惠卡列表
export function listCard(query) {
  return request({
    url: '/store/card/list',
    method: 'get',
    params: query
  })
}

// 查询优惠卡详细
export function getCard(id) {
  return request({
    url: '/store/card/' + id,
    method: 'get'
  })
}

// 新增优惠卡
export function addCard(data) {
  return request({
    url: '/store/card',
    method: 'post',
    data: data
  })
}

// 修改优惠卡
export function updateCard(data) {
  return request({
    url: '/store/card',
    method: 'put',
    data: data
  })
}

// 删除优惠卡
export function delCard(id) {
  return request({
    url: '/store/card/' + id,
    method: 'delete'
  })
}
