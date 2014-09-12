package com.dtclient.main.tree;





import com.dtclient.lanuch.DtClient;
import com.dtclient.lanuch.StartDtClient;
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
public class SynDataService {

    private static final SynDataService synDataService = new SynDataService();

    public static  SynDataService getInstance(){
        return synDataService;
    }

    private SynDataService() {

    }

    /**
     * ͬ����Ա����
     */
    public  List<UserInfo> synUsers() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getuser");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            List<UserInfo> users = gson.fromJson(rs, new TypeToken<List<UserInfo>>() {
            }.getType());
            return users;
        }
        return null;
    }



    /**
     * ͬ����λ����
     */
    public  List<Unit> synUnits() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getpartment");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            List<Unit> partments = gson.fromJson(rs, new TypeToken<List<Unit>>() {
            }.getType());
            return partments;
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
            return SanHttpClient.getDataAsString("http://" + StartDtClient.host +":" + 9090 + "/plugins/orgtree/orgtree", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
