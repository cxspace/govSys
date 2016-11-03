package com.cx.nsfw.user.service.impl;

import com.cx.core.exception.ServiceException;
import com.cx.core.service.BaseService;
import com.cx.core.service.impl.BaseServiceImpl;
import com.cx.core.util.ExcelUtil;
import com.cx.nsfw.role.entity.Role;
import com.cx.nsfw.user.dao.UserDao;
import com.cx.nsfw.user.entity.User;
import com.cx.nsfw.user.entity.UserRole;
import com.cx.nsfw.user.entity.UserRoleId;
import com.cx.nsfw.user.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.print.attribute.standard.JobMediaSheets;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by cxspace on 16-8-23.
 */

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }


    @Override
    public List<User> findUserByAccountAndPassword(String account, String password) {
        return userDao.findUsersByAccountAndPass(account,password);
    }

    @Override
    public void importExcel(File userExcel, String fileType) {

        try {

            FileInputStream fileInputStream = new FileInputStream(userExcel);

            boolean is03Excel = fileType.equals(".xls");

            //读取工作薄

            Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream) : new XSSFWorkbook(fileInputStream);
            //读取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            //读取行
            if (sheet.getPhysicalNumberOfRows() > 2)
            {
                User user = null;
                for (int k = 2 ; k < sheet.getPhysicalNumberOfRows(); k++ ){

                    //拿到行
                    Row row = sheet.getRow(k);
                    //实例化一个user对象
                    user = new User();

                    //读取单元格

                    //用户名
                    Cell cell1 = row.getCell(0);
                    user.setName(cell1.getStringCellValue());

                    //帐号
                    Cell cell2 = row.getCell(1);
                    user.setAccount(cell2.getStringCellValue());

                    //部门
                    Cell cell3 = row.getCell(2);
                    user.setDept(cell3.getStringCellValue());

                    //性别
                    Cell cell4 = row.getCell(3);
                    user.setGender(cell4.getStringCellValue().equals("男"));

                    //手机号
                    String mobile = "";
                    Cell cell5 = row.getCell(4);

                    try {
                        mobile = cell5.getStringCellValue();
                    }catch (Exception e){
                        //处理科学计数法表示的数据
                        double dMobile = cell5.getNumericCellValue();
                        mobile = BigDecimal.valueOf(dMobile).toString();
                    }
                    user.setMobile(mobile);

                    //电子邮箱
                    Cell cell6 = row.getCell(5);
                    user.setEmail(cell6.getStringCellValue());

                    //生日
                    Cell cell7 = row.getCell(6);
                    if (cell7.getDateCellValue() != null){
                        user.setBirthday(cell7.getDateCellValue());
                    }

                    //默认用户初始密码
                    user.setPassword("123456");
                    //默认用户状态为有效
                    user.setState(User.USER_STATE_VALID);

                    //保存用户
                    save(user);
                }
            }
            workbook.close();
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void exportExcel(List<User> userList, ServletOutputStream outputStream) {
        ExcelUtil.exportUserExcel(userList,outputStream);
    }

    @Override
    public List<User> findUserByAccountAndId(String id, String account) {
        return userDao.findUserByAccountAndId(id , account);
    }


    @Override
    public void saveUserAndRole(User user, String... roleIds) {

        save(user);

        if (roleIds != null){
            for (String roleId : roleIds){
                userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
            }
        }

    }

    @Override
    public void updateUserAndRole(User user, String... roleIds) {
        //删除历史
        userDao.deleteUserRoleByUserId(user.getId());

        update(user);

        if (roleIds != null){
            for (String roleId : roleIds){
                userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
            }
        }
    }

    @Override
    public List<UserRole> getUserRolesByUserId(String id) {
        return userDao.getUserRolesByUserId(id);
    }
}
