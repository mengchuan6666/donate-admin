import request from '@/utils/request';
import type { ApiResult, PageResult } from '@/api';
import type { Donate, DonateParam } from './model';

/**
 * 分页查询
 */
export async function pageDonates(params: DonateParam) {
  const res = await request.get<ApiResult<PageResult<Donate>>>(
    '/neirong/donate/page',
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
export async function listDonates(params?: DonateParam) {
  const res = await request.get<ApiResult<Donate[]>>('/neirong/donate', {
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
export async function getDonate(id: number) {
  const res = await request.get<ApiResult<Donate>>('/neirong/donate/' + id);
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加
 */
export async function addDonate(data: Donate) {
  const res = await request.post<ApiResult<unknown>>('/neirong/donate', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改
 */
export async function updateDonate(data: Donate) {
  const res = await request.put<ApiResult<unknown>>('/neirong/donate', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 审核
 */
export async function audit(data: Donate) {
  const res = await request.post<ApiResult<unknown>>(
    '/neirong/donate/audit',
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
export async function removeDonate(id?: number) {
  const res = await request.delete<ApiResult<unknown>>('/neirong/donate/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
