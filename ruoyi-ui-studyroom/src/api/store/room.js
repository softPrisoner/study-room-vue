import request from '@/utils/request'

// 查询自习室列表
export function listRoom(query) {
  return request({
    url: '/store/room/list',
    method: 'get',
    params: query
  })
}

// 查询自习室详细
export function getRoom(roomId) {
  return request({
    url: '/store/room/' + roomId,
    method: 'get'
  })
}

// 新增自习室
export function addRoom(data) {
  return request({
    url: '/store/room',
    method: 'post',
    data: data
  })
}

// 修改自习室
export function updateRoom(data) {
  return request({
    url: '/store/room',
    method: 'put',
    data: data
  })
}

// 删除自习室
export function delRoom(roomId) {
  return request({
    url: '/store/room/' + roomId,
    method: 'delete'
  })
}
