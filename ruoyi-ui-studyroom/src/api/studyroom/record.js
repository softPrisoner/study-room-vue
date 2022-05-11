import request from '@/utils/request'

// 查询学习记录列表
export function listRecord(query) {
  return request({
    url: '/studyroom/record/list',
    method: 'get',
    params: query
  })
}

// 查询学习记录详细
export function getRecord(id) {
  return request({
    url: '/studyroom/record/' + id,
    method: 'get'
  })
}

// 新增学习记录
export function addRecord(data) {
  return request({
    url: '/studyroom/record',
    method: 'post',
    data: data
  })
}

// 修改学习记录
export function updateRecord(data) {
  return request({
    url: '/studyroom/record',
    method: 'put',
    data: data
  })
}

// 删除学习记录
export function delRecord(id) {
  return request({
    url: '/studyroom/record/' + id,
    method: 'delete'
  })
}
