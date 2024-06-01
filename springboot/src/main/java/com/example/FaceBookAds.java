package com.example;

import com.facebook.ads.sdk.*;

/**
 * Note:
 * Date: 2024/5/31 下午11:51
 * Author: shihy
 */
public class FaceBookAds {
    public static final String ACCESS_TOKEN = "YOUR_ACCESS_TOKEN";
    public static final String APP_SECRET = "YOUR_APP_SECRET";
    public static final String AD_SET_ID = "YOUR_AD_SET_ID";

    public static final APIContext context = new APIContext(ACCESS_TOKEN, APP_SECRET).enableDebug(true);

    public static void main(String[] args) {
        System.out.println("feature1");
        try {
            // 获取广告活动对象
            Campaign campaign = new Campaign(CAMPAIGN_ID, context);

            // 获取广告活动下的所有广告组
            APINodeList<AdSet> adSets = campaign.getAdSets().requestAllFields().execute();

            // 打印每个广告组的 ID 和名称
            for (AdSet adSet : adSets) {
                System.out.println("AdSet ID: " + adSet.getFieldId());
                System.out.println("AdSet Name: " + adSet.getFieldName());
            }
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
