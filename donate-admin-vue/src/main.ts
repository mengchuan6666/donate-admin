import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import permission from './utils/permission';
import i18n from './i18n';
import './styles/index.less';
//引入echarts
import * as echarts from 'echarts';



const app = createApp(App);

app.config.globalProperties.$echarts = echarts;

app.use(router);
app.use(store);
app.use(permission);
app.use(i18n);

app.mount('#app');
