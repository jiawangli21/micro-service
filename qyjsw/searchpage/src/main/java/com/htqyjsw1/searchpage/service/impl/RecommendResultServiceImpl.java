package com.htqyjsw1.searchpage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.htqyjsw1.searchpage.service.RecommendResultService;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.BasicConfigurator;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import java.io.IOException;

@Service
public class RecommendResultServiceImpl implements RecommendResultService {


    public static void main(String[] args) {
        BasicConfigurator.configure();
        System.out.println(getRowKey("test", "650000" + "0000000000", "application/json"));
        System.out.println(getRowKey("test_offline", "650000" + "week", "application/json"));
    }

    /**
     *
     * @param tableName
     * @param rowkey
     * @param xmlOrJson
     * @return
     */
    public static JSONArray getRowKey(String tableName, String rowkey, String xmlOrJson) {
        String uriAPI = "http://192.168.100.34:9001/" + tableName + "/" + rowkey + "/data:commend";
        String result = "";
        JSONArray jsonArray = new JSONArray();
        HttpGet getR = new HttpGet(uriAPI);
        try {
            getR.setHeader("accept", xmlOrJson);
            HttpResponse httpResponse = new DefaultHttpClient().execute(getR);
            // 其中HttpGet是HttpUriRequst的子类
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200 || statusCode == 403) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
                JSONObject object = JSON.parseObject(result);
                JSONArray jack = (JSONArray) object.get("Row");
                JSONObject ob1 = (JSONObject) jack.get(0);
                JSONArray ob2 = (JSONArray) ob1.get("Cell");
                JSONObject ob3 = (JSONObject) ob2.get(0);
                BASE64Decoder decoder = new BASE64Decoder();
                try {
                    byte[] bytes = decoder.decodeBuffer((String) ob3.get("$"));
                    String result2 = Bytes.toString(bytes);
                    jsonArray = JSONArray.parseArray(result2);
//					System.out.println(jsonArray);
                } catch (IOException e) {
                    e.printStackTrace();
                    result = "I/o异常";
                }

                // 一般来说都要删除多余的字符
                // 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
                // result.replaceAll("\r", "");
            } else {
                getR.abort();
                result = "没有返回正确的状态码，请仔细检查请求表名及参数格式！";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return jsonArray;
    }


    /**
     * 精准推荐服务层方法
     * @param userId：用户Id
     * @param recommendType：推荐类型，实时、日、周、月
     * @return：返回从hbase查询到的数据
     */
    @Override
    public JSONArray recommendResult(String userId, String recommendType) {
        JSONArray recommendResult = new JSONArray();
        if (recommendType.equals("realTime")) {
            recommendResult = getRowKey("test", userId + "0000000000", "application/json");
        } else {
            recommendResult = getRowKey("test_offline", userId + recommendType, "application/json");
        }
        return recommendResult;
    }
}
