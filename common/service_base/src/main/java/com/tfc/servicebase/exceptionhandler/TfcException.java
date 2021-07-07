package com.tfc.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 田付成
 * @date 2021/7/6 0:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TfcException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
