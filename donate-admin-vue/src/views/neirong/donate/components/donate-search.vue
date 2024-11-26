<!-- 搜索表单 -->
<template>
  <a-form
    :label-col="{ md: { span: 6 }, sm: { span: 24 } }"
    :wrapper-col="{ md: { span: 18 }, sm: { span: 24 } }"
  >
    <a-row>
      <a-col :lg="6" :md="12" :sm="24" :xs="24">
        <a-form-item label="捐赠名称">
          <a-input
            v-model:value.trim="where.donateName"
            placeholder="请输入捐赠名称"
            allow-clear
          />
        </a-form-item>
      </a-col>
      <a-col :lg="6" :md="12" :sm="24" :xs="24">
        <a-form-item label="捐赠人名称">
          <a-input
            v-model:value.trim="where.donatePersonName"
            placeholder="请输入捐赠人名称"
            allow-clear
          />
        </a-form-item>
      </a-col>
      <a-col :lg="6" :md="12" :sm="24" :xs="24">
        <a-form-item label="捐赠人手机">
          <a-input
            v-model:value.trim="where.donatePersonPhone"
            placeholder="请输入捐赠人手机"
            allow-clear
          />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :lg="6" :md="12" :sm="24" :xs="24">
        <a-form-item label="捐赠类型">
          <a-select
            v-model:value="where.donateType"
            placeholder="请选择"
            allow-clear
          >
            <a-select-option value="1">物资</a-select-option>
            <a-select-option value="2">捐款</a-select-option>
          </a-select>
        </a-form-item>
      </a-col>
      <a-col :lg="6" :md="12" :sm="24" :xs="24">
        <a-form-item label="审核状态">
          <a-select
            v-model:value="where.auditStatus"
            placeholder="请选择"
            allow-clear
          >
            <a-select-option value="0">审核不通过</a-select-option>
            <a-select-option value="1">审核通过</a-select-option>
          </a-select>
        </a-form-item>
      </a-col>
      <a-col :lg="6" :md="12" :sm="24" :xs="24">
        <a-form-item class="ele-text-right" :wrapper-col="{ span: 24 }">
          <a-space>
            <a-button type="primary" @click="search">查询</a-button>
            <a-button @click="reset">重置</a-button>
          </a-space>
        </a-form-item>
      </a-col>
    </a-row>
  </a-form>
</template>

<script lang="ts" setup>
  import useSearch from '@/utils/use-search';
  import type { DonateParam } from '@/api/neirong/Donate/model';

  const emit = defineEmits<{
    (e: 'search', where?: DonateParam): void;
  }>();

  // 表单数据
  const { where, resetFields } = useSearch<DonateParam>({
    donateName: '',
    donatePersonName: '',
    donatePersonPhone: '',
    donateType: '',
    auditStatus: ''
  });

  /** 搜索 */
  const search = () => {
    emit('search', where);
  };

  /** 重置 */
  const reset = () => {
    resetFields();
    search();
  };
</script>
