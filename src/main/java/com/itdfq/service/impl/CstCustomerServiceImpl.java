package com.itdfq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdfq.dao.CstCustomerDAO;
import com.itdfq.model.Customer;
import com.itdfq.service.CstCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CstCustomerServiceImpl implements CstCustomerService {

    @Autowired
    private CstCustomerDAO cstCustomerDAO;

    @Transactional(readOnly = true)
    @Override
    public Customer findById(Long id) {
        return cstCustomerDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<Customer> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(cstCustomerDAO.findByPage());
    }

    @Override
    public PageInfo<Customer> findByTj(int pageNum, int pageSize, Customer customer) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(cstCustomerDAO.findByTj(customer));
    }

    @Override
    public List<Customer> myselect() {
        return cstCustomerDAO.myselect();
    }

    @Override
    public void insert(Customer customer) {
        cstCustomerDAO.insert(customer);
    }

    @Override
    public void update(Customer customer) {
        cstCustomerDAO.update(customer);
    }

    @Override
    public void deleteById(Long id) {
        cstCustomerDAO.deleteById(id);
    }

    @Override
    public void deleteSelect(List<String> custId) {
        cstCustomerDAO.deleteSelect(custId);
    }

}