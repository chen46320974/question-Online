package edu.hope.biz.entity.edit;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：zhao
 * @description：
 * @date ：2020/1/1 0001 12:29
 */
@Data
@ApiModel(value = "回答问题 表单")
public class AddAnswerEditRequest {

    /**
     * 所回答问题id
     */
    @NotBlank(message = "问题id不能为空")
    private String questionId;


    /**
     * 答案内容
     */
    @NotBlank(message = "答案内容不能为空")
    private String content;
}
