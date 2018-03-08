package com.dgtis.live;

/**
 * Created by DELLL on 2018/2/5.
 */

public interface API {

    String LIVE_ROOT = "http://19105.liveplay.myqcloud.com/live/";

//    String ROOT = "http://192.168.100.250:8080/oneportal";
    String ROOT = "http://demo.dgtis.com/oneportal";

    String LOGIN = "/appLiveInterface/appLoginSubmit.if";

    String PLAYLIVE = "/liveVideo/saveLiveVideo.if";

    String UPLOAD = "/liveVideo/fileUpLoad.if";

    String ROOMLIST = "/liveVideo/selectBespeakTime.if";

    String BACKLIST = "/liveVideo/selectVideoEndStatus.if";



}
