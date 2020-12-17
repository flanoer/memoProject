<template>
  <div class="list-group">
      <a v-for="item in list" class="list-group-item">
          <span>{{ item.M_SEQ }}</span>
          <span>{{ item.TITLE }}</span>
          <span>{{ item.M_LEVEL }}</span>
      </a>
  </div>
</template>

<script>
import axios from 'axios'
let datas = {};
export default {
    name: 'List'
    , beforeMount(){
        console.log(`At this point, vm.$el has not been created yet.`);
    }
    , mounted() {
        console.log(`At this point, vm.$el has been created and el has been replaced.`);
        let ct = this;
        ct.$nextTick(function(){
            axios.get('/sampleRest/getList.do').then(res => {
                console.log(res);
                // body = JSON.parse(body);
                // window.console.log('after json parse body object >> ',body);
                // let list = [];
                // for(let i in body.data){
                //     list.push(body.data[i]);
                // }
                // ct._data.list = list;
            });
        });
    }
    , data(){
        return {
            list : []
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
