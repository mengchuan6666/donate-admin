<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <ChildAdoptSearch @search="reload" />
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
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'image'">
            <img :src="record.image" :width="200" />
          </template>
          <template v-if="column.key === 'action'">
            <a-space
              v-if="record.auditStatus != '1' && record.auditStatus != '0'"
            >
              <a @click="openEdit(record)">审核</a>
            </a-space>
          </template>
        </template>
      </ele-pro-table>
    </a-card>
  </div>
  <!-- 编辑弹窗 -->
  <ChildAdoptEdit v-model:visible="showEdit" :data="current" @done="reload" />
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import type { EleProTable } from 'ele-admin-pro';
  import type {
    DatasourceFunction,
    ColumnItem
  } from 'ele-admin-pro/es/ele-pro-table';
  import ChildAdoptSearch from './components/childAdopt-search.vue';
  import ChildAdoptEdit from './components/childAdopt-edit.vue';
  import { pageChildAdopts } from '@/api/neirong/childAdopt';
  import type {
    ChildAdopt,
    ChildAdoptParam
  } from '@/api/neirong/childAdopt/model';

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
      title: '领养人名称',
      dataIndex: 'adoptName',
      sorter: true
    },
    {
      title: '领养儿童名称',
      dataIndex: 'adoptChildName',
      sorter: true
    },
    {
      title: '领养人性别',
      dataIndex: 'adoptSexName',
      sorter: true
    },
    {
      title: '领养人手机号',
      dataIndex: 'adoptPhone',
      sorter: true
    },
    {
      title: '领养人地址',
      dataIndex: 'adoptAddress',
      sorter: true
    },
    {
      title: '婚姻',
      dataIndex: 'marriageName',
      sorter: true
    },
    {
      title: '领养人年龄',
      dataIndex: 'adoptAge',
      sorter: true
    },
    {
      title: '创建人',
      dataIndex: 'createUser',
      sorter: true
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      sorter: true
    },
    {
      title: '审核状态',
      dataIndex: 'auditStatusName',
      sorter: true
    },
    {
      title: '审核时间',
      dataIndex: 'auditTime',
      sorter: true
    },
    {
      title: '审核人',
      dataIndex: 'auditUser',
      sorter: true
    },
    {
      title: '操作',
      key: 'action',
      align: 'center',
      hideInSetting: true
    }
  ]);

  // 表格选中数据
  const selection = ref<ChildAdopt[]>([]);

  // 当前编辑数据
  const current = ref<ChildAdopt>();

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  // 表格数据源
  const datasource: DatasourceFunction = ({ page, limit, where, orders }) => {
    return pageChildAdopts({ ...where, ...orders, page, limit });
  };

  /* 搜索 */
  const reload = (where?: ChildAdoptParam) => {
    selection.value = [];
    tableRef?.value?.reload({ page: 1, where: where });
  };

  /* 打开编辑弹窗 */
  const openEdit = (row?: ChildAdopt) => {
    current.value = row;
    showEdit.value = true;
  };
</script>

<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: 'ChildAdopt'
  });
</script>
