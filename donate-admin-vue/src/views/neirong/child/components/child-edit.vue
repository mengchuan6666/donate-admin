<!-- 角色编辑弹窗 -->
<template>
  <a-modal
    :width="860"
    :visible="visible"
    :confirm-loading="loading"
    :title="isUpdate ? '修改儿童信息' : '添加儿童信息'"
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
      <a-form-item label="儿童名称" v-bind="validateInfos.name">
        <a-input
          allow-clear
          placeholder="请输入儿童名称"
          v-model:value="form.name"
        />
      </a-form-item>
      <a-form-item label="性别" v-bind="validateInfos.sex">
        <sex-select v-model:value="form.sex" />
      </a-form-item>
      <a-form-item label="图片" v-bind="validateInfos.image">
        <a-input
          allow-clear
          placeholder="请上传图片"
          v-model:value="form.image"
          disabled
          style="width: 548px"
        />
        <a-upload :show-upload-list="false" :customRequest="onUpload">
          <a-button type="primary" class="ele-btn-icon">
            <template #icon>
              <upload-outlined />
            </template>
            <span>上传图片</span>
          </a-button>
        </a-upload>
      </a-form-item>
      <a-form-item label="年龄" v-bind="validateInfos.age">
        <a-input
          allow-clear
          placeholder="请输入年龄"
          v-model:value="form.age"
        />
      </a-form-item>
      <a-form-item label="性情" v-bind="validateInfos.temperament">
        <a-textarea
          :rows="3"
          placeholder="请输入性情"
          v-model:value="form.temperament"
        />
      </a-form-item>
      <a-form-item label="爱好" v-bind="validateInfos.hobby">
        <a-textarea
          :rows="3"
          placeholder="请输入爱好"
          v-model:value="form.hobby"
        />
      </a-form-item>
      <a-form-item label="发布日期">
        <a-date-picker
          class="ele-fluid"
          placeholder="请选择发布日期"
          v-model:value="publicDate"
        />
      </a-form-item>
      <a-form-item label="描述" v-bind="validateInfos.description">
        <a-textarea
          :rows="3"
          placeholder="请输入描述"
          v-model:value="form.description"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts" setup>
  import { ref, reactive, watch } from 'vue';
  import { Form, message } from 'ant-design-vue';
  import { assignObject } from 'ele-admin-pro';
  import { addChild, updateChild, uploadFile } from '@/api/neirong/child';
  import type { Child } from '@/api/neirong/child/model';
  import SexSelect from './sex-select.vue';
  import { RuleObject } from 'ant-design-vue/es/form';
  import dayjs from 'dayjs';
  import type { Dayjs } from 'dayjs';

  const useForm = Form.useForm;
  const publicDate = ref<Dayjs>();

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

  // 是否是修改
  const isUpdate = ref(false);

  // 提交状态
  const loading = ref(false);

  // 表单数据
  const form = reactive<Child>({
    id: undefined,
    name: '',
    sex: undefined,
    image: '',
    age: undefined,
    temperament: '',
    hobby: '',
    publicDate: '',
    description: '',
    createUser: '',
    createTime: ''
  });

  // 表单验证规则
  const rules = reactive({
    name: [
      {
        required: true,
        message: '请输入儿童名称',
        type: 'string',
        trigger: 'blur'
      }
    ],
    sex: [
      {
        required: true,
        message: '请选择性别',
        type: 'string',
        trigger: 'blur'
      }
    ],
    image: [
      {
        required: false,
        message: '请上传图片',
        type: 'string',
        trigger: 'blur'
      }
    ],
    age: [
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
    temperament: [
      {
        required: true,
        message: '请输入性情',
        type: 'string',
        trigger: 'blur'
      }
    ],
    hobby: [
      {
        required: true,
        message: '请输入爱好',
        type: 'string',
        trigger: 'blur'
      }
    ],
    description: [
      {
        required: false,
        message: '请输入描述',
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
          ...form,
          publicDate: publicDate.value?.format('YYYY-MM-DD')
        };
        const saveOrUpdate = isUpdate.value ? updateChild : addChild;
        saveOrUpdate(data)
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

  /* 上传 */
  const onUpload = ({ file }) => {
    if (file.size / 1024 / 1024 > 100) {
      message.error('大小不能超过 100MB');
      return false;
    }
    uploadFile(file)
      .then((res) => {
        message.success('上传成功');
        form.image = res;
      })
      .catch((e) => {
        message.error(e.message);
      });
    return false;
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
          if (props.data.publicDate) {
            publicDate.value = dayjs(props.data.publicDate, 'YYYY-MM-DD');
          } else {
            publicDate.value = undefined;
          }
          isUpdate.value = true;
        } else {
          isUpdate.value = false;
        }
      } else {
        resetFields();
        publicDate.value = undefined;
      }
    }
  );
</script>
