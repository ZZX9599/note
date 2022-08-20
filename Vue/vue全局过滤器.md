### vue全局过滤器

**过滤器是一个通过输入数据，能够及时对数据进行处理并返回一个数据结果的简单的函数。 **

**在实际项目开发中根据实际的需求，可以自己编写所需要的过滤器。 过滤器经常用在数据所需的格式化时使用**

**例如 字符串的格式化 以及日期时间的格式化等等**

**过滤器最大的作用就是体现其复用性，如果我们在前端处理的某些文本信息每一次都需要经过重复的特殊处理**

**那么我们一定是要编写一个过滤器来使用。**



**过滤器的语法：使用管道符 "|"**



**全局过滤器的使用：**

**全局过滤器指的是所有 vm 对象都能共享使用的过滤器。**

**过滤器能够使用在两个地方： 插值表达式/指令**



### **案例1：将所有的字母变成大写**



```js
<body>
    <div id="app">
        <!--使用过滤器的语法-->
        <!--{{内容 | 过滤器}}-->
        {{str1 | upCase}}
    </div>

    <script type="text/javascript">
        /*
            自定义全局过滤器
            Vue.filter("参数一","参数二")

            语法：Vue.filter("参数一",function(){
                参数一：过滤器的名称
                参数二：控制行为，就是那个function
                function的参数：就是我们需要操作的数据
            })
         */
        Vue.filter("upCase",function (value){
            //通过value的形参来取得需要操作的值
            value=value.toUpperCase();
            return value;
        })

        //声明vm对象
        let vm=new Vue({
            el:"#app",
            data:{
                "str1":"aaa"
            }
        })

    </script>

</body>
```





### 案例2：定义格式化时间的全局过滤器

```js
<body>
    <div id="app">
        系统默认时间：{{currentTime}}
        <br/>
        过滤器修改的时间：{{currentTime | dateTimeManager}}
    </div>
    <script type="text/javascript">
        Vue.filter("dateTimeManager",function (date){
            let y=date.getFullYear();
            let m=date.getMonth();
            let d=date.getDate();
            let h=date.getHours();
            let mi=date.getMinutes();
            let s=date.getSeconds();
            //假设我们想得到  2022-10-10 10:10:10
            //在ES5中，我们使用“+”来完成字符串拼接
            //return y+"-"+m+"-"+d
            
            //在ES6中，我们使用反引号``来完成字符串拼接
            return `${y}-${m}-${d} ${h}:${mi}:${s}`;
        })
        //声明vm对象
        let vm=new Vue({
            el:"#app",
            data:{
                "currentTime":new Date()
            }
        })
    </script>
</body>
```