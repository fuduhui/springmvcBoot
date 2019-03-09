package com.service;

import com.model.Emp;
import com.model.Purchase;

import java.util.List;
import java.util.Map;

public interface ITestService {
    public Emp getEmp(String name) throws Exception;
    public List<Emp> getAllEmp() throws Exception;
    public Map<String,Emp> getAllEmpByHandler() throws Exception;
    public void testTrans(Emp emp, Purchase purchase) throws Exception;
}
