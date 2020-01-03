package edu.hope.biz.entity.edit;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ：zhao
 * @description：
 * @date ：2019/12/31 0031 0:11
 */
@Data
@ApiModel(value = "发布问题 表单")
public class AddQuestionEditRequest {

    /**
     *id
     */
    private Integer id;

    /**
     * 问题名
     */
    @NotBlank(message = "名称不能为空")
    private String questionName;

    /**
     * 悬赏积分
     */
    @NotNull(message = "悬赏积分不能为空")
    private Integer points;

    /**
     * 问题内容
     */
    @NotBlank(message = "问题内容不能为空")
    private String content;
}
