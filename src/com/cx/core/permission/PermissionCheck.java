package com.cx.core.permission;

import com.cx.nsfw.user.entity.User;

/**
 * Created by cxspace on 16-10-27.
 */
public interface PermissionCheck {

    /*
         判断用户是否有code对应的权限
     */

    public boolean isAccessible(User user , String code);


}
