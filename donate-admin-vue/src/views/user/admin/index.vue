<template>
  <div class="ele-body ele-body-card">
     <a-row>
      <a-col :lg="18" :md="16" :sm="24" :xs="24">
        <a-card
          :bordered="false"
          class="user-info-tabs"
          style="min-height: 602px"
        >
          <a-tabs v-model:active-key="active" size="large">
            <a-tab-pane tab="基本信息" key="info">
              <a-form
                :label-col="{ md: { span: 6 }, sm: { span: 24 } }"
                :wrapper-col="{ md: { span: 18 }, sm: { span: 24 } }"
              >
                <a-form-item label="昵称" v-bind="validateInfos.nickname">
                  <a-input
                    v-model:value="form.nickname"
                    placeholder="请输入昵称"
                    allow-clear
                  />
                </a-form-item>
                <a-form-item label="性别" v-bind="validateInfos.sex">
                  <a-select
                    v-model:value="form.sex"
                    placeholder="请选择性别"
                    allow-clear
                  >
                    <a-select-option value="1">男</a-select-option>
                    <a-select-option value="2">女</a-select-option>
                  </a-select>
                </a-form-item>
                <a-form-item :wrapper-col="{ md: { offset: 6 } }">
                  <a-button type="primary" :loading="loading" @click="save">
                    {{ loading ? '保存中..' : '保存更改' }}
                  </a-button>
                </a-form-item>
              </a-form>
            </a-tab-pane>
          </a-tabs>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts" setup>
  import { ref, reactive, computed } from 'vue';
  import { Form, message } from 'ant-design-vue';
  import { assignObject } from 'ele-admin-pro';
  import { useUserStore } from '@/store/modules/user';
  import { updateUser } from '@/api/system/user';
  import type { User } from '@/api/system/user/model';

  const useForm = Form.useForm;

  const userStore = useUserStore();

  // tab页选中
  const active = ref('info');

  // 保存按钮loading
  const loading = ref(false);

  // 登录用户信息
  const loginUser = computed(() => userStore.info ?? {});

  // 表单数据
  const form = reactive<User>({
    userId: loginUser.value.userId,
    nickname: loginUser.value.nickname,
    sex: loginUser.value.sex,
  });

  // 表单验证规则
  const rules = reactive({
    nickname: [
      {
        required: true,
        message: '请输入昵称',
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
    ]
  });

  const { validate, validateInfos } = useForm(form, rules);

  /* 修改登录用户 */
  const updateLoginUser = (obj: Record<string, any>) => {
    userStore.setInfo(assignObject({ ...loginUser.value }, obj));
  };

  /* 保存更改 */
  const save = () => {
    validate().then(() => {
        loading.value = true;
        updateUser(form).then((msg) => {
          loading.value = false;
          message.success(msg);
          updateLoginUser(form);
        }).catch((e) => {
          loading.value = false;
          message.error(e.message);
        });
    }).catch(() => {

    });
  };

</script>

<script lang="ts">
  export default {
    name: 'UserProfile'
  };
</script>

<style lang="less" scoped>
  /* 用户资料卡片 */
  .user-info-avatar-group {
    margin: 16px 0;
    display: inline-block;
    position: relative;
    cursor: pointer;

    .user-info-avatar-icon {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      color: #fff;
      font-size: 30px;
      display: none;
      z-index: 2;
    }

    &:hover .user-info-avatar-icon {
      display: block;
    }

    &:hover:after {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: rgba(0, 0, 0, 0.3);
    }

    & + h1 {
      margin-bottom: 8px;
    }
  }

  /* 用户信息列表 */
  .user-info-list {
    margin: 52px 0 32px 0;

    .ele-cell + .ele-cell {
      margin-top: 16px;
    }

    & + .ant-divider {
      margin-bottom: 16px;
    }
  }

  /* 用户标签 */
  .user-info-tags {
    margin: 16px 0 4px 0;

    .ant-tag {
      margin: 0 12px 8px 0;
    }
  }

  /* 右侧卡片 */
  .user-info-tabs {
    :deep(.ant-card-body) {
      padding: 0;
    }

    :deep(.ant-tabs-tab) {
      padding-left: 4px;
      padding-right: 4px;
      margin: 0 12px 0 28px !important;
    }

    .ant-form {
      max-width: 580px;
      margin-top: 20px;
      padding: 0 24px;
    }
  }

  /* 用户账号绑定列表 */
  .user-account-list {
    margin-bottom: 16px;

    & > .ele-cell {
      padding: 18px 34px;
    }

    .user-account-icon {
      color: #fff;
      padding: 8px;
      font-size: 26px;
      border-radius: 50%;

      &.anticon-qq {
        background: #3492ed;
      }

      &.anticon-wechat {
        background: #4daf29;
      }

      &.anticon-alipay {
        background: #1476fe;
      }
    }
  }
</style>
