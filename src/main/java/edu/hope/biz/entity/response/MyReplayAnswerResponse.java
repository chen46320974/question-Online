package edu.hope.biz.entity.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author ：zhao
 * @description：
 * @date ：2020/1/1 0001 23:17
 */
@Data
@ApiModel(value = "回复我的问题")
public class MyReplayAnswerResponse {

    private Integer id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最后更新时间
     */
    private Date gmtModified;

    /**
     * 所回答问题id
     */
    private String questionId;

    /**
     * 获得积分
     */
    private Integer points;

    /**
     * 答案内容
     */
    private String content;

    /**
     *是否推荐答案 0不是 1是
     */
    private Integer isRecommend;

    /**
     * 问题名
     */
    private String questionName;

    /**
     *发布者id
     */
    private Integer publishId;

    /**
     *发布者用户名
     */
    private String publishName;



}
