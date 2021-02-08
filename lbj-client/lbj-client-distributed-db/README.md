# lbj-distributed-db

    基于数据库的分布式锁
    

## 过程

### 1.创建表
    
    CREATE TABLE IF NOT EXISTS t_dist_db(
       id INT NOT NULL,
       code VARCHAR(255) NOT NULL,
       name VARCHAR(255) NOT NULL,
       PRIMARY KEY ( id )
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    
    
