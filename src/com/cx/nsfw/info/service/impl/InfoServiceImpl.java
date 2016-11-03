package com.cx.nsfw.info.service.impl;

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
public class InfoServiceImpl implements InfoService {

    @Resource
    private InfoDao infoDao;


    @Override
    public void delete(Serializable id) {
        infoDao.delete(id);
    }

    @Override
    public void save(Info info) {
        infoDao.save(info);
    }

    @Override
    public void update(Info info) {
        infoDao.update(info);
    }

    @Override
    public Info findObjectById(Serializable id) {
        return infoDao.findObjectById(id);
    }

    @Override
    public List<Info> findObjects() {
        return infoDao.findObjects();
    }
}
