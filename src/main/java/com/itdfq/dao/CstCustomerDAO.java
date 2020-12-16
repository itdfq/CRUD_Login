package com.itdfq.dao;

import com.github.pagehelper.Page;
import com.itdfq.model.Customer;

import java.util.List;

/**
 *
 */
public interface CstCustomerDAO {

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
     * @return {@link Customer}
     */
    Page<Customer> findByPage();

    /**
     * 分页查询
     *
     * @return {@link Customer}
     */
    Page<Customer> findByTj(Customer customer);

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

    List<Customer> myselect();

    void deleteSelect(List<String> custId);

}