package edu.hope.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hope.biz.entity.model.Answers;
import edu.hope.biz.entity.request.AnswerPageQuery;
import edu.hope.biz.entity.response.MyReplayAnswerResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoMaoJie
 * @since 2019-12-29
 */
public interface AnswersMapper extends BaseMapper<Answers> {

    /**
     * 问题 分页列表
     * @param: pageQuery
     * @author: zhao
     * @Date: 13:44 2020/1/1 0001
     */
    IPage<Answers> page(IPage page,@Param("query") AnswerPageQuery pageQuery);

    /**
    * 获取回复我的问题的 答案
    * @param:
    * @author: zhao
    * @Date: 23:22 2020/1/1 0001
     */
    List<MyReplayAnswerResponse> getMyReplayAnswerList(@Param("id") Integer id);

    /**
    * 测试接口
    * @param:
    * @author: zhao
    * @Date: 16:21 2020/1/1 0001
     */
    String getTest();

}
