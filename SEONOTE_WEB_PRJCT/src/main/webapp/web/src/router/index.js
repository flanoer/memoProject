import Vue from 'vue'
import Router from 'vue-router'
import Something from '@/components/Something'
import HelloWorld from '@/components/HelloWorld'
import List from '@/components/List'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    // default path
    {
        path: '/',
    }
    ,{
        path: '/something',
        name: 'Something',
        component: Something
    }
    ,{
        path: '/list',
        name: 'List',
        component: List
    }
    ,{
        path: '/helloWorld',
        name: 'HelloWorld',
        component: HelloWorld
    }
  ]
})
