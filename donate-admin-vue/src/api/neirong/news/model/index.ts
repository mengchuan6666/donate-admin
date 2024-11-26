import type { PageParam } from '@/api';

/**
 * 
 */
export interface News {
  id?: number;
  image?: string;
  name?: string;
  description?: string;
  content?: string;
  createTime?: string;
  createUser?: string;
}

/**
 * 搜索条件
 */
export interface NewsParam extends PageParam {
  name?: string;
}
