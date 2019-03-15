package cn.com.app.main.dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by user on 2019/3/12.
 * 电视直播列表
 */
@Entity
@Table(name="tv_list")
public class TVLivingList  extends BasePojo{
    private String name1;//名字
    private String name2;//名字2
    @Column(name="url_data")
    private String urlData;//地址json_arr 录入的时候 自己写好
    @Column(name="head_img")
    private String headImg;//头像
    @Column(name="big_img")
    private String bigImg;//大头像
    @Column(name="url_type")
    private String urlType;//类型
    @Column(name="img_path")
    private String imgPath;//图片路径
    @Column(name="vedio_type")
    private String vedioType;//类型2
    @Column(name="up_time")
    private Long upTime;//更新时间
    @Column(name="cr_time")
    private Long crTime;//创建时间
    @Column(name="check_ok")
    private Boolean checkOk;//是否审核通过
    private String explains;//说明
    private String other1;//其他信息1
    private String other2;//其他信息2

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getUrlData() {
        return urlData;
    }

    public void setUrlData(String urlData) {
        this.urlData = urlData;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getVedioType() {
        return vedioType;
    }

    public void setVedioType(String vedioType) {
        this.vedioType = vedioType;
    }

    public Long getUpTime() {
        return upTime;
    }

    public void setUpTime(Long upTime) {
        this.upTime = upTime;
    }

    public Long getCrTime() {
        return crTime;
    }

    public void setCrTime(Long crTime) {
        this.crTime = crTime;
    }

    public Boolean getCheckOk() {
        return checkOk;
    }

    public void setCheckOk(Boolean checkOk) {
        this.checkOk = checkOk;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }
}
