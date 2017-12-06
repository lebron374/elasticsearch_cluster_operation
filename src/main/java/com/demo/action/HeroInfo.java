package com.demo.action;

import org.elasticsearch.action.support.nodes.NodeOperationResponse;

/**
 * Created by zhi.wang on 2017/12/6.
 */
public class HeroInfo extends NodeOperationResponse {
    private String localIp;

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    HeroInfo() {

    }

    public HeroInfo(String localIp) {
        this.localIp = localIp;
    }
}
