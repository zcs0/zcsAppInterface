package cn.com.app.main.dao.impl;

import cn.com.app.assistcode.Page;
import cn.com.app.main.dao.BaseDao;
import cn.com.app.main.dao.TVLivingListDao;
import cn.com.app.main.dao.pojo.TVLivingList;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2019/3/14.
 */
@Component
public class TVLivingListDaoImpl extends BaseDao implements TVLivingListDao {
    String hql = " from TVLivingList ";

    @Override
    public void saveTVLivingListEntity(TVLivingList tvLivingList) throws Exception {
        this.save(tvLivingList);
    }

    @Override
    public void updateTVLivingListEntity(TVLivingList tvLivingList) throws Exception {
        this.update(tvLivingList);
    }

    @Override
    public void updateTVLivingListByHQLParams(Map<String, Object> map) throws Exception {
        this.updateHQL(map,TVLivingList.class);
    }

    @Override
    public void deleteTVLivingListEntity(TVLivingList tvLivingList) throws Exception {
        this.delObject(tvLivingList);
    }

    @Override
    public void deleteTVLivingListByHQLParams(Map<String, Object> map) throws Exception {
        this.deleteHQL(map,TVLivingList.class);
    }

    @Override
    public List<TVLivingList> findAllTVLivingList() throws Exception {
        return this.find(hql);
    }

    @Override
    public List<TVLivingList> findTVLivingListByParams(Map<String, Object> params) throws Exception {
       if(params == null){
           return this.find(hql);
       }else{
           Iterator<String> iterator = params.keySet().iterator();
           StringBuilder sbu = new StringBuilder(hql);
           boolean flag = true;
           while(iterator.hasNext()){
               if(flag){
                   sbu.append(" where ");
                   flag = false;
               }else{
                   sbu.append(" and ");
               }
               String columnName = iterator.next();
               sbu.append(columnName+"=:"+columnName);
               sbu.append(" ");
           }
           return this.find(sbu.toString(),params);
       }
    }

    @Override
    public List<TVLivingList> findTVLivingListByParamsAndPage(Map<String, Object> params, Page page) throws Exception {
        if(params == null){
            return this.find(hql,null,page);
        }else{
            Iterator<String> iterator = params.keySet().iterator();
            StringBuilder sbu = new StringBuilder(hql);
            boolean flag = true;
            while(iterator.hasNext()){
                if(flag){
                    sbu.append(" where ");
                    flag = false;
                }else{
                    sbu.append(" and ");
                }
                String columnName = iterator.next();
                sbu.append(columnName+"=:"+columnName);
                sbu.append(" ");
            }
            return this.find(sbu.toString(),params,page);
        }
    }
}
