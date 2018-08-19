基于jedis-API开发的Redis可视化监控应用
redis连接：
1,获取历史连接
2，连接
3，获取当前连接
4，断开当前连接


redis集群监控：
1.心跳
2，服务器内存，cpu，io负载
3，key 键的数目以及过期键的数目
4，redis集群状态

redis基本操作
string
http://localhost:8092/redisos/base/string?action=set&key=20180611test&value=test
http://localhost:8092/redisos/base/string?action=get&key=20180611test&value=0

redis高级
1，redis配置

应用集成了swagger，方便查看服务接口
http://localhost:8092/redisos/swagger-ui.html

