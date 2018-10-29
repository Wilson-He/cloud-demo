package per.wilson.cloud.provider.controller;


import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import per.wilson.cloud.model.UserBase;
import per.wilson.cloud.service.UserBaseService;
import per.wilson.cloud.vo.AddUserBaseVO;

import javax.annotation.Resource;

/**
 * UserBaseControllerImpl-控制器
 *
 * @author Wilson
 * @since 2018-10-28
 */
@RestController
@RequestMapping("/userBase")
public class UserBaseControllerImpl {
    @Resource
    private UserBaseService userBaseService;

    @PostMapping("/")
    public Object add(@Validated @RequestBody AddUserBaseVO vo) {
        UserBase userBase = vo.toUserBase();
        userBaseService.insert(userBase);
        return userBase;
//        return null;
    }

    @GetMapping("/")
    public Object get(@ApiParam(name = "id", value = "id") @RequestParam String id) {
        return null;
    }

    @GetMapping("/page")
    public Object page(@ApiParam(name = "page", value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "每页返回数", defaultValue = "15") @RequestParam(defaultValue = "15") Integer size) {
        return userBaseService.map(UserBase::getId, UserBase::getName);
//        return null;
    }

  }
