/**
 * 路由配置
 */
import type { _RouteLocationBase } from 'vue-router';
import { createRouter, createWebHistory } from 'vue-router';
import { WHITE_LIST } from '@/config/setting';
import { useUserStore } from '@/store/modules/user';
import { menuToRoutes } from 'ele-admin-pro';
import EleLayout from '@/layout/index.vue';
import RedirectLayout from '@/components/RedirectLayout';
import RouterLayout from '@/components/RouterLayout/index.vue';
import { getToken } from '@/utils/token-util';
import NProgress from 'nprogress';
const modules = import.meta.glob('/src/views/**/index.vue');

NProgress.configure({
  speed: 200,
  minimum: 0.02,
  //trickleRate: 0.1,
  trickleSpeed: 200,
  showSpinner: false
});

// 静态路由
const routes = [
  {
    path: '/login',
    component: () => import('@/views/login/index.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/regiter',
    component: () => import('@/views/regiter/index.vue'),
    meta: { title: '用户注册' }
  },
  {
    path: '/index',
    component: () => import('@/views/home/index.vue'),
    meta: { title: '首页' }
  },

  {
    path: '/child',
    component: () => import('@/views/home/child.vue'),
    meta: { title: '儿童信息' }
  },
  {
    path: '/child/:id',
    component: () => import('@/views/home/child/childIndex.vue'),
    meta: { title: '儿童信息查看' }
  },
  {
    path: '/notice',
    component: () => import('@/views/home/notice.vue'),
    meta: { title: '公告信息' }
  },
  {
    path: '/notice/:id',
    component: () => import('@/views/home/notice/noticeIndex.vue'),
    meta: { title: '公告查看' }
  },
  {
    path: '/userinfo',
    component: () => import('@/views/home/userinfo.vue'),
    meta: { title: '个人中心' }
  },
  {
    path: '/redirect',
    component: EleLayout,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: RedirectLayout
      }
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/exception/404/index.vue')
  }
];

const router = createRouter({
  routes,
  history: createWebHistory()
});

// 路由守卫
router.beforeEach(async (to) => {
  const pathNew = to.path.split('/');
  NProgress.start();
  updateTitle(to);
  // 判断是否已登录
  if (!getToken()) {
    debugger;
    if (!WHITE_LIST.includes('/' + pathNew[1])) {
      return {
        path: '/login',
        query: to.path === '/' ? {} : { from: to.path }
      };
    }
    return;
  }
  // 判断是否已注册动态路由
  const userStore = useUserStore();
  if (!userStore.menus) {
    const { menus, homePath } = await userStore.fetchUserInfo();
    router.addRoute({
      path: '/',
      component: EleLayout,
      redirect: homePath,
      children: menuToRoutes(menus, getComponent)
    });
    return { ...to, replace: true };
  }
});

router.afterEach(() => {
  setTimeout(() => {
    NProgress.done(true);
  }, 300);
});

// 更新浏览器标题
const updateTitle = function (route: _RouteLocationBase) {
  if (!route?.path?.startsWith('/redirect/')) {
    const names: string[] = [];
    if (route?.meta?.title) {
      names.push(route.meta.title as string);
    }
    const appName = import.meta.env.VITE_APP_NAME as string;
    if (appName) {
      names.push(appName);
    }
    document.title = names.join(' - ');
  }
};

// 获取路由组件
const getComponent = function (component?: string) {
  if (!component) {
    return RouterLayout;
  }
  const module = modules[`/src/views/${component}.vue`];
  if (!module) {
    return modules[`/src/views/${component}/index.vue`];
  }
  return module;
};

export default router;
