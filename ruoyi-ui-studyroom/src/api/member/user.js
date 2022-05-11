import request from '@/utils/request'

// 查询会员列表
export function listUser(query) {
  return request({
    url: '/member/user/list',
    method: 'get',
    params: query
  })
}

// 查询会员详细
export function getUser(userId) {
  return request({
    url: '/member/user/' + userId,
    method: 'get'
  })
}

// 新增会员
export function addUser(data) {
  return request({
    url: '/member/user',
    method: 'post',
    data: data
  })
}

// 修改会员
export function updateUser(data) {
  return request({
    url: '/member/user',
    method: 'put',
    data: data
  })
}

// 删除会员
export function delUser(userId) {
  return request({
    url: '/member/user/' + userId,
    method: 'delete'
  })
}
