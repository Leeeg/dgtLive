package com.dgtis.live.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;

/**
 * Created by Lee on 2018/3/8.
 */

public class RoomListItem extends MultiItemEntity {

    /**
     * id : 104
     * videoId : 43d5b562f8e54afcb38e2015b01428a0
     * videoName : 直播
     * videoIntroduce : 直播
     * cover : https://dgt.dgtis.com/oneportal/fileServer/LiveFile/83c580da82bd41cea8770fd5d72e984d.png
     * bespeakTime : 2018-03-06 11:43:16.0
     * startTime : 2018-03-06 11:43:16.0
     * endTime : 2018-03-06 11:43:35.0
     * hotCourses : 0
     * videoStatus : 2
     * videoMode : 0
     * videoMoney : null
     * videoPwd : null
     * recFile : {"create_time":"2018-03-05 16:13:21","end_time":"2018-03-05 16:13:16","err_code":"0","expire_time":"2038-01-19 11:14:07","record_file_id":"7447398154901887164","task_id":null,"file_size":"9542264","record_type":null,"vid":"210027401_090764ac3cdb476dac3272cef440b6cf","start_time":"2018-03-05 16:11:50","report_message":null,"stream_id":"19105_solo","vod2Flag":"1","appid":"1255760557","file_id":"7447398154901887164","record_file_url":"http://1255760557.vod2.myqcloud.com/cf1c4e6avodgzp1255760557/638f6a957447398154901887164/f0.flv","id":"188323583","task_sub_type":"0","file_format":"1"}
     * addTime : 2018-03-06 11:43:17.0
     * addUser : 13621761774
     * modifyTime : null
     * modifyUser : null
     * delFlag : 0
     * companyId : 69964c94aef84d3ea9d496a01188cbd8
     * userName : null
     * liveCode : null
     */

    private int id;
    private String videoId;
    private String videoName;
    private String videoIntroduce;
    private String cover;
    private String bespeakTime;
    private String startTime;
    private String endTime;
    private String hotCourses;
    private String videoStatus;
    private String videoMode;
    private Object videoMoney;
    private Object videoPwd;
    private String recFile;
    private String addTime;
    private String addUser;
    private Object modifyTime;
    private Object modifyUser;
    private String delFlag;
    private String companyId;
    private Object userName;
    private Object liveCode;

    public static RoomListItem objectFromData(String str) {

        return new Gson().fromJson(str, RoomListItem.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoIntroduce() {
        return videoIntroduce;
    }

    public void setVideoIntroduce(String videoIntroduce) {
        this.videoIntroduce = videoIntroduce;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBespeakTime() {
        return bespeakTime;
    }

    public void setBespeakTime(String bespeakTime) {
        this.bespeakTime = bespeakTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHotCourses() {
        return hotCourses;
    }

    public void setHotCourses(String hotCourses) {
        this.hotCourses = hotCourses;
    }

    public String getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(String videoStatus) {
        this.videoStatus = videoStatus;
    }

    public String getVideoMode() {
        return videoMode;
    }

    public void setVideoMode(String videoMode) {
        this.videoMode = videoMode;
    }

    public Object getVideoMoney() {
        return videoMoney;
    }

    public void setVideoMoney(Object videoMoney) {
        this.videoMoney = videoMoney;
    }

    public Object getVideoPwd() {
        return videoPwd;
    }

    public void setVideoPwd(Object videoPwd) {
        this.videoPwd = videoPwd;
    }

    public String getRecFile() {
        return recFile;
    }

    public void setRecFile(String recFile) {
        this.recFile = recFile;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public Object getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Object modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Object getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Object modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getLiveCode() {
        return liveCode;
    }

    public void setLiveCode(Object liveCode) {
        this.liveCode = liveCode;
    }
}
