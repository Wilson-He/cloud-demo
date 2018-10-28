package per.wilson.cloud.consumer.controller;


import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import per.wilson.cloud.consumer.dto.GetUserBaseDTO;
import per.wilson.cloud.consumer.dto.PageUserBaseDTO;
import per.wilson.cloud.consumer.vo.AddUserBaseVO;
import per.wilson.cloud.consumer.vo.UpdateUserBaseVO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * UserBaseController-控制器
 *
 * @author Wilson
 * @since 2018-10-28
 */
@Validated
@Api(value = "UserBaseController", tags = {""})
public interface UserBaseController {
    @ApiOperation(value = "[Doc]添加", notes = "添加")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = Object.class)})
    @PostMapping("/")
    Object add(AddUserBaseVO vo);

    @ApiOperation(value = "[Doc]查看详情", notes = "查看详情")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = Object.class),
            @ApiResponse(code = 201, message = "data", response = GetUserBaseDTO.class)})
    @GetMapping("/")
    Object get(@NotBlank @ApiParam(name = "id", value = "id", required = true) @RequestParam String id);

    @ApiOperation(value = "[Doc]分页查询", notes = "分页查询")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = Object.class),
            @ApiResponse(code = 201, message = "data", response = PageUserBaseDTO.class)})
    @GetMapping("/page")
    Object page(@Min(1) @ApiParam(name = "page", value = "页码", defaultValue = "1", required = true) @RequestParam(defaultValue = "1") Integer page,
                @Min(1) @ApiParam(name = "size", value = "每页返回数", defaultValue = "15", required = true) @RequestParam(defaultValue = "15") Integer size);

    @ApiOperation(value = "[Doc]更新", notes = "更新")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = Object.class)})
    @PutMapping("/")
    Object update(@Validated @RequestBody UpdateUserBaseVO vo);

    @ApiOperation(value = "[Doc]删除", notes = "删除")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "访问成功", response = Object.class)})
    @DeleteMapping("/")
    Object delete(@ApiParam(name = "id", value = "id") @RequestParam String id);
 }
