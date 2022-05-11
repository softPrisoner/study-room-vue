import request from '@/utils/request'

// 查询余额
列表
export function listBalance(query) {
  return request({
    url: '/studyroom/balance/list',
    method: 'get',
    params: query
  })
}

// 查询余额
详细
export function getBalance(id) {
  return request({
    url: '/studyroom/balance/' + id,
    method: 'get'
  })
}

// 新增余额

export function addBalance(data) {
  return request({
    url: '/studyroom/balance',
    method: 'post',
    data: data
  })
}

// 修改余额

export function updateBalance(data) {
  return request({
    url: '/studyroom/balance',
    method: 'put',
    data: data
  })
}

// 删除余额

export function delBalance(id) {
  return request({
    url: '/studyroom/balance/' + id,
    method: 'delete'
  })
}
