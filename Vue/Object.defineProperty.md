### Object.defineProperty

```html
<script type="text/javascript">
    Vue.config.productionTip=false;
    let address="北京";
    let person={
        name:"周志雄",
        age:20
    }
    Object.defineProperty(person,'address',{
        value:"北京",
        //控制是否可以枚举，也就是能否遍历
        enumerable:true,
        //控制能不能修改
        writable:true,
        //控制能不能删除
        configurable:true,
        //当属性被读取【也就是address被读取，就会执行】
        get(){
            console.log("被读取");
            return address;
        },
        set(value){
            console.log("被修改");
            address=value;
        }
    })
</script>
```