package edu.hope.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hope.biz.common.constants.RStatus;
import edu.hope.biz.common.exception.ServiceException;
import edu.hope.biz.common.util.UserUtil;
import edu.hope.biz.entity.edit.AddQuestionEditRequest;
import edu.hope.biz.entity.edit.UserCache;
import edu.hope.biz.entity.model.Questions;
import edu.hope.biz.entity.request.QuestionsPageQuery;
import edu.hope.biz.mapper.QuestionsMapper;
import edu.hope.biz.service.QuestionsService;
import edu.hope.biz.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Service
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsService {

    @Autowired
    private UsersService usersService;

//    @Autowired
//    private QuestionsMapper questionsMapper;

    @Override
    public void addQuestion(AddQuestionEditRequest request) {
        Questions questions = new Questions();
        BeanUtils.copyProperties(request,questions);
        questions.setGmtCreate(new Date());
        questions.setStatus(0);
        UserCache userCache = usersService.getLoginUser();
        questions.setPublishId(userCache.getId());
        questions.setPublishName(userCache.getUsername());
        save(questions);


    }

    @Override
    public void updateQuestion(AddQuestionEditRequest request) {

        //首先判断id不能为空
        if(StringUtils.isEmpty(request.getId())){
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR,"id不能为空！");
        }

        //判断是否存在
        Questions questions = getById(request.getId());
        if(BeanUtil.isEmpty(questions)){
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR,"问题不存在！");
        }
        BeanUtils.copyProperties(request,questions);
        updateById(questions);

    }

    @Override
    public void deleteQuestion(Integer id) {
        Questions questions = getById(id);
        if(BeanUtil.isEmpty(questions)){
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR,"问题不存在！");
        }
        questions.setIsDeleted(true);
        updateById(questions);
    }

    @Override
    public IPage<Questions> questionsPage(QuestionsPageQuery pageQuery) {
        UserCache userCache = usersService.getLoginUser();
        pageQuery.setPublishId(userCache.getId());
        return baseMapper.getQuestionsPage(pageQuery.getPage(),pageQuery);
    }

    @Override
    public IPage<Questions> getAllQuestions(QuestionsPageQuery pageQuery) {
        return baseMapper.getQuestionsPage(pageQuery.getPage(),pageQuery);
    }
}
