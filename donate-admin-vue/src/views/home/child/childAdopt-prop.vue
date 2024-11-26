<!-- 弹窗弹窗 -->
<template>
  <a-modal
    :width="860"
    :visible="visible"
    :confirm-loading="loading"
    :title="'领养申请'"
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
      <a-form-item label="领养儿童ID" v-show="false">
        <a-input v-model:value="form.adoptChildId" />
      </a-form-item>
      <a-form-item label="领养人名称" v-bind="validateInfos.adoptName">
        <a-input v-model:value="form.adoptName" />
      </a-form-item>
      <a-form-item label="领养儿童名称" v-bind="validateInfos.adoptChildName">
        <a-input readOnly v-model:value="form.adoptChildName" />
      </a-form-item>
      <a-form-item label="领养人性别" v-bind="validateInfos.adoptSex">
        <sex-select v-model:value="form.adoptSex" />
      </a-form-item>
      <a-form-item label="领养人年龄" v-bind="validateInfos.adoptAge">
        <a-input v-model:value="form.adoptAge" />
      </a-form-item>
      <a-form-item label="领养人手机号" v-bind="validateInfos.adoptPhone">
        <a-input v-model:value="form.adoptPhone" />
      </a-form-item>
      <a-form-item label="领养人地址" v-bind="validateInfos.adoptAddress">
        <a-input v-model:value="form.adoptAddress" />
      </a-form-item>
      <a-form-item label="领养人婚姻" v-bind="validateInfos.marriage">
        <marriage-select v-model:value="form.marriage" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts" setup>
  import { ref, reactive, watch } from 'vue';
  import { Form, message } from 'ant-design-vue';
  import type { ChildAdopt } from '@/api/neirong/childAdopt/model';
  import type { Child } from '@/api/neirong/child/model';
  import { addChildAdopt } from '@/api/neirong/childAdopt';
  import SexSelect from './sex-select.vue';
  import MarriageSelect from './marriage-select.vue';
  import { RuleObject } from 'ant-design-vue/es/form';

  const useForm = Form.useForm;
  const emit = defineEmits<{
    (e: 'done'): void;
    (e: 'update:visible', visible: boolean): void;
  }>();
  const props = defineProps<{
    // 弹窗是否打开
    visible: boolean;
    // 修改回显的数据
    data?: Child;
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
    adoptName: [
      {
        required: true,
        message: '请输入领养人名称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    adoptChildName: [
      {
        required: true,
        message: '请输入领养儿童名称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    adoptSex: [
      {
        required: true,
        message: '请选择领养人性别',
        type: 'string',
        trigger: 'blur'
      }
    ],
    adoptAge: [
      {
        required: true,
        type: 'string',
        trigger: 'blur',
        validator: (_rule: RuleObject, value: string) => {
          return new Promise<void>((resolve, reject) => {
            if (!value) {
              return reject('请输入年龄');
            }
            if (/^\d{1,2}$/.test(value)) {
              return resolve();
            }
            return reject('年龄不正确');
          });
        }
      }
    ],
    adoptPhone: [
      {
        required: true,
        type: 'string',
        trigger: 'blur',
        validator: (_rule: RuleObject, value: string) => {
          return new Promise<void>((resolve, reject) => {
            if (!value) {
              return reject('请输入领养人手机号');
            }
            if (/^1[3456789]\d{9}$/.test(value)) {
              return resolve();
            }
            return reject('领养人手机号不正确');
          });
        }
      }
    ],
    adoptAddress: [
      {
        required: true,
        message: '请输入领养人地址',
        type: 'string',
        trigger: 'blur'
      }
    ],
    marriage: [
      {
        required: true,
        message: '请选择领养人婚姻',
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
        addChildAdopt(data)
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
          form.adoptChildId = props.data.id;
          form.adoptChildName = props.data.name;
        }
      } else {
        resetFields();
      }
    }
  );
</script>
