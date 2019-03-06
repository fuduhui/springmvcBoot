package poi;

import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 不采用回调方式生成excel数据
 */
public class PoiNoCallBack {

    public static void main(String[] args) throws Exception{

        //数据初始化
        Student s1=new Student("张三",188,"张三比较高");
        Student s2=new Student("李四",150,"李四比较矮");
        List<Student> list=new ArrayList<>();
        list.add(s1);
        list.add(s2);

        //excel导出需要的表头
        String[] headerArr={"姓名","身高","备注"};


        //转化成导出excel需要的数据格式
        List<String[]> excelDataList=new ArrayList<>();
        for(Student stu:list){
            String[] dataArr=new String[3];
            dataArr[0]=stu.getName();
            dataArr[1]=stu.getHeight()+"";
            dataArr[2]=stu.getRemark();
            excelDataList.add(dataArr);
        }

        //导出excel
        exportWithNoCallBack(headerArr,excelDataList);
    }

    public static void exportWithNoCallBack(String[] headerArr,List<String[]> excelDataList) throws Exception{
        int rowIndex=0;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("testSheet");
        HSSFRow headerRow = sheet.createRow(rowIndex++);

        //表头加粗
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle headerStyle= workbook.createCellStyle();
        headerStyle.setFont(font);

        //生成表头
        int headerCellIndex=0;
        for(String header:headerArr){
            HSSFCell cell=headerRow.createCell(headerCellIndex++);
            cell.setCellValue(header);
            cell.setCellStyle(headerStyle);
        }

        //生成数据
        for(String[] data:excelDataList){
            HSSFRow dataRow = sheet.createRow(rowIndex++);
            int dataIndex=0;
            for(String val:data){
                HSSFCell cell=dataRow.createCell(dataIndex++);
                cell.setCellValue(val);
            }
        }

        OutputStream out=new FileOutputStream(new File("D:\\noCallBack.xls"));
        workbook.write(out);

    }


}
