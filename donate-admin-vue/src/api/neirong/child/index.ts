import request from '@/utils/request';
import type { ApiResult, PageResult } from '@/api';
import type { Child, ChildParam } from './model';

/**
 * 分页查询
 */
export async function pageChilds(params: ChildParam) {
  const res = await request.get<ApiResult<PageResult<Child>>>(
    '/neirong/child/page',
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
export async function listChilds(params?: ChildParam) {
  const res = await request.get<ApiResult<Child[]>>('/neirong/child', {
    params
  });
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
  debugger;
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 根据id查询
 */
export async function getChild(id: number) {
  const res = await request.get<ApiResult<Child>>('/neirong/child/' + id);
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加
 */
export async function addChild(data: Child) {
  const res = await request.post<ApiResult<unknown>>('/neirong/child', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改
 */
export async function updateChild(data: Child) {
  const res = await request.put<ApiResult<unknown>>('/neirong/child', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除
 */
export async function removeChild(id?: number) {
  const res = await request.delete<ApiResult<unknown>>('/neirong/child/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
