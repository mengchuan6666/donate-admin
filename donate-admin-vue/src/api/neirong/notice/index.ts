import request from '@/utils/request';
import type { ApiResult, PageResult } from '@/api';
import type { Notice, NoticeParam } from './model';

/**
 * 分页查询
 */
export async function pageNotices(params: NoticeParam) {
  const res = await request.get<ApiResult<PageResult<Notice>>>(
    '/system/notice/page',
    { params }
  );
  if (res.data.code === 0) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 查询列表
 */
export async function listNotices(params?: NoticeParam) {
  const res = await request.get<ApiResult<Notice[]>>('/system/notice', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 根据id查询
 */
export async function getNotice(id: number) {
  const res = await request.get<ApiResult<Notice>>('/system/notice/' + id);
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加
 */
export async function addNotice(data: Notice) {
  const res = await request.post<ApiResult<unknown>>('/system/notice', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改
 */
export async function updateNotice(data: Notice) {
  const res = await request.put<ApiResult<unknown>>('/system/notice', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 发布
 */
export async function fabuNotice(id?: number) {
  const res = await request.put<ApiResult<unknown>>('/system/notice/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除
 */
export async function removeNotice(id?: number) {
  const res = await request.delete<ApiResult<unknown>>('/system/notice/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
