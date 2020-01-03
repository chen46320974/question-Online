package edu.hope.biz.controller;


import edu.hope.biz.common.resonse.R;
import edu.hope.biz.common.resonse.RPage;
import edu.hope.biz.entity.edit.AddAnswerEditRequest;
import edu.hope.biz.entity.request.AnswerPageQuery;
import edu.hope.biz.entity.request.SetRecommendRequest;
import edu.hope.biz.entity.response.MyReplayAnswerResponse;
import edu.hope.biz.service.AnswersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Validated
@RestController
@Api(tags = {"答案接口"})
@RequestMapping("/answers")
public class AnswersController {

    @Autowired
    private AnswersService answersService;

    @ApiOperation(value = "新增答案")
    @PostMapping("/add")
    public R addAnswer(@Validated @RequestBody AddAnswerEditRequest request) {
        answersService.addAnswer(request);
        return R.ok();
    }

    @ApiOperation(value = "答案列表")
    @PostMapping("/page")
    public RPage answerPage(@Validated @RequestBody AnswerPageQuery pageQuery) {
        return new RPage(answersService.page(pageQuery));
    }

    @ApiOperation(value = "设置选中答案")
    @PostMapping("/setRecommend")
    public R setRecommend(@Validated @RequestBody SetRecommendRequest request) {
        answersService.SetRecommendAnswer(request);
        return R.ok();
    }

    @ApiOperation(value = "我的回复")
    @PostMapping("/myReplay")
    public R myReplay() {
        List<MyReplayAnswerResponse> list =  answersService.myReplayAnswer();
        return R.ok(list);
    }


}
