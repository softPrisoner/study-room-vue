import request from '@/utils/request'

// 查询座位-记录列表
export function listSeatRecord(query) {
  return request({
    url: '/studyroom/seatRecord/list',
    method: 'get',
    params: query
  })
}

// 查询座位-记录详细
export function getSeatRecord(id) {
  return request({
    url: '/studyroom/seatRecord/' + id,
    method: 'get'
  })
}

// 新增座位-记录
export function addSeatRecord(data) {
  return request({
    url: '/studyroom/seatRecord',
    method: 'post',
    data: data
  })
}

// 修改座位-记录
export function updateSeatRecord(data) {
  return request({
    url: '/studyroom/seatRecord',
    method: 'put',
    data: data
  })
}

// 删除座位-记录
export function delSeatRecord(id) {
  return request({
    url: '/studyroom/seatRecord/' + id,
    method: 'delete'
  })
}
