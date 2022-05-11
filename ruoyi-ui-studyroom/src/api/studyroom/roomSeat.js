import request from '@/utils/request'

// 查询自习室-座位关联列表
export function listRoomSeat(query) {
  return request({
    url: '/studyroom/roomSeat/list',
    method: 'get',
    params: query
  })
}

// 查询自习室-座位关联详细
export function getRoomSeat(id) {
  return request({
    url: '/studyroom/roomSeat/' + id,
    method: 'get'
  })
}

// 新增自习室-座位关联
export function addRoomSeat(data) {
  return request({
    url: '/studyroom/roomSeat',
    method: 'post',
    data: data
  })
}

// 修改自习室-座位关联
export function updateRoomSeat(data) {
  return request({
    url: '/studyroom/roomSeat',
    method: 'put',
    data: data
  })
}

// 删除自习室-座位关联
export function delRoomSeat(id) {
  return request({
    url: '/studyroom/roomSeat/' + id,
    method: 'delete'
  })
}
