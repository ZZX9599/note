### data和el的两种写法



**data和el的写法**

```html
<script type="text/javascript">
    Vue.config.productionTip=false;
    let vm=new Vue({
        data(){
            //是vue调用的
            return{
                name:"zhouzhixiong"
            }
        }
    })
    console.log(vm);
    //挂载
    vm.$mount("#root")
</script>
```



**常用写法**

```html
<script type="text/javascript">
    Vue.config.productionTip=false;
    let vm=new Vue({
        el:"#root",
        data:{
            name:"zhouxhixiong"
        }
    })
</script>
```



**注意：如果这些函数是Vue管理的，一定不要写箭头函数**