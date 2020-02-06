## 在SpringBoot 中整合 [squirrel-foundation](https://github.com/hekailiang/squirrel) 状态机的demo

### 原理

将 `squirrel-foundation` 提供的状态机功能进行二次封装, 具体请查看 [OrderStateMachineEngine](./src/main/java/com/miaotaizi/statemachinedemo/statemachine/OrderStateMachineEngine.java) 的实现

### 测试
修改 `application.yaml` 的相关参数, 运行访问 [OrderController](./src/main/java/com/miaotaizi/statemachinedemo/controller/OrderController.java) 定义的路由即可

> 数据持久化使用的是 `mybatis-plus` 并非本 demo 的重点
> `squirrel-foundation` 的详细使用, 请参考 [squirrel-foundation状态机的使用细节](https://segmentfault.com/a/1190000009906469) [@TimGuan](https://github.com/TimGuan)