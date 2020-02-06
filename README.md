## 在SpringBoot 中整合 [squirrel-foundation](https://github.com/hekailiang/squirrel) 状态机的demo

### 原理

将 `squirrel-foundation` 提供的状态机功能进行二次封装, 具体请查看 [OrderStateMachineEngine](./src/main/java/com/miaotaizi/statemachinedemo/statemachine/OrderStateMachineEngine.java) 的实现

### 测试
修改 `application.yaml` 的相关参数, 运行访问 [OrderController](./src/main/java/com/miaotaizi/statemachinedemo/controller/OrderController.java) 定义的路由即可