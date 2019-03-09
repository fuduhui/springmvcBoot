package com.dao;

import com.model.Emp;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface EmpDao {
     Emp getEmp(Map<String, Object> param);
     int insertEmp(Emp emp);
      List<Emp> getAllEmp(Map<String, Object> param);
      void  getAllEmp(Map<String, Object> param, ResultHandler resultHandler);
}
