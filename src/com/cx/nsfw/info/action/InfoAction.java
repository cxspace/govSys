package com.cx.nsfw.info.action;

import com.cx.core.action.BaseAction;
import com.cx.core.util.QueryHelper;
import com.cx.nsfw.info.entity.Info;
import com.cx.nsfw.info.service.InfoService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cxspace on 16-10-27.
 */
public class InfoAction extends BaseAction{

    @Resource
    private InfoService infoService;

    private List<Info> infoList;

    private Info info;

    private String strTitle;

    //列表页面
    public String listUI() throws Exception{

        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);

        QueryHelper queryHelper = new QueryHelper(Info.class,"i");

        try {

            if (info != null){

                if (StringUtils.isNotBlank(info.getTitle())){

                    info.setTitle(URLDecoder.decode(info.getTitle(),"utf-8"));

                    queryHelper.addCondition("i.title like ?", "%" + info.getTitle() +"%");
                }

                queryHelper.addCondition("i.state = ?","1");

            }

            queryHelper.addOrderByProperty("i.createTime",QueryHelper.ORDER_BY_DESC);

            infoList = infoService.findObjects(queryHelper);


        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return "listUI";

    }

    //跳转到新增页面

    public String addUI()
    {
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);

        info = new Info();

        info.setCreateTime(new Timestamp(new Date().getTime()));

        return "addUI";
    }

    //保存新增
    public String add(){

        try {
            if (info != null){
                infoService.save(info);
            }

            info = null;

        }catch (Exception e){
            e.printStackTrace();
        }

        return "list";
    }


    //跳转到编辑页面
    public String editUI(){

        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);

        if (info != null && info.getInfoId() != null){

            strTitle = info.getTitle();

            info = infoService.findObjectById(info.getInfoId());

        }

        return "editUI";

    }

    //保存编辑
    public String edit(){

        try {
            if (info != null){
                infoService.update(info);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "list";
    }


    public String delete(){

        if (info != null && info.getInfoId() != null){

            strTitle = info.getTitle();

            infoService.delete(info.getInfoId());
        }


        return "list";
    }

    //批量删除
    public String deleteSelected(){

        if (selectedRow != null){
            for (String id:selectedRow){
                infoService.delete(id);
            }
        }

        return "list";
    }

    public void publicInfo(){

        try {

            if (info != null){

                //拿到要更改状态的记录
                Info tem = infoService.findObjectById(info.getInfoId());

                tem.setState(info.getState());

                infoService.update(tem);

                //输出更新结果到前台
                HttpServletResponse response = ServletActionContext.getResponse();

                response.setContentType("text/html");

                ServletOutputStream outputStream = response.getOutputStream();

                outputStream.write("更新状态成功".getBytes("utf-8"));

                outputStream.close();

            }


        }catch (Exception e){

        }

    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public InfoService getInfoService() {
        return infoService;
    }

    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }


    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }
}
