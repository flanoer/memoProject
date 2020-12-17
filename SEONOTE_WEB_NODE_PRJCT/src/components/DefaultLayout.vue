<template>
    <div>
        <cmm-header />
        <router-view class="container"/>
        <cmm-footer />
    </div>
</template>

<script>
export default {
  /* eslint-disable */
  name: 'DefaultLayout'
  , components : {
      CmmHeader : () => import('@/components/Header.vue'),
      CmmFooter : () => import('@/components/Footer.vue')
  }
}
</script>
