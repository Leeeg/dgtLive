package com.dgtis.live.Inners;

import com.google.gson.Gson;

/**
 * Created by Lee on 2017/4/10.
 */

public class GsonInner {

    private static class GsonHolder{
        private static final Gson INSTANCE = new Gson();
    }

    public static Gson getGsonInstance(){//Gson自带线程安全
        return GsonHolder.INSTANCE;
    }

}
