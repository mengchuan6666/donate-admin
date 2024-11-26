import type { PageParam } from '@/api';

/**
 *
 */
export interface Child {
  id?: number;
  name?: string;
  sex?: string;
  sexName?: string;
  image?: string;
  age?: number;
  temperament?: string;
  hobby?: string;
  publicDate?: string;
  description?: string;
  createTime?: string;
  createUser?: string;
  isAdopt?: number;
  isAdoptName?: string;
}

/**
 * 搜索条件
 */
export interface ChildParam extends PageParam {
  name?: string;
  sex?: string;
}
