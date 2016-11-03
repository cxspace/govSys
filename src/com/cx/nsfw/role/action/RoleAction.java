package com.cx.nsfw.role.action;

import com.cx.core.action.BaseAction;
import com.cx.core.constant.Constant;
import com.cx.nsfw.role.entity.Role;
import com.cx.nsfw.role.entity.RolePrivilege;
import com.cx.nsfw.role.entity.RolePrivilegeId;
import com.cx.nsfw.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;


import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by cx on 2016/10/21.
 */
public class RoleAction extends BaseAction{

    @Resource
    private RoleService roleService;
    private List<Role> roleList;
    private Role role;
    //角色权限数组
    private String[] privilegeIds;


    public String listUI() throws Exception{
        //加载权限集合,存入栈顶,只要前加#便可拿出
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        try {
            roleList = roleService.findObjects();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "listUI";
    }


    public String addUI(){

        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);

        return "addUI";
    }

    //保存角色以及对应的权限
    public String add(){

        try{
            if (role != null){

                if(privilegeIds != null){

                    HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();

                    for(int i = 0; i < privilegeIds.length ; i++ ){
                        //把权限列表添加到角色里面
                        set.add(new RolePrivilege(new RolePrivilegeId(role,privilegeIds[i])));
                    }

                    role.setRolePrivileges(set);


                }

                roleService.save(role);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "list";
    }


    //删除
    public String delete(){
        if(role != null && role.getRoleId() != null){
            roleService.delete(role.getRoleId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if(selectedRow != null){
            for(String id: selectedRow){
                roleService.delete(id);
            }
        }
        return "list";
    }


    //编辑页面
    public String editUI(){

        ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);

        if (role != null && role.getRoleId() != null){
            //查到这个角色
            role = roleService.findObjectById(role.getRoleId());

            //处理数据回显

            if (role.getRolePrivileges() != null){
                privilegeIds = new String[role.getRolePrivileges().size()];

                int i = 0;

                for (RolePrivilege rp : role.getRolePrivileges())
                {
                    privilegeIds[i++] = rp.getId().getCode();
                }

            }

        }

        return "editUI";
    }


    public String edit(){

        try{
            if(role != null){

                //处理权限保存
                if (privilegeIds!=null){

                    HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();

                    for (int i = 0; i < privilegeIds.length ; i++){
                        //吧多个权限存到set
                        set.add(new RolePrivilege(new RolePrivilegeId(role,privilegeIds[i])));
                    }

                    role.setRolePrivileges(set);

                }
                //更新
                roleService.update(role);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "list";

    }


    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
