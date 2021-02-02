## zephyr 
**和风**   ***<u>The zephyr is a symbol of hope in spring</u>***

## 快速启动
 启动注册中心：进入注册中心目录，zephyr-registor/nacos/bin 执行命令startup -m standalone 单机模式启动注册中心

 默认端口8848  默认用户名:nacos  密码:nacos 
## 中间件说明

### 基础服务

| Spring Cloud Version    | Spring Cloud Alibaba Version | Spring Boot Version |
| ----------------------- | ---------------------------- | ------------------- |
| Spring Cloud Hoxton.SR8 | 2.2.5.RELEASE                | 2.3.2.RELEASE       |

### 组件

| 组件中间件作用 | 组件名称            | 版本  |
| -------------- | ------------------- | ----- |
| 注册中心       | nacos               | 1.4.1 |
| 配置中心       | nacos               | 1.4.1 |
| 网关           | spring cloud config |       |
| 服务间调用     | feign               |       |
| 负载均衡       | ribbon              |       |
| 熔断降级       | sentinel            | 1.8.0 |
| 分布式事务     | Seata               | 1.3.0 |

### 未来版本说明：

后续会升级SpringCloud 2020 ，时间会在SpringCloud Alibaba 毕业版本支持时候更新

到时候项目会重构部分模块，因为SpringCloud 2020做了许多更新和移除

[详情参考](https://www.cnblogs.com/javastack/p/14183634.html)

## 端口说明
- 网关zephyr-gateway 9000
- 认证中心zephyr-auth 10010
- 系统服务zephyr-sys 10020
- 商城服务zephyr-mall 10030

## 优秀开源推荐
- zlt-microservices-platform https://gitee.com/zlt2000/microservices-platform.git
- Pro-Cloud https://gitee.com/gitsc/pro-cloud.git 
- Cloud-Platform https://gitee.com/geek_qi/cloud-platform.git
- wisdom-education  https://gitee.com/zhuimengshaonian/wisdom-education.git
- pig https://gitee.com/log4j/pig.git
- FEBS-Cloud https://github.com/febsteam/FEBS-Cloud

- youlai-mall https://github.com/hxrui/youlai-mall.git
