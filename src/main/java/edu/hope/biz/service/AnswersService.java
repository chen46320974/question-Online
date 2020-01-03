package edu.hope.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.hope.biz.entity.edit.AddAnswerEditRequest;
import edu.hope.biz.entity.model.Answers;
import edu.hope.biz.entity.request.AnswerPageQuery;
import edu.hope.biz.entity.request.SetRecommendRequest;
import edu.hope.biz.entity.response.MyReplayAnswerResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
public interface AnswersService extends IService<Answers> {


    /**
    * 新增答案
    * @param: request
    * @author: zhao
    * @Date: 12:32 2020/1/1 0001
     */
    void addAnswer(AddAnswerEditRequest request);

    /**
    * 设置推荐（选中）答案
    * @param: request
    * @author: zhao
    * @Date: 13:31 2020/1/1 0001
     */
    void SetRecommendAnswer(SetRecommendRequest request);

    /**
    * 问题 分页列表
    * @param: pageQuery
    * @author: zhao
    * @Date: 13:44 2020/1/1 0001
     */
    IPage<Answers> page(AnswerPageQuery pageQuery);

    /**
    * 查询 回复我的答案
    * @param:
    * @author: zhao
    * @Date: 23:05 2020/1/1 0001
     */
    List<MyReplayAnswerResponse> myReplayAnswer();



}
