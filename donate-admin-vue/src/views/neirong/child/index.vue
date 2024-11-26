<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <ChildSearch @search="reload" />
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="id"
        :columns="(columns as any)"
        :datasource="datasource"
        v-model:selection="selection"
        :scroll="{ x: 800 }"
        :toolkit="null"
      >
        <template #toolbar>
          <a-space>
            <a-button type="primary" @click="openEdit()">
              <template #icon>
                <plus-outlined />
              </template>
              <span>新建</span>
            </a-button>
          </a-space>
        </template>
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'image'">
            <img :src="record.image" :width="200" />
          </template>
          <template v-if="column.key === 'action'">
            <a-space>
              <a @click="openEdit(record)">修改</a>
              <a-divider type="vertical" />
              <a-popconfirm
                title="确定要删除此数据吗？"
                @confirm="remove(record)"
              >
                <a class="ele-text-danger">删除</a>
              </a-popconfirm>
            </a-space>
          </template>
        </template>
      </ele-pro-table>
    </a-card>
  </div>
  <!-- 编辑弹窗 -->
  <ChildEdit v-model:visible="showEdit" :data="current" @done="reload" />
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import { message } from 'ant-design-vue';
  import type { EleProTable } from 'ele-admin-pro';
  import type {
    DatasourceFunction,
    ColumnItem
  } from 'ele-admin-pro/es/ele-pro-table';
  import { toDateString } from 'ele-admin-pro';
  import ChildSearch from './components/child-search.vue';
  import ChildEdit from './components/child-edit.vue';
  import { pageChilds, removeChild } from '@/api/neirong/child';
  import type { Child, ChildParam } from '@/api/neirong/child/model';

  // 表格实例
  const tableRef = ref<InstanceType<typeof EleProTable>>();

  // 表格列配置
  const columns = ref<ColumnItem[]>([
    {
      key: 'index',
      width: 48,
      align: 'center',
      fixed: 'left',
      hideInSetting: true,
      customRender: ({ index }) => index + (tableRef.value?.tableIndex ?? 0)
    },
    {
      title: '名称',
      dataIndex: 'name',
      sorter: true
    },
    {
      title: '性别',
      dataIndex: 'sexName',
      sorter: true
    },
    {
      title: '图片',
      key: 'image',
      dataIndex: 'image',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '年龄',
      dataIndex: 'age',
      sorter: true
    },
    {
      title: '发布日期',
      dataIndex: 'publicDate',
      sorter: true,
      width: 100
    },
    {
      title: '是否领养',
      dataIndex: 'isAdoptName',
      sorter: true,
      width: 100
    },
    {
      title: '创建人',
      dataIndex: 'createUser',
      sorter: true
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      sorter: true,
      ellipsis: true,
      customRender: ({ text }) => toDateString(text)
    },
    {
      title: '操作',
      key: 'action',
      width: 200,
      align: 'center',
      hideInSetting: true
    }
  ]);

  // 表格选中数据
  const selection = ref<Child[]>([]);

  // 当前编辑数据
  const current = ref<Child>();

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  // 表格数据源
  const datasource: DatasourceFunction = ({ page, limit, where, orders }) => {
    return pageChilds({ ...where, ...orders, page, limit });
  };

  /* 搜索 */
  const reload = (where?: ChildParam) => {
    selection.value = [];
    tableRef?.value?.reload({ page: 1, where: where });
  };

  /* 打开编辑弹窗 */
  const openEdit = (row?: Child) => {
    current.value = row;
    showEdit.value = true;
  };

  /* 删除单个 */
  const remove = (row: Child) => {
    const hide = message.loading('请求中..', 0);
    removeChild(row.id)
      .then((msg) => {
        hide();
        message.success(msg);
        reload();
      })
      .catch((e) => {
        hide();
        message.error(e.message);
      });
  };
</script>

<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: 'Child'
  });
</script>
<style>
  img{
    width: 100px;
    height: 100px;
  }
</style>
