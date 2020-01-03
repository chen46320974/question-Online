package edu.hope.biz.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hope.biz.common.constants.RStatus;
import edu.hope.biz.common.exception.ServiceException;
import edu.hope.biz.entity.edit.AddAnswerEditRequest;
import edu.hope.biz.entity.edit.UserCache;
import edu.hope.biz.entity.model.Answers;
import edu.hope.biz.entity.model.Questions;
import edu.hope.biz.entity.request.AnswerPageQuery;
import edu.hope.biz.entity.request.SetRecommendRequest;
import edu.hope.biz.entity.response.MyReplayAnswerResponse;
import edu.hope.biz.mapper.AnswersMapper;
import edu.hope.biz.service.AnswersService;
import edu.hope.biz.service.QuestionsService;
import edu.hope.biz.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Service
public class AnswersServiceImpl extends ServiceImpl<AnswersMapper, Answers> implements AnswersService {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private UsersService usersService;

    @Override
    public void addAnswer(AddAnswerEditRequest request) {
        Questions questions = questionsService.getById(request.getQuestionId());
        if (BeanUtil.isEmpty(questions)){
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR,"问题不存在");
        }
        Answers answers = new Answers();
        BeanUtils.copyProperties(request,answers);
        answers.setGmtCreate(new Date());
        answers.setIsRecommend(0);
        save(answers);
    }

    @Override
    public void SetRecommendAnswer(SetRecommendRequest request) {

        // 做问题和答案的校验
        Questions questions = questionsService.getById(request.getQuestionId());
        if (BeanUtil.isEmpty(questions)){
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR,"问题不存在");
        }
        Answers answers = getById(request.getAnswerId());
        if (BeanUtil.isEmpty(answers)){
            throw new ServiceException(RStatus.BUSINESS_PARAM_ERROR,"答案不存在");
        }
        questions.setRecommendAnswerId(answers.getId());
        questions.setStatus(1);
        questionsService.updateById(questions);
        answers.setIsRecommend(1);
        updateById(answers);

    }

    @Override
    public IPage<Answers> page(AnswerPageQuery pageQuery) {
        return baseMapper.page(pageQuery.getPage(),pageQuery);
    }

    @Override
    public List<MyReplayAnswerResponse> myReplayAnswer() {
        UserCache userCache = usersService.getLoginUser();
        List<MyReplayAnswerResponse> answersList = baseMapper.getMyReplayAnswerList(userCache.getId());
        return answersList;
    }
}
