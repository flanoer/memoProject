import Vue from 'vue'
import Router from 'vue-router'

const NotFoundComponent = () => import(/* webpackChunkName: "notfound" */ '@/components/global/NotFound.vue')

export default new Router({
  mode: 'history',
  routes: [
    // default path
    {
        path: '/def',
        component : () => import(/* webpackChunkName: "def" */'@/components/DefaultLayout.vue'),
        children : [
            {
                path: 'something',
                component : () => import(/* webpackChunkName: "something" */ '@/components/Something.vue')
            },
            {
                path: 'list',
                component : () => import(/* webpackChunkName: "list" */ '@/components/List.vue')
            },
            {
                path: 'helloWorld',
                component : () => import(/* webpackChunkName: "helloworld" */ '@/components/HelloWorld.vue')
            },
            {
                path: '*',
                component : NotFoundComponent
            }
        ]
    },
    {
        path : '/ins',
        component : () => import(/* webpackChunkName: "ins" */ '@/components/ins/InsLayout.vue'),
        children : [
            {
                path: 'save',
                component : () => import(/* webpackChunkName: "save" */ '@/components/ins/Save.vue')
            },
            {
                path: 'annuity',
                component : () => import(/* webpackChunkName: "annuity" */ '@/components/ins/Annuity.vue')
            },
            {
                path: 'health',
                component : () => import(/* webpackChunkName: "health" */ '@/components/ins/Health.vue')
            },
            {
                path: 'cancer',
                component : () => import(/* webpackChunkName: "cancer" */ '@/components/ins/Cancer.vue')
            },
            {
                path: '*',
                component : NotFoundComponent
            }
        ]
    },
    {
        path: '*',
        component : NotFoundComponent
    }

  ]
})
