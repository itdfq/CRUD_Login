package com.itdfq.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdfq.model.Customer;

import com.itdfq.service.CstCustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/dfq")
public class CstCustomerController {
    @Autowired
    private CstCustomerService cstCustomerService;

    Map<String,Object> map = new HashMap<>();


    @RequestMapping("/myselect")
    public Map<String,Object> mySelect(){
        map.clear();
        try {
            List<Customer> all = cstCustomerService.myselect();
//            System.out.println(all);
            map.put("msg",1);
            map.put("data",all);
        }catch (Exception e){
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @RequestMapping("/findByPage")
    public Map<String,Object>selectByFy12(@RequestParam(required=false,defaultValue="1") int page,
                                          @RequestParam(required=false,defaultValue = "10") int limit) {
        // 使用Pagehelper传入当前页数和页面显示条数会自动为我们的select语句加上limit查询
        // 从他的下一条sql开始分页
        try {

            map.clear();
            PageHelper.startPage(page, limit);
            PageInfo<Customer> byPage = cstCustomerService.findByPage(page, limit);// 这是我们的sql
// 使用pageInfo包装查询
            map.put("code",0);
            map.put("msg","1");
            map.put("count",byPage.getTotal());
            map.put("data",byPage.getList());
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

//    分页条件查询
@RequestMapping("/selectByTj")
public Map<String,Object>selectByTj( @RequestParam(required=false,defaultValue="1") int page,
                                       @RequestParam(required=false,defaultValue = "10") int limit,@RequestBody Customer customer) {
    // 使用Pagehelper传入当前页数和页面显示条数会自动为我们的select语句加上limit查询
    // 从他的下一条sql开始分页
    System.out.println(customer);
    try {
        map.clear();
        PageHelper.startPage(page, limit);
        PageInfo<Customer> byPage = cstCustomerService.findByTj(page, limit,customer);// 这是我们的sql
// 使用pageInfo包装查询
        map.put("code",0);
        map.put("msg","1");
        map.put("count",byPage.getTotal());
        map.put("data",byPage.getList());
    } catch (Exception e) {
        map.put("msg",e.getMessage());
        e.printStackTrace();
    }
    return map;
}
    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public Customer findById(@ApiParam("ID") @PathVariable("id") Long id) {
        return cstCustomerService.findById(id);
    }

    @RequestMapping("/")
    @ApiOperation("分页查询")
    public PageInfo<Customer> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                         @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return cstCustomerService.findByPage(pageNum, pageSize);
    }

    @RequestMapping("/save")
    @ApiOperation("新增")
    public Map<String,Object> insert(@RequestBody Customer customer) {
        System.out.println(customer);
        map.clear();
        try {
            cstCustomerService.insert(customer);
            map.put("msg",1);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/update")
    @ApiOperation("修改")
    public Map<String,Object> update(@RequestBody Customer customer) {
        System.out.println(customer);
        map.clear();
        try {
            cstCustomerService.update(customer);
            map.put("msg",1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @RequestMapping("/deleteById")
    public Map<String,Object> deleteById(@RequestBody Customer customer) {
        System.out.println(customer);
        map.clear();
        try {
            cstCustomerService.deleteById(customer.getCustId());
            map.put("msg",1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @RequestMapping("/deleteSelect")
    public Map<String,Object> deleteSelect(String custId){
        map.clear();
        try {
            List<String> list = new ArrayList<>();
            String[] strs = custId.split(",");
            for (String str : strs) {
                list.add(str);
            }
            cstCustomerService.deleteSelect(list);
            map.put("msg",1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        return map;
    }
}
