package edu.hope.biz.entity.request;

import edu.hope.biz.common.request.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author ：zhao
 * @description：
 * @date ：2019/12/31 0031 16:16
 */
@Data
@ApiModel(value = "问题 分页参数")
public class QuestionsPageQuery extends BasePageQuery {

    /**
     * 问题名
     */
    private String questionName;

    /**
     * 解决状态
     */
    private Integer status;

    /**
     *发布者id
     */
    private Integer publishId;

//    /**
//     *发布者用户名
//     */
//    private String publishName;
}
