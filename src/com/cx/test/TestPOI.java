package com.cx.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by cxspace on 16-8-24.
 */
public class TestPOI {


    //03版本excel
    public void testWrite03Excel() throws Exception{
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("helloWorld");
        //创建行:创建第4行
        HSSFRow row = sheet.createRow(3);
        //创建单元格,在第三行第4列
        HSSFCell cell = row.createCell(3);

        cell.setCellValue("helloWorld");

        //输出到硬盘
        FileOutputStream outputStream =  new FileOutputStream("/home/cxspace/myPrj/governmentSys/doc/测试.xls");
        //输出
        workbook.write(outputStream);

        workbook.close();
        outputStream.close();
    }


    //07版本excel
    public void testWrite07Excel() throws Exception{
        // 创建工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = workbook.createSheet("helloWorld");
        //创建行:创建第4行
        XSSFRow row = sheet.createRow(3);
        //创建单元格,在第三行第4列
        XSSFCell cell = row.createCell(3);

        cell.setCellValue("helloWorld");

        //输出到硬盘
        FileOutputStream outputStream =  new FileOutputStream("/home/cxspace/myPrj/governmentSys/doc/测试.xlsx");
        //输出
        workbook.write(outputStream);

        workbook.close();
        outputStream.close();
    }


    public void testReab03Excel() throws Exception{

        FileInputStream fileInputStream = new FileInputStream("/home/cxspace/myPrj/governmentSys/doc/测试.xls");

        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        //读取第一个工作表
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.getRow(3);
        HSSFCell cell = row.getCell(3);

        System.out.println("第四行第四列的值"+cell.getStringCellValue());
    }


    //读取03或07
    public void testRead03And07Excel() throws Exception {

        String fileName = "/home/cxspace/myPrj/governmentSys/doc/测试.xlsx";

        String filetype = fileName.substring(fileName.lastIndexOf("."));

        if (filetype.equals(".xls")||filetype.equals(".xlsx")) {//判断是不是excel文档

            boolean is03Excel = filetype.equals(".xls");

            FileInputStream inputStream = new FileInputStream(fileName);

            Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            Row row = sheet.getRow(3);

            Cell cell = row.getCell(3);

            System.out.println(cell.getStringCellValue());

            workbook.close();
            inputStream.close();

        }
    }

    @Test
     /*
     合并单元格的对象(CellRangeAddress)属于工作薄,运用于工作表
     CellRangeAddress(int firstRow , int lastRow , int firstCol , int lastCol);
                      起始行号     结束行号     起始列号     结束列号
     */

    public void testExcelStyle() throws Exception{

        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        /*
            样式
         */

        //合并第四行四五六列单元格
        CellRangeAddress cellRangeAddress = new CellRangeAddress(3,3,3,5);

        //创建单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        //水平居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗的字体
        font.setFontHeightInPoints((short)16);

        //加载字体到样式
        style.setFont(font);


        //单元格背景
        //设置填充模式
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //设置填充背景色
        style.setFillBackgroundColor(HSSFColor.YELLOW.index);
        //设置填充前景
        style.setFillForegroundColor(HSSFColor.RED.index);
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("helloWorld");

        sheet.addMergedRegion(cellRangeAddress);
        //创建行:创建第4行
        HSSFRow row = sheet.createRow(3);
        //创建单元格,在第三行第4列
        HSSFCell cell = row.createCell(3);

        cell.setCellStyle(style);
        cell.setCellValue("helloWorld");

        //输出到硬盘
        FileOutputStream outputStream =  new FileOutputStream("/home/cxspace/myPrj/governmentSys/doc/测试.xls");
        //输出
        workbook.write(outputStream);

        workbook.close();
        outputStream.close();
    }

}






