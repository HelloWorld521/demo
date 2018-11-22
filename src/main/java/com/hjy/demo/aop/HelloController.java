package com.hjy.demo.aop;

import org.springframework.web.bind.annotation.*;

/**
 * example
 *
 * @author hjy
 * @date 2018/11/20
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> hello(@RequestParam String name) {
        Result success = ResultUtil.success("Hello " + name);
        return success;
    }
}
