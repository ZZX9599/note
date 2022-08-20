### 在for循环中使用过滤器



**案例：将所有的商品进行打8折,5折**

```js
<body>
    <div id="app">
        <!--记得养成为key作为唯一标识的好习惯-->
        <p v-for="fruit in fruitList" :key="fruit.id">
            水果ID：{{fruit.id}}
            <br/>
            水果名：{{fruit.name}}
            <br/>
            水果原价：{{fruit.price}}
            <br/>
            水果八折价：{{fruit.price | discount8}}
            <br/>
            水果五折价：{{fruit.price | discount5}}
        </p>
    </div>
    <script type="text/javascript">
        //打折过滤器
        Vue.filter("discount8",function (value){
            return parseInt(value)*0.8;
        });

        Vue.filter("discount5",function (value){
            return parseInt(value)*0.5;
        })
        
        //声明vm对象
        let vm=new Vue({
            el:"#app",
            data:{
                "fruitList":[
                    {"id":"A001","name":"苹果","price":10},
                    {"id":"A002","name":"梨子","price":8},
                    {"id":"A003","name":"香蕉","price":14},
                    {"id":"A004","name":"桃子","price":18},

                ]
            }
        })
    </script>
</body>
```



### 连续使用多个过滤器

```js
水果八折，然后五折：{{fruit.price | discount8 | discount5}}
```