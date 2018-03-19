package com.example.android.bakingapp.data;

import java.io.Serializable;

/**
 * Created by Santosh on 25-09-2017.
 */

public class Instructions_Data implements Serializable {
    String videoUrl;
    String short_desc;
    String desc;
    String thumbNailUrl;

    public Instructions_Data(String a, String b, String c, String d) {
        this.videoUrl = a;
        this.short_desc = b;
        this.desc = c;
        this.thumbNailUrl = d;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public String getDesc() {
        return desc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getShort_desc() {
        return short_desc;
    }
}

