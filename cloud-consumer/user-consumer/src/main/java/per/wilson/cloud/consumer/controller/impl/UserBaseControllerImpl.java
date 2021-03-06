package per.wilson.cloud.consumer.controller.impl;


import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import per.wilson.cloud.consumer.controller.UserBaseController;
import per.wilson.cloud.consumer.service.HelloFeignService;
import per.wilson.cloud.vo.AddUserBaseVO;
import per.wilson.cloud.vo.UpdateUserBaseVO;

import javax.annotation.Resource;

/**
 * UserBaseControllerImpl-控制器
 *
 * @author Wilson
 * @since 2018-10-28
 */
@RestController
@RequestMapping("/userBase")
public class UserBaseControllerImpl implements UserBaseController {
    @Resource
    private HelloFeignService helloFeignService;

    @Override
    @PostMapping("/")
    public Object add(@Validated @RequestBody AddUserBaseVO vo) {
        return helloFeignService.insert(vo);
    }

    @Override
    @GetMapping("/")
    public Object get(@ApiParam(name = "id", value = "id") @RequestParam String id) {
        return null;
    }

    @Override
    @GetMapping("/page")
    public Object page(@ApiParam(name = "page", value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "每页返回数", defaultValue = "15") @RequestParam(defaultValue = "15") Integer size) {
        return helloFeignService.pageUser(page, size);
//        return null;
    }

    @Override
    @PutMapping("/")
    public Object update(@Validated @RequestBody UpdateUserBaseVO vo) {
        return null;
    }

    @Override
    @DeleteMapping("/")
    public Object delete(@ApiParam(name = "id", value = "id") @RequestParam String id) {
        return null;
    }
}
