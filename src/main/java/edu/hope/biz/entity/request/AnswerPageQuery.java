package edu.hope.biz.entity.request;

import edu.hope.biz.common.request.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：zhao
 * @description：
 * @date ：2020/1/1 0001 13:39
 */
@Data
@ApiModel(value = "答案分页查询参数")
public class AnswerPageQuery extends BasePageQuery{


    @ApiModelProperty(value = "问题id")
    private Integer questionId;


}
