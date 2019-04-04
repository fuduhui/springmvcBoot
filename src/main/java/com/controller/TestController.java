package com.controller;

import com.model.Emp;
import com.model.Purchase;
import com.service.ITestService;
import com.util.lock.AFirst;
import com.util.lock.BFirst;
import com.util.lock.DeadLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Resource
    private ITestService testService;

    private static Logger logger= LoggerFactory.getLogger(TestController.class);
    private static Logger logger2=LoggerFactory.getLogger("logger.boot2");


    @RequestMapping(value = "/queryDbByMybatis")
    @ResponseBody
    public Emp queryDbByMybatis(String name) {
        try {
             Emp emp= testService.getEmp(name);
            return emp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/queryAllEmp")
    @ResponseBody
    public List<Emp> queryAllEmp() {
        try {
            List<Emp> list= testService.getAllEmp();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/queryAllEmpWithMap")
    @ResponseBody
    public Map<String,Emp> queryAllEmpWithMap() {
        try {
            Map<String,Emp> map= testService.getAllEmpByHandler();
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/testInsert")
    @ResponseBody
    public String testInsert(String empName,String role,String purName,String money) {
        try {
            Emp empParam=new Emp();
            empParam.setName(empName);
            empParam.setRole(role);

            Purchase purParam=new Purchase();
            purParam.setName(purName);
            purParam.setMoney(money);
            testService.testTrans(empParam,purParam);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 死循环
     * @return
     */
    @RequestMapping(value = "/deadRepeat")
    @ResponseBody
    public String badCpu() {
        try {
            while (true){
                int k=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 死锁
     * @return
     */
    @RequestMapping(value = "/deadLock")
    @ResponseBody
    public String deadLock() {
        try {
            Thread a = new Thread(new AFirst());
            Thread b = new Thread(new BFirst());
            a.start();
            b.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 内存溢出
     * @return
     */
    @RequestMapping(value = "/outMenory")
    @ResponseBody
    public String outMenory(){
        List<Object> list=new ArrayList<>();
        while (true){
            list.add(new Object());
        }
    }

    @RequestMapping(value = "/bootALog")
    @ResponseBody
    public String bootALog(String log){
        logger.info(log);
        return "succuss";
    }

    @RequestMapping(value = "/boot2Log")
    @ResponseBody
    public String boot2Log(String log){
        logger2.info(log);
        return "succuss";
    }


}
