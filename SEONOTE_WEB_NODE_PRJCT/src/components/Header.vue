<template>
<ul class="nav nav-pills nav-justified" role="tablist">
    <li role="presentation"><router-link to="/def">App First Page</router-link>
    <li role="presentation"><router-link to="/def/something">SOMETHING</router-link></li>
    <li role="presentation"><router-link to="/def/list">LIST</router-link></li>
    <li role="presentation"><router-link to="/def/helloWorld">HELLOWORLD</router-link></li>
    <li role="presentation"><router-link to="/ins">보험 메인</router-link></li>
</ul>
</template>

<script>
export default  {
    name : 'CmmHeader'
}
</script>
