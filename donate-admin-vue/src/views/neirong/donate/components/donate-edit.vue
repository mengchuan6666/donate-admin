<!-- 捐赠弹窗 -->
<template>
  <a-modal
    :width="860"
    :visible="visible"
    :confirm-loading="loading"
    :title="'物资捐赠'"
    :body-style="{ paddingBottom: '8px' }"
    @update:visible="updateVisible"
    @ok="save"
  >
    <a-form
      :label-col="{ md: { span: 3 }, sm: { span: 24 } }"
      :wrapper-col="{ md: { span: 21 }, sm: { span: 24 } }"
    >
      <!-- 隐藏字段 -->
      <a-form-item v-show="false">
        <a-input v-model:value="form.id" />
      </a-form-item>
      <a-form-item label="捐赠名称" v-bind="validateInfos.donateName">
        <a-input readOnly v-model:value="form.donateName" />
      </a-form-item>
      <a-form-item label="捐赠类型" v-bind="validateInfos.donateType">
        <donate-type-select
          disabled="disabled"
          v-model:value="form.donateType"
        />
      </a-form-item>
      <a-form-item label="捐赠数量" v-bind="validateInfos.donateNumber">
        <a-input readOnly v-model:value="form.donateNumber" />
      </a-form-item>
      <a-form-item label="捐赠人账号" v-bind="validateInfos.donateAccount">
        <a-input readOnly v-model:value="form.donateAccount" />
      </a-form-item>
      <a-form-item label="捐赠人名称" v-bind="validateInfos.donatePersonName">
        <a-input readOnly v-model:value="form.donatePersonName" />
      </a-form-item>
      <a-form-item label="捐赠人手机" v-bind="validateInfos.donatePersonPhone">
        <a-input readOnly v-model:value="form.donatePersonPhone" />
      </a-form-item>
      <a-form-item label="捐赠物品描述" v-bind="validateInfos.donateGoodsDesc">
        <a-input readOnly v-model:value="form.donateGoodsDesc" />
      </a-form-item>
      <a-form-item label="捐赠时间" v-bind="validateInfos.donateTime">
        <a-input readOnly v-model:value="form.donateTime" />
      </a-form-item>
      <a-form-item label="审核状态" v-bind="validateInfos.auditStatus">
        <audit-status-select v-model:value="form.auditStatus" />
      </a-form-item>
      <a-form-item label="审核回复" v-bind="validateInfos.auditReply">
        <a-input v-model:value="form.auditReply" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts" setup>
  import { ref, reactive, watch } from 'vue';
  import { Form, message } from 'ant-design-vue';
  import { audit } from '@/api/neirong/donate';
  import type { Donate } from '@/api/neirong/donate/model';
  import DonateTypeSelect from './donate-type-select.vue';
  import AuditStatusSelect from './audit-status-select.vue';
  import { assignObject } from 'ele-admin-pro';

  const useForm = Form.useForm;
  const emit = defineEmits<{
    (e: 'done'): void;
    (e: 'update:visible', visible: boolean): void;
  }>();

  const props = defineProps<{
    // 弹窗是否打开
    visible: boolean;
    // 修改回显的数据
    data?: Donate;
  }>();

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const form = reactive<Donate>({
    id: undefined,
    donateName: '',
    donateType: '',
    donateNumber: undefined,
    donateAccount: '',
    donatePersonName: '',
    donatePersonPhone: '',
    donateGoodsDesc: '',
    donateTime: '',
    auditStatus: '',
    auditReply: ''
  });

  // 表单验证规则
  const rules = reactive({
    auditStatus: [
      {
        required: false,
        message: '请选择审核状态',
        type: 'string',
        trigger: 'blur'
      }
    ]
  });

  const { resetFields, validate, validateInfos } = useForm(form, rules);

  /* 保存编辑 */
  const save = () => {
    validate()
      .then(() => {
        loading.value = true;
        const data = {
          ...form
        };
        audit(data)
          .then((msg) => {
            loading.value = false;
            message.success(msg);
            updateVisible(false);
            emit('done');
          })
          .catch((e) => {
            loading.value = false;
            message.error(e.message);
          });
      })
      .catch(() => {});
  };
  /* 更新visible */
  const updateVisible = (value: boolean) => {
    emit('update:visible', value);
  };
  watch(
    () => props.visible,
    (visible) => {
      if (visible) {
        if (props.data) {
          assignObject(form, props.data);
        }
      } else {
        resetFields();
      }
    }
  );
</script>
