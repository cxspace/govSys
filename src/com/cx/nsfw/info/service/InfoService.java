package com.cx.nsfw.info.service;

import com.cx.nsfw.info.entity.Info;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cxspace on 16-10-27.
 */
public interface InfoService {

    public void save(Info info);

    public void update(Info info);

    public void delete(Serializable id);

    public Info findObjectById(Serializable id);

    public List<Info> findObjects();

}
