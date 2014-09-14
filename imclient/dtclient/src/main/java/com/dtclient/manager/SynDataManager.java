package com.dtclient.manager;


import com.dtclient.sys.SysProperties;
import com.dtclient.vo.Unit;
import com.dtclient.vo.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.san30.pub.tools.SanHttpClient;

import java.util.HashMap;
import java.util.List;

/**
 * ͬ������������
 * ͬ����Ա��Ϣ����λ��Ϣ
 * Created by a on 2014/7/9.
 */
public class SynDataManager {

    private List<UserInfo> userInfoList;
    private List<Unit> unitList;
    private UserInfo loginUserInfo;

    private static final SynDataManager SYN_DATA_MANAGER = new SynDataManager();

    public static SynDataManager getInstance(){
        return SYN_DATA_MANAGER;
    }

    private SynDataManager() {

    }

    /**
     * ͬ����Ա����
     */
    public  List<UserInfo> synUsers() {
        if(userInfoList!=null)return userInfoList;
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getuser");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            userInfoList = gson.fromJson(rs, new TypeToken<List<UserInfo>>() {
            }.getType());
            return userInfoList;
        }
        return null;
    }

    public UserInfo getLoginUserInfo(){
        if(loginUserInfo!=null)return loginUserInfo;
        for (UserInfo userInfo : userInfoList){
            if(userInfo.getId().equals(SysProperties.getUser())){
                loginUserInfo = userInfo;
            }
        }
        return loginUserInfo;
    }




    /**
     * ͬ����λ����
     */
    public  List<Unit> synUnits() {
        if(unitList!=null)return unitList;
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getpartment");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            unitList = gson.fromJson(rs, new TypeToken<List<Unit>>() {
            }.getType());
            return unitList;
        }
        return null;
    }

    /**
     * ִ��ͬ�����󣬷������������
     *
     * @param paramMap ��������� map
     * @return rs
     */
    private  String execute(HashMap<String, String> paramMap) {
        try {
            return SanHttpClient.getDataAsString("http://" + SysProperties.getHost() +":" + 9090 + "/plugins/orgtree/orgtree", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
