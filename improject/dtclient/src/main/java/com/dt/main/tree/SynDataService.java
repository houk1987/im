package com.dt.main.tree;

import com.comunication.connection.ConnectionManager;
import com.dt.vo.Unit;
import com.dt.vo.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.san30.pub.tools.SanHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 同步服务器数据
 * 同步人员信息，单位信息
 * Created by a on 2014/7/9.
 */
public class SynDataService {

    private static final SynDataService synDataService = new SynDataService();
    private static List<UserInfo> userInfoList;

    public static  SynDataService getInstance(){
        return synDataService;
    }

    private SynDataService() {

    }

    /**
     * 同步人员数据
     */
    public  List<UserInfo> synUsers() {
        if(userInfoList != null)return userInfoList;
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getuser");
        String rs = execute(paramMap);
        if (!"-1".equals(rs.trim())) {
            Gson gson = new Gson();
            userInfoList = new ArrayList<>();
            userInfoList = gson.fromJson(rs, new TypeToken<List<UserInfo>>() {
            }.getType());
            return userInfoList;
        }
        return null;
    }

    public UserInfo getUserInfo(String jid){
        if(jid != null) {
            for (UserInfo userInfo : userInfoList) {
                if (jid.equals(userInfo.getId())) {
                    return userInfo;
                }
            }
        }
        return new UserInfo();
    }

    /**
     * 同步单位数据
     */
    public  List<Unit> synUnits() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getpartment");
        String rs = execute(paramMap);
        if (!"-1".equals(rs.trim())) {
            Gson gson = new Gson();
            List<Unit> partments = gson.fromJson(rs, new TypeToken<List<Unit>>() {
            }.getType());
            return partments;
        }
        return null;
    }

    /**
     * 执行同步请求，返回请求的数据
     *
     * @param paramMap 请求参数的 map
     * @return rs
     */
    private  String execute(HashMap<String, String> paramMap) {
        try {
            return SanHttpClient.getDataAsString("http://" + ConnectionManager.getConnection().getHost() +":" + 9090 + "/plugins/dtserver/dtserver", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
