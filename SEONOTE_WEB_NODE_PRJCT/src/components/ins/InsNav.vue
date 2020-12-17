<template>
<ul class="nav nav-pills nav-justified" role="tablist">
    <li role="presentation"><router-link to="/def">홈</router-link>
    <li role="presentation"><router-link to="/ins"> 보험 메인 </router-link>
    <li role="presentation"><router-link to="/ins/Annuity"> 연금보험 </router-link>
    <li role="presentation"><router-link to="/ins/Cancer"> 암보험 </router-link></li>
    <li role="presentation"><router-link to="/ins/Health"> 건강보험 </router-link></li>
    <li role="presentation"><router-link to="/ins/Save"> 정기보험</router-link></li>
</ul>
</template>

<script>
export default {
    name : 'InsNav'
}
</script>
