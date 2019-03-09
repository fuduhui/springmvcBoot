package com.dbhandler;

import com.model.Emp;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.Map;

public class EmpHandler implements ResultHandler {

    private  Map<String,Emp> retMap ;

    @Override
    public void handleResult(ResultContext resultContext) {
        Emp emp=(Emp)resultContext.getResultObject();
        retMap.put(emp.getId(),emp);
    }
    public EmpHandler(Map<String,Emp> retMap){
        this.retMap=retMap;
    }
}
