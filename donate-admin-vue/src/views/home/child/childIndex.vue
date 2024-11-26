<template>
  <Header />
  <div class="ele-admin-main" style="background-color: #f1f1f1">
    <div class="ele-admin-body">
      <div class="ele-admin-content">
        <div class="ele-admin-content-view">
          <div class="ele-body" style="margin: 0px 150px;">
            <a-card style="width: 100%">
              <a-row type="flex" style="margin-bottom: 20px">
                <a-col flex="auto">
                  <a-card-meta>
                    <template #title>
                      <a-tag color="pink"
                        >发布时间：{{ childData.publicDate }}</a-tag
                      >
                      <a-tag color="red"
                        >发布人：{{ childData.createUser }}</a-tag
                      >
                    </template>
                  </a-card-meta>
                </a-col>
              </a-row>
              <a-divider />
              <a-typography style="margin-top: 20px">
                <a-row type="flex" style="margin-bottom: 20px">
                  <a-col :lg="6" :md="12" :sm="24" :xs="24">
                    姓名：{{ childData.name }}
                  </a-col>
                  <a-col :lg="6" :md="12" :sm="24" :xs="24">
                    性别：{{ childData.sexName }}
                  </a-col>
                  <a-col :lg="6" :md="12" :sm="24" :xs="24">
                    年龄：{{ childData.age }}
                  </a-col>
                </a-row>
                <a-row type="flex" style="margin-bottom: 20px">
                  <a-col :lg="6" :md="12" :sm="24" :xs="24">
                    性情：{{ childData.temperament }}
                  </a-col>
                  <a-col :lg="6" :md="12" :sm="24" :xs="24">
                    爱好：{{ childData.hobby }}
                  </a-col>
                  <a-col :lg="6" :md="12" :sm="24" :xs="24">
                    描述：{{ childData.description }}
                  </a-col>
                </a-row>
                <a-row style="margin-bottom: 20px">
                  <a-col :lg="6" :md="12" :sm="24" :xs="24">
                    <a-form-item
                      class="ele-text-right"
                      :wrapper-col="{ span: 24 }"
                    >

                    </a-form-item>
                  </a-col>
                </a-row>
                <a-image :src="childData.image" />
              </a-typography>
            </a-card>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 编辑弹窗 -->
  <ChildAdoptProp v-model:visible="showProp" :data="current" />
</template>

<script lang="ts" setup>
  import { ref, onMounted } from 'vue';
  import { getChild } from '@/api/neirong/child';
  import type { Child } from '@/api/neirong/child/model';
  import ChildAdoptProp from './childAdopt-prop.vue';
  import Header from '../header.vue';
  import { useRoute } from 'vue-router';

  const childData = ref({
    id: '',
    name: '',
    sexName: '',
    age: '',
    temperament: '',
    hobby: '',
    description: '',
    publicDate: '',
    createUser: '',
    image: '',
    isAdopt: ''
  });

  // 初始化
  onMounted(() => {
    let route = useRoute();
    getChild(route.params.id).then((res) => {
      childData.value = res;
    });
  });

  // 是否显示弹窗
  const showProp = ref(false);

  // 当前数据
  const current = ref<Child>();

  const applyAdopt = (row?: Child) => {
    current.value = row;
    showProp.value = true;
  };
</script>

<style scoped>
  .header-info-avatar img {
    width: 150px;
    height: 150px;
    border-radius: 4px;
  }
  bbs-follow-info {
    font-size: 12px;
    line-height: 30px;
    margin-left: 20px;
    float: left;
  }
  .bbs-follow-info span {
    margin-right: 10px;
  }
  .bbs-follow-info span m {
    color: #f00;
  }
</style>
