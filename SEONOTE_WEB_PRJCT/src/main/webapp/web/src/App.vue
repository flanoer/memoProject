<template>
<div id="app">
    <ul>
        <li><router-link to="/">App First Page</router-link>
        <li><router-link to="/something">SOMETHING</router-link></li>
        <li><router-link to="/list">LIST</router-link></li>
        <li><router-link to="/helloWorld">HELLOWORLD</router-link></li>
    </ul>
    <router-view />
</div>
</template>

<script>
import request from 'request'
export default {
  /* eslint-disable */
  name: 'App'
}
</script>

<style>
npm run dev #app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
