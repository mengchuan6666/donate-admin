<!-- 审批弹窗弹窗 -->
<template>
  <a-modal
    :width="860"
    :visible="visible"
    :confirm-loading="loading"
    :title="'领养审批'"
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
      <a-form-item label="领养人名称" v-bind="validateInfos.adoptName">
        <a-input readOnly v-model:value="form.adoptName" />
      </a-form-item>
      <a-form-item label="领养儿童名称" v-bind="validateInfos.adoptChildName">
        <a-input readOnly v-model:value="form.adoptChildName" />
      </a-form-item>
      <a-form-item label="领养人性别" v-bind="validateInfos.adoptSex">
        <sex-select disabled="disabled" v-model:value="form.adoptSex" />
      </a-form-item>
      <a-form-item label="领养人年龄" v-bind="validateInfos.adoptAge">
        <a-input readOnly v-model:value="form.adoptAge" />
      </a-form-item>
      <a-form-item label="领养人手机号" v-bind="validateInfos.adoptPhone">
        <a-input readOnly v-model:value="form.adoptPhone" />
      </a-form-item>
      <a-form-item label="领养人地址" v-bind="validateInfos.adoptAddress">
        <a-input readOnly v-model:value="form.adoptAddress" />
      </a-form-item>
      <a-form-item label="领养人婚姻" v-bind="validateInfos.marriage">
        <marriage-select disabled="disabled" v-model:value="form.marriage" />
      </a-form-item>
      <a-form-item label="创建时间" v-bind="validateInfos.createTime">
        <a-input readOnly v-model:value="form.createTime" />
      </a-form-item>
      <a-form-item label="创建人" v-bind="validateInfos.createUser">
        <a-input readOnly v-model:value="form.createUser" />
      </a-form-item>
      <a-form-item label="审核状态" v-bind="validateInfos.auditStatus">
        <audit-status-select v-model:value="form.auditStatus" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts" setup>
  import { ref, reactive, watch } from 'vue';
  import { Form, message } from 'ant-design-vue';
  import { audit } from '@/api/neirong/childAdopt';
  import type { ChildAdopt } from '@/api/neirong/childAdopt/model';
  import SexSelect from './sex-select.vue';
  import MarriageSelect from './marriage-select.vue';
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
    data?: ChildAdopt;
  }>();

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const form = reactive<ChildAdopt>({
    id: undefined,
    adoptName: '',
    adoptChildName: '',
    adoptSex: '',
    adoptSexName: '',
    adoptPhone: '',
    adoptAge: undefined,
    adoptAddress: '',
    marriage: '',
    adoptChildId: '',
    auditStatus: '',
    createTime: '',
    createUser: ''
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
