# lbj-distributed-reids

    基于redis的分布式锁
    
    

## 创建redis docker

    mkdir -p /home/lbj/Desktop/docker/lbj_redis
    docker pull redis:3.2
    docker run --name lbj_redis -p 16379:6379 -v $PWD/data:/home/lbj/Desktop/docker/lbj_redis/data  -d redis:3.2 redis-server  --appendonly yes

## 逐步发展

    redis锁大致为这三个
    setnx
    redLock
    redisson
    
### setnx

    一般代指set指令加上nx参数。实现了key不存在才set成功的特性。
    
    String uuid = xxxx;
    // 有的提供的方法名为set 有的叫setIfAbsent
    set Test uuid NX PX 3000
    try{
    // handle
    } finally {
        // unlock
        if(uuid.equals(redisTool.get('Test')){
            redisTool.del('Test');
        }
    }
    
    
    但是get del和expire并非原子操作，所以存在进程问题。可以使用lua脚本
    通过redis的eval和evalsha命令执行
    
    -- lua删除锁：
    -- KEYS和ARGV分别是以集合方式传入的参数，对应上文的Test和uuid。
    -- 如果对应的value等于传入的uuid。
    if redis.call('get', KEYS[1]) == ARGV[1] 
        then 
    	-- 执行删除操作
            return redis.call('del', KEYS[1]) 
        else 
    	-- 不成功，返回0
            return 0 
    end
    
    通过lua能保证原子性，是因为redis的eval/evalsha一条命令执行不完，不会执行其他指令。
    
    
    
    
