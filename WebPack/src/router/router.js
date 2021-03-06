import Main from '@/views/Main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
    path: '/login',
    name: 'login',
    meta: {
        title: 'Login - 登录'
    },
    component: () => import('@/views/login.vue')
};

export const page404 = {
    path: '/*',
    name: 'error-404',
    meta: {
        title: '404-页面不存在'
    },
    component: () => import('@/views/error-page/404.vue')
};

export const page403 = {
    path: '/403',
    meta: {
        title: '403-权限不足'
    },
    name: 'error-403',
    component: () => import('@//views/error-page/403.vue')
};

export const page500 = {
    path: '/500',
    meta: {
        title: '500-服务端错误'
    },
    name: 'error-500',
    component: () => import('@/views/error-page/500.vue')
};

export const preview = {
    path: '/preview',
    name: 'preview',
    component: () => import('@/views/form/article-publish/preview.vue')
};

export const locking = {
    path: '/locking',
    name: 'locking',
    component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
    path: '/',
    name: 'otherRouter',
    redirect: '/home',
    component: Main,
    children: [
        {path: 'home', title: {i18n: 'home'}, name: 'home_index', component: () => import('@/views/home/home.vue')},
        {
            path: 'ownspace',
            title: '个人中心',
            name: 'ownspace_index',
            component: () => import('@/views/own-space/own-space.vue')
        },
        {
            path: 'order/:order_id',
            title: '订单详情',
            name: 'order-info',
            component: () => import('@/views/advanced-router/component/order-info.vue')
        }, // 用于展示动态路由
        {
            path: 'shopping',
            title: '购物详情',
            name: 'shopping',
            component: () => import('@/views/advanced-router/component/shopping-info.vue')
        }, // 用于展示带参路由
        {path: 'message', title: '消息中心', name: 'message_index', component: () => import('@/views/message/message.vue')}
    ]
};

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
    {
        path: '/access',
        icon: 'key',
        name: 'access',
        title: '权限管理',
        component: Main,
        children: [
            {path: 'index', title: '权限管理', name: 'access_index', component: () => import('@/views/access/access.vue')}
        ]
    },
    {
        path: '/access-test',
        icon: 'lock-combination',
        title: '权限测试页',
        name: 'accesstest',
        access: 0,
        component: Main,
        children: [
            {
                path: 'index',
                title: '权限测试页',
                name: 'accesstest_index',
                access: 0,
                component: () => import('@/views/access/access-test.vue')
            }
        ]
    },
    {
        path: '/international',
        icon: 'earth',
        title: {i18n: 'international'},
        name: 'international',
        component: Main,
        children: [
            {
                path: 'index',
                title: {i18n: 'international'},
                name: 'international_index',
                component: () => import('@/views/international/international.vue')
            }
        ]
    },
    {
        path: '/system-config',
        icon: 'gear-b',
        name: 'systemConfig',
        title: '系统设置',
        component: Main,
        children: [
            {
                path: 'user-mgr',
                icon: 'android-globe',
                name: 'userMgr',
                title: '用户管理',
                component: () => import('@/views/my-system/user/user-manager.vue')
            },
            {
                path: 'item-dict',
                icon: 'android-globe',
                name: 'itemDict',
                title: '物品字典',
                component: () => import('@/views/my-system/system-config/item-dict.vue')
            },
            {
                path: 'area-config',
                icon: 'android-globe',
                name: 'areaConfig',
                title: '区域名录',
                component: () => import('@/views/my-system/system-config/area-config.vue')
            },
            {
                path: 'export-type-config',
                icon: 'android-globe',
                name: 'exportTypeConfig',
                title: '出库类型字典',
                component: () => import('@/views/my-system/system-config/export-type-config.vue')
            },
            {
                path: 'house-resource-config',
                icon: 'android-globe',
                name: 'houseResourceConfig',
                title: '房源名录',
                component: () => import('@/views/my-system/system-config/house-resource-config.vue')
            },
            {
                path: 'erector-config',
                icon: 'ios-people',
                name: 'erectorConfig',
                title: '安装师傅名录',
                component: () => import('@/views/my-system/system-config/erector-config.vue')
            },
            {
                path: 'price-config',
                icon: 'social-yen',
                name: 'priceConfig',
                title: '打包价外费用名录',
                component: () => import('@/views/my-system/system-config/price-config.vue')
            },
            {
                path: 'store-config',
                icon: 'filing',
                name: 'storeConfig',
                title: '仓库名录',
                component: () => import('@/views/my-system/system-config/store-config.vue')
            },
            {
                path: 'fix-reporter-config',
                icon: 'ios-body-outline',
                name: 'fixReporterConfig',
                title: '报修人员名录',
                component: () => import('@/views/my-system/system-config/fix-reporter-config.vue')
            },
            {
                path: 'fix-type-config',
                icon: 'information-circled',
                name: 'fixTypeConfig',
                title: '维修品类名录',
                component: () => import('@/views/my-system/system-config/fix-type-config.vue')
            },
            {
                path: 'fix-item-config',
                icon: 'android-menu',
                name: 'fixItemConfig',
                title: '维修名录',
                component: () => import('@/views/my-system/system-config/fix-item-config.vue')
            },
            {
                path: 'config-type-config',
                icon: 'ios-cog',
                name: 'configTypeConfig',
                title: '配置类型名录',
                component: () => import('@/views/my-system/system-config/config-type-config.vue')
            },
            {
                path: 'exchange-reason-config',
                icon: 'reply',
                name: 'exchangeReasonConfig',
                title: '退换原因名录',
                component: () => import('@/views/my-system/system-config/exchange-reason-config.vue')
            },
            {
                path: 'exchange-record-config',
                icon: 'arrow-swap',
                name: 'exchangeRecordConfig',
                title: '退换记录名录',
                component: () => import('@/views/my-system/system-config/exchange-record-config.vue')
            },
            {
                path: 'fault-config',
                icon: 'wrench',
                name: 'faultConfig',
                title: '故障名录',
                component: () => import('@/views/my-system/system-config/fault-config.vue')
            },
            {
                path: 'punishment-config',
                icon: 'cash',
                name: 'punishmentConfig',
                title: '扣罚名录',
                component: () => import('@/views/my-system/system-config/punishment-config.vue')
            },
            // {
            //     path: 'text-editor',
            //     icon: 'compose',
            //     name: 'text-editor',
            //     title: '富文本编辑器',
            //     component: () => import('@/views/my-components/text-editor/text-editor.vue')
            // },
            // {
            //     path: 'md-editor',
            //     icon: 'pound',
            //     name: 'md-editor',
            //     title: 'Markdown编辑器',
            //     component: () => import('@/views/my-components/markdown-editor/markdown-editor.vue')
            // },
            // {
            //     path: 'image-editor',
            //     icon: 'crop',
            //     name: 'image-editor',
            //     title: '图片预览编辑',
            //     component: () => import('@/views/my-components/image-editor/image-editor.vue')
            // },
            // {
            //     path: 'draggable-list',
            //     icon: 'arrow-move',
            //     name: 'draggable-list',
            //     title: '可拖拽列表',
            //     component: () => import('@/views/my-components/draggable-list/draggable-list.vue')
            // },
            // {
            //     path: 'area-linkage',
            //     icon: 'ios-more',
            //     name: 'area-linkage',
            //     title: '城市级联',
            //     component: () => import('@/views/my-components/area-linkage/area-linkage.vue')
            // },
            // {
            //     path: 'file-upload',
            //     icon: 'android-upload',
            //     name: 'file-upload',
            //     title: '文件上传',
            //     component: () => import('@/views/my-components/file-upload/file-upload.vue')
            // },
            // {
            //     path: 'count-to',
            //     icon: 'arrow-graph-up-right',
            //     name: 'count-to',
            //     title: '数字渐变',
            //     // component: () => import('@/views/my-components/count-to/count-to.vue')
            //     component: () => import('@/views/my-components/count-to/count-to.vue')
            // },
            // {
            //     path: 'split-pane-page',
            //     icon: 'ios-pause',
            //     name: 'split-pane-page',
            //     title: 'split-pane',
            //     component: () => import('@/views/my-components/split-pane/split-pane-page.vue')
            // }
        ]
    },
    {
        path: '/store-manager',
        icon: 'android-checkbox',
        name: 'storeManager',
        title: '库存管理',
        component: Main,
        children: [
            {
                path: 'import-list',
                title: '入库单列表',
                name: 'importList',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/import-list.vue')
            },
            {
                path: 'deliver-list',
                title: '发货单列表',
                name: 'deliverList',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/deliver-list.vue')
            },
            {
                path: 'export-list',
                title: '出库单列表',
                name: 'exportList',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/export-list.vue')
            },
            {
                path: 'import/:documentNo?',
                title: '入库单',
                name: 'import',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/import.vue')
            },
            {
                path: 'deliver/:documentNo?',
                title: '发货单',
                name: 'deliver',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/deliver.vue')
            },

            {
                path: 'export/:documentNo?',
                title: '出库单',
                name: 'export',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/export.vue')
            },
            // { path: 'artical-publish', title: '文章发布', name: 'artical-publish', icon: 'compose', component: () => import('@/views/form/article-publish/article-publish.vue') },
            // { path: 'workflow', title: '工作流', name: 'workflow', icon: 'arrow-swap', component: () => import('@/views/form/work-flow/work-flow.vue') }

        ]
    },
    // {
    //     path: '/charts',
    //     icon: 'ios-analytics',
    //     name: 'charts',
    //     title: '图表',
    //     component: Main,
    //     children: [
    //         { path: 'pie', title: '饼状图', name: 'pie', icon: 'ios-pie', component: resolve => { require('@/views/access/access.vue') },
    //         { path: 'histogram', title: '柱状图', name: 'histogram', icon: 'stats-bars', component: resolve => { require('@/views/access/access.vue') }

    //     ]
    // },
    {
        path: '/defective',
        icon: 'ios-grid-view',
        name: 'defective',
        title: '不良品管理',
        component: Main,
        children: [
            {
                path: 'defective-product-list',
                title: '不良品登记列表',
                name: 'defectiveProductList',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/defective-product-list.vue')
            },
            {
                path: 'defective-damage-list',
                title: '不良品报损列表',
                name: 'defectiveDamageList',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/defective-damage-list.vue')
            },
            {
                path: 'defective-repair-list',
                title: '不良品修复列表',
                name: 'defectiveRepairList',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/defective-repair-list.vue')
            },
            {
                path: 'defective-product/:documentNo?',
                title: '不良品登记单',
                name: 'defectiveProduct',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/defective-product.vue')
            },
            {
                path: 'defective-damage/:documentNo?',
                title: '不良品报损单',
                name: 'defectiveDamage',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/defective-damage.vue')
            },
            {
                path: 'defective-repair/:documentNo?',
                title: '不良品修复单',
                name: 'defectiveRepair',
                icon: 'compose',
                component: () => import('@/views/my-system/store-manager/defective-repair.vue')
            },
            // { path: 'dragableTable', title: '可拖拽排序', name: 'dragable-table', icon: 'arrow-move', component: () => import('@/views/tables/dragable-table.vue') },
            // { path: 'editableTable', title: '可编辑表格', name: 'editable-table', icon: 'edit', component: () => import('@/views/tables/editable-table.vue') },
            // { path: 'searchableTable', title: '可搜索表格', name: 'searchable-table', icon: 'search', component: () => import('@/views/tables/searchable-table.vue') },
            // { path: 'exportableTable', title: '表格导出数据', name: 'exportable-table', icon: 'code-download', component: () => import('@/views/tables/exportable-table.vue') },
            // { path: 'table2image', title: '表格转图片', name: 'table-to-image', icon: 'images', component: () => import('@/views/tables/table-to-image.vue') }
        ]
    },
    {
        path: '/advanced-router',
        icon: 'ios-infinite',
        name: 'advanced-router',
        title: '高级路由',
        component: Main,
        children: [
            {
                path: 'mutative-router',
                title: '动态路由',
                name: 'mutative-router',
                icon: 'link',
                component: () => import('@/views/advanced-router/mutative-router.vue')
            },
            {
                path: 'argument-page',
                title: '带参页面',
                name: 'argument-page',
                icon: 'android-send',
                component: () => import('@/views/advanced-router/argument-page.vue')
            }
        ]
    },
    {
        path: '/error-page',
        icon: 'android-sad',
        title: '错误页面',
        name: 'errorpage',
        component: Main,
        children: [
            {
                path: 'index',
                title: '错误页面',
                name: 'errorpage_index',
                component: () => import('@/views/error-page/error-page.vue')
            }
        ]
    }
];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
    loginRouter,
    otherRouter,
    preview,
    locking,
    ...appRouter,
    page500,
    page403,
    page404
];
