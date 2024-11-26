<template>
  <Header />
  <div class="ele-admin-main" style="background-color: #f1f1f1">
    <div class="ele-admin-body">
      <div class="ele-admin-content">
        <div class="ele-admin-content-view">
          <div class="ele-body" style="margin: 0px 150px;">
            <!-- 首页轮播图 -->
            <a-carousel autoplay>
              <div
                ><img
                  style="width: 100%; height: 400px"
                  src="../../assets/image/banner-001.png"
              /></div>
              <div
                ><img
                  style="width: 100%; height: 400px"
                  src="../../assets/image/banner-002.png"
              /></div>
              <div
                ><img
                  style="width: 100%; height: 400px"
                  src="../../assets/image/banner-003.png"
              /></div>
            </a-carousel>
            <div style="margin-top: 10px">
              <a-card title="儿童信息">
                <template #extra>
                  <a-space>
                    <a-input
                      v-model:value="name"
                      placeholder="请输入儿童名称"
                      style="width: 400px"
                      allowClear
                    />
                    <a-button type="primary" @click="search">查询</a-button>
                  </a-space>
                </template>
                <a-list
                  :grid="{ column: 2 }"
                  item-layout="vertical"
                  size="large"
                  :pagination="pagination"
                  :data-source="dataList"
                  style="margin: -10px"
                >
                  <template #renderItem="{ item }">
                    <router-link :to="'/child/' + item.id">
                      <a-card hoverable style="margin: 10px">
                        <template #cover>
                          <img
                            height="240"
                            :src="item.image"
                            style="padding: 15px 15px 0px 15px"
                          />
                        </template>
                        儿童名称：{{ item.name }}
                      </a-card>
                    </router-link>
                  </template>
                </a-list>
              </a-card>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
  import { onMounted, ref } from 'vue';
  import Header from './header.vue';
  import { pageChilds } from '@/api/neirong/child';
  import type { Child } from '@/api/neirong/child/model';
  const name = ref<string>('');

  // 查询参数
  const queryParam = ref({
    page: 1,
    limit: 8,
    sort: 'createTime',
    order: 'desc'
  });

  const dataList = ref<Child[]>([]);

  onMounted(() => {
    queryNewsPage(queryParam.value);
  });

  const queryNewsPage = (queryParam: any) => {
    pageChilds(queryParam).then((res) => {
      dataList.value = res.list;
      pagination.value.total = res.count;
    });
  };

  // 分页配置
  const pagination = ref({
    total: 0,
    current: 1,
    pageSize: 8,
    // 改变每页数量时更新显示
    onChange: (current: any, size: any) => {
      // 分页点击后更新
      pagination.value.current = current;
      pagination.value.pageSize = size;
      // 赋值分页参数
      queryParam.value.page = current;
      queryParam.value.limit = size;
      pageChilds(queryParam).then((res) => {
        dataList.value = res.list;
        pagination.value.total = res.count;
      });
    }
  });

  const search = () => {
    queryParam.value.name = name.value;
    pageChilds(queryParam.value).then((res) => {
      dataList.value = res.list;
      pagination.value.total = res.count;
    });
  };
</script>

<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: 'ChildIndex'
  });
</script>
