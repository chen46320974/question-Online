package edu.hope.biz.common.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fanwenhao
 * @date 2019/9/28 10:06
 */
@Data
@ApiModel
public class BasePageQuery {

    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private Integer size;

    /**
     * 当前页码
     */
    @ApiModelProperty("查询的页码")
    private Integer page;

    @JsonIgnore
    public Page getPage(){
        if(page == null || page < 0){
            page = 0;
        }
        if(size == null || size <= 0 ){
            size = 20;
        }
        return new Page<>(page,size);
    }

}
