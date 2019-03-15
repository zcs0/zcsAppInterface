package cn.com.app.main.dao;

import cn.com.app.assistcode.Page;
import cn.com.app.main.dao.pojo.TVLivingList;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2019/3/14.
 */
public interface TVLivingListDao {
    public void saveTVLivingListEntity(TVLivingList tvLivingList) throws Exception;

    public void updateTVLivingListEntity(TVLivingList tvLivingList) throws Exception;

    public void updateTVLivingListByHQLParams(Map<String,Object> map) throws Exception;

    public void deleteTVLivingListEntity(TVLivingList tvLivingList) throws Exception;

    public void deleteTVLivingListByHQLParams(Map<String,Object> map) throws Exception;

    public List<TVLivingList> findAllTVLivingList() throws Exception;

    public List<TVLivingList> findTVLivingListByParams(Map<String,Object> params) throws Exception;

    public List<TVLivingList> findTVLivingListByParamsAndPage(Map<String,Object> params,Page page) throws Exception;

}
