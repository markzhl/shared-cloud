如果使用@EnableSpringHttpSession
则注释掉application.yml中的以下内容
spring:
    redis:
        database: 1
        host: 172.20.97.63
        pool:
            max-active: 20
并在GateBootstrap.java中添加如下Bean的定义
@Bean
public MapSessionRepository sessionRepository() {
        return new MapSessionRepository();
}
如果使用@EnableRedisHttpSession
则将application.yml中的以下内容去掉注释
spring:
    redis:
        database: 1
        host: 172.20.97.63
        pool:
            max-active: 20
并在GateBootstrap.java中去掉如下Bean的定义
@Bean
public MapSessionRepository sessionRepository() {
        return new MapSessionRepository();
}