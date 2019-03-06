package poi;

import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PoiWithCallBack {

    public static void main(String[] args) throws Exception{

        //数据初始化
        Student s1=new Student("张三",188,"张三比较高");
        Student s2=new Student("李四",150,"李四比较矮");
        final List<Student> list=new ArrayList<>();
        list.add(s1);
        list.add(s2);

        //excel导出需要的表头
        String[] headerArr={"姓名","身高","备注"};


        //回调函数
        CellValCallBack callBack= new CellValCallBack() {
            @Override
            String getCellVal(int dadaRowIndex,int cellIndex) {
                Student stu=list.get(dadaRowIndex);
                switch (cellIndex)
                {
                    case 0:
                        return stu.getName();
                    case 1:
                        return stu.getHeight()+"";
                    case 2:
                        return stu.getRemark();
                }
                return null;
            }
        };


        //导出excel
        exportWithCallBack(headerArr,list.size(),callBack);
    }

    public static void exportWithCallBack(String[] headerArr,int dataSize,CellValCallBack cellValCallBack) throws Exception{
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

        //生成数据--通过回调
        for(int dadaRowIndex=0;dadaRowIndex<dataSize;dadaRowIndex++){
            HSSFRow dataRow = sheet.createRow(rowIndex++);
            for(int dataIndex=0;dataIndex<headerArr.length;dataIndex++){
                HSSFCell cell=dataRow.createCell(dataIndex);
                cell.setCellValue(cellValCallBack.getCellVal(dadaRowIndex,dataIndex));
            }

        }

        OutputStream out=new FileOutputStream(new File("D:\\callBack.xls"));
        workbook.write(out);

    }


}
