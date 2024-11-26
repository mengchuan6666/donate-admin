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
            <div style="margin-top: 20px">
              <a-list :grid="{ gutter: 16, column: 4 }" :data-source="dataList">
                <template #renderItem="{ item }">
                  <a-list-item>
                    <a-card hoverable :title="item.name">
                      <template #extra>
                        <router-link :to="'/notice/' + item.id">
                          <a>点击查看</a>
                        </router-link>
                      </template>
                      <a-typography-paragraph
                        style="font-size: 15px; line-height: 1.75"
                        type="secondary"
                        :ellipsis="{ rows: 3 }"
                        :content="item.neirong.replace(/(<([^>]+)>)/gi, '')"
                      />
                      <a-typography-text code
                        >发布时间：{{ item.createTime }}</a-typography-text
                      >
                    </a-card>
                  </a-list-item>
                </template>
              </a-list>
              <div>
                <a-tabs
                  v-model:activeKey="activeKeyWz"
                  @change="changeTabsTwo"
                  type="card"
                  :tabBarGutter="10"
                  :tabBarStyle="{
                    background: '#595959',
                    padding: '16px 16px 16px'
                  }"
                >
                  <a-tab-pane key="newWz" tab="最新发布">
                    <a-list
                      :grid="{ column: 2 }"
                      item-layout="vertical"
                      size="large"
                      :data-source="dataList1"
                      style="margin: -10px"
                    >
                      <template #renderItem="{ item }">
                        <router-link :to="'/news/' + item.id">
                          <a-card hoverable style="margin: 10px">
                            <template #cover>
                              <img
                                height="240"
                                :src="item.image"
                                style="padding: 15px 15px 0px 15px"
                              />
                            </template>
                            <a-card-meta :title="item.name">
                              <template #description>
                                <a-typography-paragraph
                                  style="font-size: 15px; line-height: 1.75"
                                  type="secondary"
                                  :ellipsis="{ rows: 2 }"
                                  :content="
                                    item.description.replace(
                                      /(<([^>]+)>)/gi,
                                      ''
                                    )
                                  "
                                />
                              </template>
                            </a-card-meta>
                          </a-card>
                        </router-link>
                      </template>
                    </a-list>
                  </a-tab-pane>
                  <template #rightExtra>
                    <a-button type="primary" danger class="tabs-extra-button">
                      <router-link to="/news"
                        >资讯
                        <arrow-right-outlined />
                      </router-link>
                    </a-button>
                  </template>
                </a-tabs>
              </div>
              <div>
                <a-tabs
                  v-model:activeKey="activeKeyWz"
                  @change="changeTabsTwo"
                  type="card"
                  :tabBarGutter="10"
                  :tabBarStyle="{
                    background: '#595959',
                    padding: '16px 16px 16px'
                  }"
                >
                  <a-tab-pane key="newWz" tab="最新发布">
                    <a-list
                      :grid="{ column: 2 }"
                      item-layout="vertical"
                      size="large"
                      :data-source="dataList2"
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
                            <a-card-meta :title="item.name">
                              <template #description>
                                <a-typography-paragraph
                                  style="font-size: 15px; line-height: 1.75"
                                  type="secondary"
                                  :ellipsis="{ rows: 2 }"
                                  :content="
                                    item.description.replace(
                                      /(<([^>]+)>)/gi,
                                      ''
                                    )
                                  "
                                />
                              </template>
                            </a-card-meta>
                          </a-card>
                        </router-link>
                      </template>
                    </a-list>
                  </a-tab-pane>
                  <template #rightExtra>
                    <a-button type="primary" danger class="tabs-extra-button">
                      <router-link to="/child"
                        >儿童信息
                        <arrow-right-outlined />
                      </router-link>
                    </a-button>
                  </template>
                </a-tabs>
              </div>
            </div>
            <router-layout />
          </div>
        </div>
      </div>
    </div>
  </div>
  <NoticeChakan v-model:visible="showEdit" :data="current" />
</template>

<script lang="ts" setup>
  import { onMounted, ref, computed } from 'vue';
  import Header from './header.vue';
  import { pageNotices } from '@/api/neirong/notice';
  import type { Notice } from '@/api/neirong/notice/model';
  import { pageChilds } from '@/api/neirong/child';
  import type { Child } from '@/api/neirong/child/model';
  import { useRoute, useRouter } from 'vue-router';
  import type { News } from '@/api/neirong/news/model';
  import { pageNewss } from '@/api/neirong/news';

  const route = useRoute();
  const router = useRouter();

  const showEdit = ref(false);
  // 当前编辑数据
  const current = ref<Notice>();

  const chakan = (row?: News) => {
    current.value = row;
    showEdit.value = true;
  };

  //点击导航
  const selectedKeys = computed(() => {
    return [route.path];
  });

  //点击导航跳转对应页面
  const toPage = ({ key }: { key: string }) => {
    router.push({
      path: key
    });
  };

  const dataList = ref<Notice[]>([]);

  const dataList1 = ref<News[]>([]);

  const dataList2 = ref<Child[]>([]);

  onMounted(() => {
    /* 获取字典数据 */
    pageNotices({
      page: 1,
      limit: 4,
      status: 1,
      sort: 'createTime',
      order: 'desc'
    }).then((list) => {
      dataList.value = list.list;
    });
    pageChilds({
      page: 1,
      limit: 8,
      sort: 'createTime',
      order: 'desc'
    }).then((list) => {
      dataList2.value = list.list;
    });
    pageNewss({
      page: 1,
      limit: 6,
      sort: 'createTime',
      order: 'desc'
    }).then((list) => {
      dataList1.value = list.list;
    });
  });
</script>

<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: 'HomeIndex'
  });
</script>
