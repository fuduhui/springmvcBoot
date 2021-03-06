package com.service.impl;

import com.dao.EmpDao;
import com.dao.PurchaseDao;
import com.dbhandler.EmpHandler;
import com.model.Emp;
import com.model.Purchase;
import com.service.ITestService;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements ITestService {


    @Resource
    private EmpDao empDao;

    @Resource
    private PurchaseDao purchaseDao;

    @Resource
    private DataSourceTransactionManager  txManager;

    @Override
    public Emp getEmp(String name) throws Exception {
        Map<String,Object> param=new HashMap<>();
        param.put("name",name);
        return empDao.getEmp(param);
    }

    @Override
    public List<Emp> getAllEmp() throws Exception {
        return empDao.getAllEmp(null);
    }

    @Override
    public Map<String, Emp> getAllEmpByHandler() throws Exception {
        Map<String,Emp> retMap=new HashMap<>();
        ResultHandler resultHandler=new EmpHandler(retMap);
        empDao.getAllEmp(null,resultHandler);
        return retMap;
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public void testTrans(Emp emp, Purchase purchase) throws Exception {
        int k=1;
        empDao.insertEmp(emp);
        int j=5/k;
        purchaseDao.insertPurchase(purchase);
    }

    //以编程方式控制事务
 /*  public void testTrans(Emp emp, Purchase purchase) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);

        try {
            int k=1;
            empDao.insertEmp(emp);
            int j=5/k;
            purchaseDao.insertPurchase(purchase);
        }
        catch (Exception ex) {
             txManager.rollback(status);
           throw ex;
        }
        txManager.commit(status);

    }*/


}
