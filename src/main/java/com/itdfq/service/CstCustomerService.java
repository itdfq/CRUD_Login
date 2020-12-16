package com.itdfq.service;

import com.github.pagehelper.PageInfo;
import com.itdfq.model.Customer;

import java.util.List;

/**
 *
 */
public interface CstCustomerService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link Customer}
     */
    Customer findById(Long id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link Customer}
     */
    PageInfo<Customer> findByPage(int pageNum, int pageSize);

//    分页条件查询
PageInfo<Customer> findByTj(int pageNum, int pageSize,Customer customer);
    List<Customer> myselect();

    /**
     * 新增
     *
     * @param customer
     */
    void insert(Customer customer);

    /**
     * 修改
     *
     * @param customer
     */
    void update(Customer customer);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Long id);

    /**
     * 批量删除
     * @param custId
     */
    void deleteSelect(List<String> custId);



}