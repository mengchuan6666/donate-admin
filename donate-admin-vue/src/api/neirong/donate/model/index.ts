import type { PageParam } from '@/api';

/**
 *
 */
export interface Donate {
  id?: number;
  donateName?: string;
  donateType?: string;
  donateNumber?: number;
  donateAccount?: string;
  donatePersonName?: string;
  donatePersonPhone?: string;
  donateGoodsDesc?: string;
  donateTime?: string;
  createTime?: string;
  createUser?: string;
  auditStatus?: string;
  auditReply?: string;
  auditTime?: string;
  auditUser?: string;
}

/**
 * 搜索条件
 */
export interface DonateParam extends PageParam {
  donateName?: string;
  donatePersonName?: string;
  donatePersonPhone?: string;
  donateType?: string;
  auditStatus?: string;
}
