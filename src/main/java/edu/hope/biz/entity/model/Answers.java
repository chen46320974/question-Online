package edu.hope.biz.entity.model;

import edu.hope.biz.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Answers extends BaseEntity<Answers> {

    private static final long serialVersionUID = 1L;


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


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
