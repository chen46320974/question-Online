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
public class Questions extends BaseEntity<Questions> {

    private static final long serialVersionUID = 1L;


    /**
     * 问题名
     */
    private String questionName;

    /**
     * 悬赏积分
     */
    private Integer points;

    /**
     * 问题内容
     */
    private String content;

    /**
     * 解决状态
     */
    private Integer status;

    /**
     *发布者id
     */
    private Integer publishId;

    /**
     *发布者用户名
     */
    private String publishName;

    /**
     *推荐答案
     */
    private Integer recommendAnswerId;



}
