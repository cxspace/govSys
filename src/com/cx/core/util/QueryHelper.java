package com.cx.core.util;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2016/11/04.
 */
public class QueryHelper {

    private String fromClause = "";

    private String whereClause = "";

    private String orderByClause = "";

    public static String ORDER_BY_DESC = "DESC";

    public static String ORDER_BY_ASC = "ASC";

    private List<Object> parameters;

    /*

         构造from语句

         @param clazz 实体类

         @param alias 实体类对应的别名

     */

    public QueryHelper(Class clazz , String alias){
        fromClause = "FROM "+clazz.getSimpleName() + " " + alias;
    }

    /*

         构造where字句

         @param condition 查询条件语句

         @param 查询条件语句中？对应的查询条件值

     */


    public void addCondition(String condition , Object... params){

        if (whereClause.length() > 1) {  //非第一个查询条件
            whereClause += " AND " + condition;
        }else {
            whereClause += " WHERE " + condition;
        }

        //设置查询条件值到查询条件值集合中
        if (parameters == null){
            parameters = new ArrayList<Object>();
        }

        if (params != null){

            for (Object param : params){
                parameters.add(param);
            }
        }

    }




    public void addOrderByProperty(String property , String order){

        if (orderByClause.length() > 1){

            orderByClause += "," + property + " " + order;

        }else {
            orderByClause = " ORDER BY "+property+" "+order;
        }

    }

    //查询hql语句

    public String getQueryListHql(){

        return  fromClause + whereClause + orderByClause ;
    }


    public String getQueryCountHql(){

        return "SELECT COUNT(*) "+fromClause + whereClause;

    }

    //查询hql语句中？对应的查询条件值集合

    public List<Object> getParameters(){

        return parameters;
    }
}



