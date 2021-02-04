# lbj-client-demo

    部署docker到k8s
    
## 1.打包
## 2.创建dockerfile
    
    mkdir -r /home/lbj/Desktop/Docker
    vi Dockerfile
    
## 3.构建镜像 
       
       docker build -f Dockerfile -t lbj-client-demo:latest .