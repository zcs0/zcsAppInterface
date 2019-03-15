package cn.com.app.main.service.impl;

import cn.com.app.assistcode.Page;
import cn.com.app.common.CommonStatic;
import cn.com.app.main.dao.TVLivingListDao;
import cn.com.app.main.dao.pojo.TVLivingList;
import cn.com.app.main.service.TVLivingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2019/3/14.
 */
@Service
public class TVLivingServiceImpl implements TVLivingListService {

    @Autowired
    private TVLivingListDao tvLivingListDao;

    @Override
    public void saveTVLivingListEntity(TVLivingList tvLivingList) throws Exception {
        this.tvLivingListDao.saveTVLivingListEntity(tvLivingList);
    }

    @Override
    public void updateTVLivingListEntity(TVLivingList tvLivingList) throws Exception {
        this.tvLivingListDao.updateTVLivingListEntity(tvLivingList);
    }

    @Override
    public void updateTVLivingListByHQLParams(Map<String, Object> map) throws Exception {
        this.tvLivingListDao.updateTVLivingListByHQLParams(map);
    }

    @Override
    public void deleteTVLivingListEntity(TVLivingList tvLivingList) throws Exception {
        this.tvLivingListDao.deleteTVLivingListEntity(tvLivingList);
    }

    @Override
    public void deleteTVLivingListByHQLParams(Map<String, Object> map) throws Exception {
        this.tvLivingListDao.deleteTVLivingListByHQLParams(map);
    }

    @Override
    public List<TVLivingList> findAllTVLivingList() throws Exception {
        return this.tvLivingListDao.findAllTVLivingList();
    }

    @Override
    public List<TVLivingList> findTVLivingListByParams(Map<String, Object> params) throws Exception {
       if(CommonStatic.ObjectIsEmpty(params)){
           return this.tvLivingListDao.findAllTVLivingList();
       }else{
           return this.tvLivingListDao.findTVLivingListByParams(params);
       }
    }

    @Override
    public List<TVLivingList> findTVLivingListByParamsAndPage(Map<String, Object> params, Page page) throws Exception {
        if(CommonStatic.ObjectIsEmpty(params)){
            return this.tvLivingListDao.findTVLivingListByParamsAndPage(null,page);
        }else{
            return this.tvLivingListDao.findTVLivingListByParamsAndPage(params,page);
        }
    }
}
