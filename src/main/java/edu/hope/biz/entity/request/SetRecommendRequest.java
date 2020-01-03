package edu.hope.biz.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：zhao
 * @description：
 * @date ：2020/1/1 0001 13:22
 */
@Data
@ApiModel(value = "设置推荐答案")
public class SetRecommendRequest {


    @ApiModelProperty(value = "问题id")
    private Integer questionId;

    @ApiModelProperty(value = "答案id")
    private Integer answerId;

}
