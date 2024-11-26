import request from '@/utils/request';
import type { ApiResult, PageResult } from '@/api';
import type { News, NewsParam } from './model';

/**
 * 分页查询
 */
export async function pageNewss(params: NewsParam) {
  const res = await request.get<ApiResult<PageResult<News>>>(
    '/system/news/page',
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
export async function listNewss(params?: NewsParam) {
  const res = await request.get<ApiResult<News[]>>('/system/news', {
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
export async function getNews(id: number) {
  const res = await request.get<ApiResult<News>>('/system/news/' + id);
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 上传文件
 */
export async function uploadFile(file: File) {
  const formData = new FormData();
  formData.append('file', file);
  const res = await request.post<ApiResult<unknown>>(
    '/api/file/upload',
    formData
  );
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加
 */
export async function addNews(data: News) {
  const res = await request.post<ApiResult<unknown>>('/system/news', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改
 */
export async function updateNews(data: News) {
  const res = await request.put<ApiResult<unknown>>('/system/news', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除
 */
export async function removeNews(id?: number) {
  const res = await request.delete<ApiResult<unknown>>('/system/news/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
