package com.lbj.distributed.db.home;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "hi接口", description = "")
public class HomeHi {
    @ApiModelProperty("端口")
    private String port;
}
