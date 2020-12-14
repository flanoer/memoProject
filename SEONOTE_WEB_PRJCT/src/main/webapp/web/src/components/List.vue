<template>
  <div>
      <div v-for="item in list">
          <div>{{ item }}</div>
      </div>
  </div>
</template>

<script>
import request from 'request'
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
            request('http://localhost:8080/sampleRest/getList.do', function(err, resp, body) {
                window.console.log(err)
                window.console.log(resp)
                window.console.log(body)
                body = JSON.parse(body);
                window.console.log('after json parse body object >> ',body);
                let list = [];
                for(let i in body.data){
                    list.push(body.data[i]);
                }
                ct._data.list = list;
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
