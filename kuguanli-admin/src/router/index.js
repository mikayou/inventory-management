import { createRouter, createWebHistory } from 'vue-router'
import { isLogin } from '../http/api/user'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '',
      name: 'Main',
      component: () => import('@/views/MainView.vue'),
      meta: { requireAuth: true }, // 添加一个元字段，表示需要身份验证
      children: [
        {
          path: '/init/product',
          name: 'addProduct',
          meta: { requireAuth: true },
          component: () => import('@/components/ProductForm.vue')
        },
        {
          path: '/init/warehouse',
          name: 'addWarehouse',
          meta: { requireAuth: true },
          component: () => import('@/components/WarehouseForm.vue')
        },
        {
          path: '/init/supplier',
          name: 'addSupplier',
          meta: { requireAuth: true },
          component: () => import('@/components/SupplierOrCustomerForm.vue')
        },
        {
          path: '/init/catalog',
          name: 'addCatalog',
          meta: { requireAuth: true },
          component: () => import('@/components/CatalogForm.vue')
        },
        {
          path: '/init/customer',
          name: 'addCustomer',
          meta: { requireAuth: true },
          component: () => import('@/components/SupplierOrCustomerForm.vue')
        },
        {
          path: '/rms/purchase-order',
          name: 'addPurchasrOrder',
          meta: { requireAuth: true },
          component: () => import('@/components/PurchaseOrderForm.vue')
        },
        {
          path: '/rms/inspection',
          name: 'inspectionPurchase',
          meta: { requireAuth: true },
          component: () => import('@/components/InspectionPurchase.vue')
        },
        {
          path: '/rms/receiving',
          name: 'receipt',
          meta: { requireAuth: true },
          component: () => import('@/components/Receipt.vue')
        },
        {
          path: '/inventory',
          name: 'inventory',
          meta: { requireAuth: true },
          component: () => import('@/components/WatchInventory.vue')
        }

      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/LoginView.vue')
    }
  ]
})
router.beforeEach((to, from, next) => {
  console.log("此处有1")
  if (to.meta.requireAuth && to.name !== 'Login') {
    console.log("此处有2")
    isLogin().then(res => {
      if (res.data.code == 2001) {
        console.log("此处有3")
        next({ name: 'Login' })
      } else {
        console.log("此处有4")
        next()
      }
    })

  } else {
    next()
  }
})


export default router
