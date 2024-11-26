import type { PageParam } from '@/api';

/**
 *
 */
export interface ChildAdopt {
  id?: number;
  adoptName?: string;
  adoptChildName?: string;
  adoptSex?: string;
  adoptSexName?: string;
  adoptPhone?: string;
  adoptAge?: number;
  adoptAddress?: string;
  marriage?: string;
  adoptChildId?: string;
  createTime?: string;
  createUser?: string;
  createUserId?: number;
  auditStatus?: string;
  auditTime?: string;
  auditUser?: string;
}

/**
 * 搜索条件
 */
export interface ChildAdoptParam extends PageParam {
  adoptName?: string;
  adoptSex?: string;
  auditStatus?: string;
  adoptPhone?: string;
  adoptAddress?: string;
  createUserId?: number;
}
