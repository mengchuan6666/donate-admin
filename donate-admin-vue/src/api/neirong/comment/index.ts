import request from '@/utils/request';
import type { ApiResult, PageResult } from '@/api';
import type { Comment, CommentParam } from './model';

/**
 * 分页查询
 */
export async function pageComments(params: CommentParam) {
  const res = await request.get<ApiResult<PageResult<Comment>>>(
    '/system/comment/page',
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
export async function listComments(params?: CommentParam) {
  const res = await request.get<ApiResult<Comment[]>>('/system/comment', {
    params
  });
  if (res.data.code === 0 && res.data.data) {
    return res.data.data;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 添加
 */
export async function addComment(data: Comment) {
	debugger
  const res = await request.post<ApiResult<unknown>>('/system/comment', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 修改
 */
export async function updateComment(data: Comment) {
  const res = await request.put<ApiResult<unknown>>('/system/comment', data);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}

/**
 * 删除
 */
export async function removeComment(id?: number) {
  const res = await request.delete<ApiResult<unknown>>('/system/comment/' + id);
  if (res.data.code === 0) {
    return res.data.message;
  }
  return Promise.reject(new Error(res.data.message));
}
