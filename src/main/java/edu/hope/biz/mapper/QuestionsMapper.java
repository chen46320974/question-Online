package edu.hope.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hope.biz.entity.model.Questions;
import edu.hope.biz.entity.request.QuestionsPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoMaoJie
 * @since 2019-12-29
 */
public interface QuestionsMapper extends BaseMapper<Questions> {


    /**
    * 问题分页
    * @param: page
    * @param: query
    * @author: zhao
    * @Date: 16:34 2019/12/31 0031
     */
    @Mapper
    IPage<Questions> getQuestionsPage(IPage page,@Param("query") QuestionsPageQuery query);

    /**
    * 测试
    * @param:
    * @author: zhao
    * @Date: 16:25 2020/1/1 0001
     */
    @Mapper
    String test();
}
