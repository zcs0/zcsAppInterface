package cn.com.app.main.controller;

import cn.com.app.assistcode.AppResult;
import cn.com.app.assistcode.Page;
import cn.com.app.common.CommonJSONTransfer;
import cn.com.app.enums.EnumCommon;
import cn.com.app.main.dao.pojo.TVLivingList;
import cn.com.app.main.service.TVLivingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2019/3/14.
 */
@Controller
@RequestMapping(path="app")
public class AppInterfaceController {
    @Autowired
    private TVLivingListService tvLivingListService;

    @RequestMapping(path="saveTVLiving.act",method = RequestMethod.POST)
    public void saveTVLivingListObject(HttpServletRequest request, HttpServletResponse response){
        AppResult appResult = new AppResult();
        appResult.setDoing("保存电视直播列表");
        try {
            TVLivingList tvLivingList = CommonJSONTransfer.getObjectFromJSONStr(request, TVLivingList.class);
            tvLivingList.setCrTime(new Date() .getTime());
            tvLivingList.setUpTime(Long.parseLong("0"));
            tvLivingListService.saveTVLivingListEntity(tvLivingList);
            appResult.setObj(tvLivingList);
            appResult.setState(EnumCommon.RESULTSUCCESS.getCode());
            appResult.setMessage(EnumCommon.RESULTSUCCESS.getMsg());
        } catch (Exception e) {
            appResult.setState(EnumCommon.RESULTMISSING.getCode());
            appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
            e.printStackTrace();
        }finally {
            CommonJSONTransfer.responseJSONObjectToApp(response,appResult);
        }
    }

    @RequestMapping(path="updateTVLiving.act",method = RequestMethod.POST)
    public void updateTVLivingListObject(HttpServletRequest request, HttpServletResponse response){
        AppResult appResult = new AppResult();
        appResult.setDoing("修改电视直播列表");
        try {
            Map<String,Object> map = CommonJSONTransfer.getJSONFieldByRequest(request, TVLivingList.class);
           if(map != null && map.get("id") != null && map.size()>1){
               map.put("upTime", new Date().getTime());
               tvLivingListService.updateTVLivingListByHQLParams(map);
               appResult.setState(EnumCommon.RESULTSUCCESS.getCode());
               appResult.setMessage(EnumCommon.RESULTSUCCESS.getMsg());
           }else{
               appResult.setState(EnumCommon.RESULTMISSING.getCode());
               appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
               appResult.setObj("缺少属性值或者缺少id属性");
           }
        } catch (Exception e) {
            appResult.setState(EnumCommon.RESULTMISSING.getCode());
            appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
            appResult.setObj(e.getStackTrace());
            e.printStackTrace();
        }finally {
            CommonJSONTransfer.responseJSONObjectToApp(response,appResult);
        }
    }

    @RequestMapping(path="deleteTVLiving.act",method = RequestMethod.POST)
    public void deleteTVLivingListObject(HttpServletRequest request, HttpServletResponse response){
        AppResult appResult = new AppResult();
        appResult.setDoing("删除电视直播列表");
        try {
            Map<String,Object> map = CommonJSONTransfer.getJSONFieldByRequest(request, TVLivingList.class);
            if(map != null){
                tvLivingListService.deleteTVLivingListByHQLParams(map);
                appResult.setState(EnumCommon.RESULTSUCCESS.getCode());
                appResult.setMessage(EnumCommon.RESULTSUCCESS.getMsg());
            }else{
                appResult.setState(EnumCommon.RESULTMISSING.getCode());
                appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
                appResult.setObj("未解析出对应的属性值");
            }
        } catch (Exception e) {
            appResult.setState(EnumCommon.RESULTMISSING.getCode());
            appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
            appResult.setObj(e.getStackTrace());
            e.printStackTrace();
        }finally {
            CommonJSONTransfer.responseJSONObjectToApp(response, appResult);
        }
    }

    @RequestMapping(path="findAllTVLiving.act",method = RequestMethod.POST)
    public void findAllTVLivingListObject(HttpServletRequest request, HttpServletResponse response){
        AppResult appResult = new AppResult();
        appResult.setDoing("查询全部电视直播列表");
        try {
            List<TVLivingList> list = this.tvLivingListService.findAllTVLivingList();
            appResult.setObj(list);
            appResult.setState(EnumCommon.RESULTSUCCESS.getCode());
            appResult.setMessage(EnumCommon.RESULTSUCCESS.getMsg());
        } catch (Exception e) {
            appResult.setState(EnumCommon.RESULTMISSING.getCode());
            appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
            e.printStackTrace();
        }finally {
            CommonJSONTransfer.responseJSONObjectToApp(response,appResult);
        }
    }

    @RequestMapping(path="findTVLivingByParams.act",method = RequestMethod.POST)
    public void findTVLivingByParams(HttpServletRequest request, HttpServletResponse response){
        AppResult appResult = new AppResult();
        appResult.setDoing("条件查询电视直播列表");
        try {
            Map<String,Object> map = CommonJSONTransfer.getJSONFieldByRequest(request, TVLivingList.class);
           if(!map.isEmpty()){
               List<TVLivingList> list = this.tvLivingListService.findTVLivingListByParams(map);
               appResult.setObj(list);
               appResult.setState(EnumCommon.RESULTSUCCESS.getCode());
               appResult.setMessage(EnumCommon.RESULTSUCCESS.getMsg());
           }else{
               appResult.setState(EnumCommon.RESULTMISSING.getCode());
               appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
               appResult.setObj("传递参数异常，缺少参数");
           }
        } catch (Exception e) {
            appResult.setState(EnumCommon.RESULTMISSING.getCode());
            appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
            appResult.setObj(e.getStackTrace());
            e.printStackTrace();
        }finally {
            CommonJSONTransfer.responseJSONObjectToApp(response,appResult);
        }
    }

    @RequestMapping(path="findTVLivingByPage.act",method = RequestMethod.POST)
    public void findTVLivingByPage(HttpServletRequest request, HttpServletResponse response){
        AppResult appResult = new AppResult();
        appResult.setDoing("分页条件查询电视直播列表");
        try {
            Map<String,Object> map = CommonJSONTransfer.getPageObjectFromJSONStr(request, TVLivingList.class);
            if(!map.isEmpty()){
                Map<String,Object> maptmp = null;
                if(map.get("obj") != null){
                    maptmp = (Map<String,Object>)map.get("obj");
                }
                Page page = null;
                if(map.get("page") != null){
                    page = (Page)map.get("page");
                }
                List<TVLivingList> list = this.tvLivingListService.findTVLivingListByParamsAndPage(maptmp,page);
                appResult.setObj(list);
                appResult.setState(EnumCommon.RESULTSUCCESS.getCode());
                appResult.setMessage(EnumCommon.RESULTSUCCESS.getMsg());
            }else{
                appResult.setState(EnumCommon.RESULTMISSING.getCode());
                appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
                appResult.setObj("传递参数异常，缺少参数");
            }
        } catch (Exception e) {
            appResult.setState(EnumCommon.RESULTMISSING.getCode());
            appResult.setMessage(EnumCommon.RESULTMISSING.getMsg());
            appResult.setObj(e.getStackTrace());
            e.printStackTrace();
        }finally {
            CommonJSONTransfer.responseJSONObjectToApp(response,appResult);
        }
    }
}
