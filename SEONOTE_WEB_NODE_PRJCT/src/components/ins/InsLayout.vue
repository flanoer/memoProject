<template>
    <div>
        <ins-nav />
        <router-view class="container"/>
    </div>
</template>

<script>
export default {
    name: 'InsLayout',
    components : {
        InsNav : () => import('@/components/ins/InsNav.vue')
    }
}

</script>
