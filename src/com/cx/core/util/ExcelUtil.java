package com.cx.core.util;

import com.cx.nsfw.user.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by cxspace on 16-8-24.
 */
public class ExcelUtil {


    //导出用户表

    public static void exportUserExcel(List<User> userList, ServletOutputStream outputStream) {
//        1.创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
//        1.1：创建合并单元格对象
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0,0,0,4);
//        1.2：样式
        HSSFCellStyle style1 = createCellStyle(workbook , (short) 12);

        HSSFCellStyle style2 = createCellStyle(workbook , (short) 10);

//
//        2.创建工作表
        HSSFSheet sheet = workbook.createSheet("用户列表");

        //设置单元格宽度
        sheet.setDefaultColumnWidth(20);
//        2.1：加载合并单元格对象
        sheet.addMergedRegion(cellRangeAddress);
//        3.创建行
//        3.1：创建头标题行,并且设置头标题
        HSSFRow row1 = sheet.createRow(0);
        //后面几列被合并,这里必须写第一列
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellStyle(style1);
        cell1.setCellValue("用户列表");

//        3.2：创建列标题行,并且设置列标题

        HSSFRow row2 = sheet.createRow(1);
        String[] titles = {"用户名","帐号","所属部门","性别","电子邮箱"};

        for (int i = 0 ; i < titles.length ; i++) {
            HSSFCell cell2 = row2.createCell(i);
            cell2.setCellStyle(style2);
            cell2.setCellValue(titles[i]);
        }

//        4.操作单元格
        if (userList != null){
            for (int j = 0 ; j < userList.size() ; j++){

                HSSFRow row = sheet.createRow(j + 2);
                HSSFCell cell11 = row.createCell(0);
                cell11.setCellValue(userList.get(j).getName());

                HSSFCell cell12 = row.createCell(1);
                cell12.setCellValue(userList.get(j).getAccount());

                HSSFCell cell13 = row.createCell(2);
                cell13.setCellValue(userList.get(j).getDept());

                HSSFCell cell14 = row.createCell(3);
                cell14.setCellValue(userList.get(j).isGender()?"男":"女");

                HSSFCell cell15 = row.createCell(4);
                cell15.setCellValue(userList.get(j).getEmail());

            }
        }
//        5.输出

        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
        创建单元格样式

         HSSFWorkbook workbook
          操作的工作薄

          short fontSize
          字体大小
     */

    public static HSSFCellStyle createCellStyle(HSSFWorkbook workbook , short fontSize){

        HSSFCellStyle style= workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //加粗
        font.setFontHeightInPoints(fontSize); //设置大小
        style.setFont(font);
        return style;
    }
}
