package com.cx.core.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by cx on 2016/10/01.
 *
 * 抽出公共的action
 *
 *
 */
public abstract class BaseAction extends ActionSupport {

    protected String[] selectedRow;

    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }
}
