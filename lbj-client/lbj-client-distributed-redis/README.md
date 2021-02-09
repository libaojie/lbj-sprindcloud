# lbj-distributed-reids

    基于redis的分布式锁
    
    

## 创建redis docker

    mkdir -p /home/lbj/Desktop/docker/lbj_redis
    docker pull redis:3.2
    docker run --name lbj_redis -p 16379:6379 -v $PWD/data:/home/lbj/Desktop/docker/lbj_redis/data  -d redis:3.2 redis-server  --appendonly yes

    
    
    
