package edu.hope.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hope.biz.common.resonse.R;
import edu.hope.biz.common.resonse.RPage;
import edu.hope.biz.entity.edit.AddQuestionEditRequest;
import edu.hope.biz.entity.model.Questions;
import edu.hope.biz.entity.request.QuestionsPageQuery;
import edu.hope.biz.service.QuestionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoXinYing
 * @since 2019-12-29
 */
@Validated
@RestController
@Api(tags = {"问题接口"})
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @ApiOperation(value = "发布问题")
    @PostMapping("/publish")
    public R publishQuestion(@Validated @RequestBody AddQuestionEditRequest request){
        questionsService.addQuestion(request);
        return R.ok();
    }

    @ApiOperation(value = "修改问题")
    @PostMapping("/update")
    public R updateQuestion(@Validated @RequestBody AddQuestionEditRequest request){
        questionsService.updateQuestion(request);
        return R.ok();
    }

    @ApiOperation(value = "删除问题")
    @PostMapping("/delete")
    public R deleteQuestion(@NotBlank(message = "id不能为空") Integer id){
        questionsService.deleteQuestion(id);
        return R.ok();
    }

    @ApiOperation(value = "问题分页")
    @PostMapping("/page")
    public RPage questionPage(@Validated @RequestBody QuestionsPageQuery pageQuery){
        IPage<Questions> page = questionsService.questionsPage(pageQuery);
        return new RPage(page);
    }

    @ApiOperation(value = "所有问题")
    @PostMapping("/all")
    public RPage getAll(@Validated @RequestBody QuestionsPageQuery pageQuery){
        IPage<Questions> page = questionsService.getAllQuestions(pageQuery);
        return new RPage(page);
    }

}
