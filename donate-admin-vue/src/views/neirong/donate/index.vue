<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <DonateSearch @search="reload" />
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
  <DonateEdit v-model:visible="showEdit" :data="current" @done="reload" />
</template>

<script lang="ts" setup>
  import { ref } from 'vue';
  import type { EleProTable } from 'ele-admin-pro';
  import type {
    DatasourceFunction,
    ColumnItem
  } from 'ele-admin-pro/es/ele-pro-table';
  import DonateSearch from './components/donate-search.vue';
  import DonateEdit from './components/donate-edit.vue';
  import { pageDonates } from '@/api/neirong/donate';
  import type { Donate, DonateParam } from '@/api/neirong/donate/model';

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
      title: '捐赠名称',
      dataIndex: 'donateName',
      sorter: true
    },
    {
      title: '捐赠类型',
      dataIndex: 'donateTypeName',
      sorter: true
    },
    {
      title: '捐赠数量',
      dataIndex: 'donateNumber',
      sorter: true
    },
    {
      title: '捐赠账号',
      dataIndex: 'donateAccount',
      sorter: true
    },
    {
      title: '捐赠人名称',
      dataIndex: 'donatePersonName',
      sorter: true
    },
    {
      title: '捐赠人手机',
      dataIndex: 'donatePersonPhone',
      sorter: true,
      showSorterTooltip: false,
      ellipsis: true
    },
    {
      title: '捐赠物品描述',
      dataIndex: 'donateGoodsDesc',
      sorter: true
    },
    {
      title: '捐赠时间',
      dataIndex: 'donateTime',
      sorter: true
    },
    {
      title: '操作人',
      dataIndex: 'createUser',
      sorter: true
    },
    {
      title: '操作时间',
      dataIndex: 'createTime',
      sorter: true
    },
    {
      title: '审核状态',
      dataIndex: 'auditStatusName',
      sorter: true
    },
    {
      title: '审核回复',
      dataIndex: 'auditReply',
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
  const selection = ref<Donate[]>([]);

  // 当前编辑数据
  const current = ref<Donate>();

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  // 表格数据源
  const datasource: DatasourceFunction = ({ page, limit, where, orders }) => {
    return pageDonates({ ...where, ...orders, page, limit });
  };

  /* 搜索 */
  const reload = (where?: DonateParam) => {
    selection.value = [];
    tableRef?.value?.reload({ page: 1, where: where });
  };

  /* 打开编辑弹窗 */
  const openEdit = (row?: Donate) => {
    current.value = row;
    showEdit.value = true;
  };
</script>

<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: `Donate`
  });
</script>
