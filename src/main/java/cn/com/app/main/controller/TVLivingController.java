package cn.com.app.main.controller;

import cn.com.app.common.CommonJSONTransfer;
import cn.com.app.main.dao.pojo.TVLivingList;
import cn.com.app.main.service.TVLivingListService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Date;

/**
 * Created by user on 2019/3/14.
 */
@Controller
@RequestMapping(path="tvliving")
public class TVLivingController {

    @Autowired
    private TVLivingListService tvLivingListService;

    @RequestMapping(path="test.act",method = RequestMethod.POST)
    public void testMethod(HttpServletRequest request, HttpServletResponse response){
        try {

            TVLivingList tvLivingList = CommonJSONTransfer.getObjectFromJSONStr(request,TVLivingList.class);
            tvLivingList.setCrTime(new Date() .getTime());
            tvLivingList.setUpTime(Long.valueOf(0));
            tvLivingListService.saveTVLivingListEntity(tvLivingList);
            CommonJSONTransfer.responseJSONObjectToApp(response,tvLivingList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
