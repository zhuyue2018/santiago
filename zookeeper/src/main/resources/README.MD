docker-compose.yml
```
version: '2'
networks:
  zk:
services:  
  zookeeper1:
    image: zookeeper:3.4.14
    container_name: zk1.cloud
    networks:
        - zk
    ports:
        - "2181:2181"
    environment:
      ZOO_MY_ID: 1
      ZOO_SERVERS: server.1=0.0.0.0:2888:3888 server.2=zk2.cloud:2888:3888 server.3=zk3.cloud:2888:3888
  zookeeper2:
    image: zookeeper:3.4.14
    container_name: zk2.cloud
    networks:
        - zk
    ports:
        - "2182:2181"
    environment:
      ZOO_MY_ID: 2
      ZOO_SERVERS: server.1=zk1.cloud:2888:3888 server.2=0.0.0.0:2888:3888 server.3=zk3.cloud:2888:3888
  zookeeper3:
    image: zookeeper:3.4.14
    container_name: zk3.cloud
    networks:
        - zk
    ports:
        - "2183:2181"
    environment:
      ZOO_MY_ID: 3
      ZOO_SERVERS: server.1=zk1.cloud:2888:3888 server.2=zk2.cloud:2888:3888 server.3=0.0.0.0:2888:3888
```
注意版本号是3.4.14

执行

docker exec -it zk1.cloud bash ./bin/zkServer.sh status

验证


ZooKeeper JMX enabled by default
Using config: /conf/zoo.cfg
Mode: follower

