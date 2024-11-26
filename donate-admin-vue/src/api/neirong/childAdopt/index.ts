import request from '@/utils/request';
import type { ApiResult, PageResult } from '@/api';
import type { ChildAdopt, ChildAdoptParam } from './model';

/**
 * 分页查询
 */
export async function pageChildAdopts(params: ChildAdoptParam) {
  const res = await request.get<ApiResult<PageResult<ChildAdopt>>>(
    '/neirong/adopt/page',
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
export async function listChildAdopts(params?: ChildAdoptParam) {
  const res = await request.get<ApiResult<ChildAdopt[]>>('/neirong/adopt', {
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
export async function getChildAdopt(id: number) {
  const res = await request.get<ApiResult<ChildAdopt>>('/neirong/adopt/' + id);
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加
 */
export async function addChildAdopt(data: ChildAdopt) {
  const res = await request.post<ApiResult<unknown>>('/neirong/adopt', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改
 */
export async function updateChildAdopt(data: ChildAdopt) {
  const res = await request.put<ApiResult<unknown>>('/neirong/adopt', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 审核
 */
export async function audit(data: ChildAdopt) {
  const res = await request.post<ApiResult<unknown>>(
    '/neirong/adopt/audit',
    data
  );
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除
 */
export async function removeChildAdopt(id?: number) {
  const res = await request.delete<ApiResult<unknown>>('/neirong/adopt/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
