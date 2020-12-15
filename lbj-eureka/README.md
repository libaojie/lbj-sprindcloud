# 服务注册中心

    注意，如果用docker封装时，请传参数EUREKA_INSTANCE_IP-ADDRESS=10.4.70.12
    例：
    docker run -d ${v_port}  -v /etc/localtime:/etc/localtime -v ${v_wormhole}:/wormhole --name ${v_name} -e "EUREKA_INSTANCE_IP-ADDRESS=10.4.70.12" ${v_name}:${v_version} /bin/bash /wormhole/script/start.sh
