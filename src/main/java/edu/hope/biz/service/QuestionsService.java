package edu.hope.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.hope.biz.entity.edit.AddQuestionEditRequest;
import edu.hope.biz.entity.model.Questions;
import edu.hope.biz.entity.request.QuestionsPageQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
public interface QuestionsService extends IService<Questions> {

    /**
    * 发布问题
    * @param: request
    * @author: zhao
    * @Date: 0:17 2019/12/31 0031
     */
    void addQuestion(AddQuestionEditRequest request);

    /**
    * 修改问题内容
    * @param: request
    * @author: zhao
    * @Date: 0:20 2019/12/31 0031
     */
    void updateQuestion(AddQuestionEditRequest request);

    /**
    * 删除问题
    * @param: id
    * @author: zhao
    * @Date: 0:46 2019/12/31 0031
     */
    void deleteQuestion(Integer id);

    /**
    * 问题分页
    * @param:
    * @author: zhao
    * @Date: 16:20 2019/12/31 0031
     */
    IPage<Questions> questionsPage(QuestionsPageQuery pageQuery);

    /**
     * 获取所有问题
     * @param pageQuery
     * @return
     */
    IPage<Questions> getAllQuestions(QuestionsPageQuery pageQuery);

}
