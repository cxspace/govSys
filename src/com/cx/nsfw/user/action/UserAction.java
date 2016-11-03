package com.cx.nsfw.user.action;

import com.cx.core.action.BaseAction;
import com.cx.core.exception.ActionException;
import com.cx.core.exception.ServiceException;
import com.cx.core.exception.SysException;
import com.cx.nsfw.role.entity.Role;
import com.cx.nsfw.role.service.RoleService;
import com.cx.nsfw.user.entity.User;
import com.cx.nsfw.user.entity.UserRole;
import com.cx.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by cxspace on 16-8-23.
 */
public class UserAction extends BaseAction{

    //这里必须时userService才能在容器中找到userService
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    private List<User> userList;

    private String[] userRoleIds;


    private File userExcel;
    private String userExcelContentType;
    private String userExcelFileName;


    private File headImg;
    private String headImgContentType;
    private String headImgFileName;

    public File getUserExcel() {
        return userExcel;
    }

    public void setUserExcel(File userExcel) {
        this.userExcel = userExcel;
    }

    public String getUserExcelContentType() {
        return userExcelContentType;
    }

    public void setUserExcelContentType(String userExcelContentType) {
        this.userExcelContentType = userExcelContentType;
    }

    public String getUserExcelFileName() {
        return userExcelFileName;
    }

    public String[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public void setUserExcelFileName(String userExcelFileName) {
        this.userExcelFileName = userExcelFileName;
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


    //验证账户唯一
    public void verifyAccount(){

        //方式一
        //1.获取账号
       // if (user != null && user.getAccount() != null && !user.getAccount().equals("")){
            //2.到数据库中验证
        //}

        //方式二
        if (user != null && StringUtils.isNotBlank(user.getAccount())) {

            try {
                List<User> list = userService.findUserByAccountAndId(user.getId(), user.getAccount());
                String strResult = "true";
                if (list != null && list.size() > 0) {
                    strResult = "false";
                }
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("text/html");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(strResult.getBytes());
                outputStream.close();
            }catch (Exception e){
               e.printStackTrace();
            }
        }

    }


    //导出用户列表
    public void exportExcel() throws SysException{
        //实现导出
        //1.查找用户列表
        userList = userService.findObjects();
        //2.导出

        try {

            //拿到response对象
            HttpServletResponse response = ServletActionContext.getResponse();
            //设置发送到客户端的内容
            response.setContentType("application/x-excel");
            //new String 是为了兼容浏览器
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));

            //导出1到浏览器的输出流
            ServletOutputStream outputStream = response.getOutputStream();

            //调用导出业务
            userService.exportExcel(userList,outputStream);

            if (outputStream != null){
                outputStream.close();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //导入用户列表
    public String importExcel(){

        if (userExcel != null){

            String fileType = userExcelFileName.substring(userExcelFileName.lastIndexOf("."));

            System.out.println(fileType);

            //判断是否是excel
            if (fileType.equals(".xls") || fileType.equals(".xlsx")){
                userService.importExcel(userExcel,fileType);
            }
        }
        return "list";
    }

    //列表页面
    public String listUI() throws SysException{

        try {
            userList = userService.findObjects();
        }catch (Exception e){
            throw new ActionException("action 出现异常："+e.getMessage());
        }
            return "listUI";
    }
    //跳转到新增页面
    public String addUI() throws ServiceException {

        //查出角色列表
        ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());

        return "addUI";

    }
    //保存新增
    public String add(){

            if (user != null) {

                saveHeadImg();

            }
        userService.saveUserAndRole(user,userRoleIds);

        return "list";
    }
    //跳转到编辑页面
    public String editUI() throws ServiceException {

        ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());

        //找出原来的信息
        if (user != null && user.getId() != null) {
            user = userService.findObjectById(user.getId());

            List<UserRole> list = userService.getUserRolesByUserId(user.getId());

            if (list != null && list.size()>0){

                userRoleIds = new String[list.size()];

                for (int i = 0 ; i < list.size() ; i++){
                    userRoleIds[i] = list.get(i).getId().getRole().getRoleId();
                }
            }


        }
            return "editUI";
    }
    //保存编辑
    public String edit(){

        try {

            if (user != null) {

                saveHeadImg();

                userService.updateUserAndRole(user,userRoleIds);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "list";
    }
    //删除
    public String delete(){
        if (user != null && user.getId() != null)
        {
            userService.delete(user.getId());
        }
        return "list";
    }


    //批量删除
    public String deleteSelected(){

        if (selectedRow != null){

            for (String id : selectedRow){
                userService.delete(id);
            }
        }

        return "list";
    }

    public void saveHeadImg()
    {
        try {
            //处理头像上传
            if (headImg != null) {

                //1.保存头像到upload/user,拿到绝对路径
                String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");

                //随机重命名
                //获取保存路径绝对地址,解决文件名重复和文件覆盖问题
                String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));

                FileUtils.copyFile(headImg, new File(filePath, fileName));

                //2.设置文件头像路径
                user.setHeadImg("user/" + fileName);

            }
        }catch (Exception e){
            LOG.error(e.getMessage());
            e.printStackTrace();
        }

    }

}

