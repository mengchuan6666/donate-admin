import type { PageParam } from '@/api';

/**
 * 
 */
export interface Comment {
  id?: number;
  userId?: number;
  sid?: string;
  comment?: string;
  name?: string;
  createTime?: string;
  createUser?: string;
}

/**
 * 搜索条件
 */
export interface CommentParam extends PageParam {
  name?: string;
  userId?: number;
  sid?: string;
}
