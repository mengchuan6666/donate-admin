<template>
  <div class="ele-body">
    <a-card :bordered="false">
      <!-- 搜索表单 -->
      <CommentSearch @search="reload" />
      <!-- 表格 -->
      <ele-pro-table
        ref="tableRef"
        row-key="id"
        :columns="(columns as any)"
        :datasource="datasource"
        v-model:selection="selection"
        :scroll="{ x: 800 }"
      >

        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'action'">
            <a-space>
              <a-popconfirm
                title="确定要删除此公告数据吗？"
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
</template>

<script lang="ts" setup>
  import { computed, ref } from 'vue';
  import { message, Modal } from 'ant-design-vue';
  import type { EleProTable } from 'ele-admin-pro';
  import type {
    DatasourceFunction,
    ColumnItem
  } from 'ele-admin-pro/es/ele-pro-table';
  import { toDateString } from 'ele-admin-pro';
  import CommentSearch from './components/comment-search.vue';
  import { pageComments, removeComment } from '@/api/neirong/comment';
  import type { Comment, CommentParam } from '@/api/neirong/comment/model';
  import { useUserStore } from '@/store/modules/user';

  const userStore = useUserStore();
  // 登录用户信息
  const loginUser = computed(() => userStore.info ?? {});

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
      title: '公告标题',
      dataIndex: 'name',
      sorter: true
    },
    {
      title: '评论内容',
      width: 600,
      dataIndex: 'comment',
      sorter: true
    },
    {
	  title: '评论人',
	  dataIndex: 'createUser',
	  sorter: true
    },
    {
      title: '评论时间',
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
  const selection = ref<Comment[]>([]);

  // 当前编辑数据
  const current = ref<Comment>();

  // 是否显示编辑弹窗
  const showEdit = ref(false);

  const showEdit1 = ref(false);

  // 表格数据源
  const datasource: DatasourceFunction = ({ page, limit, where, orders }) => {
    return pageComments({ ...where, ...orders, page, limit });
  };

  /* 搜索 */
  const reload = (where?: CommentParam) => {
    selection.value = [];
    tableRef?.value?.reload({ page: 1, where: where });
  };

  /* 打开编辑弹窗 */
  const openEdit = (row?: Comment) => {
    current.value = row;
    showEdit.value = true;
  };

  const chakan = (row?: Comment) => {
    current.value = row;
    showEdit1.value = true;
  };

  /* 删除单个 */
  const remove = (row: Comment) => {
    const hide = message.loading('请求中..', 0);
    removeComment(row.id)
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


  const fabu = (row: Comment) => {
    const hide = message.loading('请求中..', 0);
    fabuComment(row.id)
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
    name: 'Comment'
  });
</script>
