<template>
  <Header />
  <div class="ele-admin-main" style="background-color: #f1f1f1">
    <div class="ele-admin-body">
      <div class="ele-admin-content">
        <div class="ele-admin-content-view">
          <div class="ele-body" style="margin: 0px 150px;">
            <div
              style="
                margin: 10px 0px;
                padding: 20px 0px 20px 0px;
                text-align: center;
              "
            >
              <div>
                <a-avatar
                  :size="100"
                  :src="
                    loginUser.avatar == null
                      ? '/src/assets/user.png'
                      : loginUser.avatar
                  "
                />
              </div>
              <div>
                <a-typography>
                  <a-typography-title :level="3">{{
                    loginUser.nickname
                  }}</a-typography-title>
                </a-typography>
                <a-space :size="10">
                  <a-tag color="red">{{ loginUser.type }}</a-tag>
                </a-space>
              </div>
            </div>
            <div
              style="
                margin: -10px 0px 20px 0px;
                padding: 0px 20px 20px 20px;
                background-color: #fff;
              "
            >
              <a-tabs v-model:activeKey="activeKey" size="large" centered>
                <a-tab-pane key="ziliao" tab="个人资料">
                  <a-descriptions
                    title="基本信息"
                    :column="3"
                    size="default"
                    bordered
                    layout="vertical"
                  >
                    <template #extra>
                      <a-button type="primary" @click="userEdit(loginUser)"
                        >编辑</a-button
                      >
                    </template>
                    <a-descriptions-item label="用户ID">{{
                      loginUser.userId
                    }}</a-descriptions-item>
                    <a-descriptions-item label="注册时间">{{
                      loginUser.createTime
                    }}</a-descriptions-item>
                    <a-descriptions-item label="账号">{{
                      loginUser.username
                    }}</a-descriptions-item>
                    <a-descriptions-item label="昵称">{{
                      loginUser.nickname
                    }}</a-descriptions-item>
                    <a-descriptions-item label="性别">{{
                      loginUser.sex == 1 ? '男' : '女'
                    }}</a-descriptions-item>
                    <a-descriptions-item label="手机">{{
                      loginUser.phone
                    }}</a-descriptions-item>
                    <a-descriptions-item label="邮箱">{{
                      loginUser.email
                    }}</a-descriptions-item>
                  </a-descriptions>
                </a-tab-pane>
              </a-tabs>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 编辑弹窗 -->
  <userInfo v-model:visible="showEdit" :data="current" @done="reload" />
</template>

<script lang="ts" setup>
  import { computed, ref } from 'vue';
  import Header from './header.vue';
  import { useUserStore } from '@/store/modules/user';
  import userInfo from './userInfo/user-edit.vue';
  import type { User } from '@/api/system/user/model';
  import { useRouter } from 'vue-router';

  const activeKey = ref('ziliao');
  // 获取用户存储
  const userStore = useUserStore();
  // 当前用户信息
  const loginUser = computed(() => userStore.info ?? {});
  const userEdit = (loginUser: User) => {
    showEdit.value = true;
    current.value = loginUser;
  };

  const router = useRouter();

  // 当前编辑数据
  const current = ref<User>();

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  const reload = () => {
    router.go(0);
  };
</script>

<style scoped></style>
