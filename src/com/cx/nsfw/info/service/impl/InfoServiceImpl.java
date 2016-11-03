package com.cx.nsfw.info.service.impl;

import com.cx.core.service.impl.BaseServiceImpl;
import com.cx.nsfw.info.dao.InfoDao;
import com.cx.nsfw.info.entity.Info;
import com.cx.nsfw.info.service.InfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cxspace on 16-10-27.
 */

@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService{

    private InfoDao infoDao;
    @Resource

    public void setInfoDao(InfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }
}
