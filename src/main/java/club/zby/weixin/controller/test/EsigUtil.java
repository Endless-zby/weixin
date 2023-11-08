package club.zby.weixin.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import okhttp3.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/**
 * 聚合电子签章工具类
 *
 * @author zhangcy
 * @date 2022-05-18
 */
public class EsigUtil {

    public static Map<String, String> generateSigncode(Map<String, String> map, String appkey, String secret)
            throws Exception {
        Map<String, String> keyMap = new HashMap<>(8);
        Long time = System.currentTimeMillis();
        map.put("timestamp", String.valueOf(time));
        map.put("appkey", appkey);
        List<String> list = new ArrayList();

        Iterator iterator = map.entrySet().iterator();

        String key;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iterator.next();
            key = entry.getKey();
            if (!key.equals("signcode") && !key.equals("paramsStr")) {
                list.add(key);
            }
        }

        Collections.sort(list);
        StringBuffer signCode = new StringBuffer(secret);

        for (Iterator var10 = list.iterator(); var10.hasNext(); signCode = signCode.append(key).append(map.get(key))) {
            key = (String) var10.next();
        }

        signCode = signCode.append(secret);
        String sign = encryption(signCode.toString().toUpperCase()).toUpperCase();
        keyMap.put("timestamp", String.valueOf(time));
        keyMap.put("signcode", sign);
        keyMap.put("appkey", appkey);
        return keyMap;
    }

    private static String encryption(String plainText)
            throws Exception {
        String md5;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] b = md.digest();


            StringBuilder buf = new StringBuilder();
            for (int offset = 0; offset < b.length; offset++) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            md5 = buf.toString();
        } catch (Exception e) {
            throw e;
        }
        return md5;
    }


    public static void main(String[] args) throws Exception {

//        HashMap<String, String> map = new HashMap<>();
//        map.put("signInfos", "{\"signInfos\":[{\"sealId\":\"b801e676233142959bbf711769e5a2b3\",\"userId\":\"DF327E3DEEF046EEE05311016B0A05E8\",\"signKeywords\":[{\"offsetCoordX\":\"0\",\"keyword\":\"买受人（盖章）\"}]},{\"sealId\":\"9181509b5f284417a217ba562302a097\",\"userId\":\"DF351ADBBAFA4C86E05311016B0A374F\",\"signKeywords\":[{\"offsetCoordX\":\"0\",\"keyword\":\"出卖人（盖章）\"}]}]}");
//        Map<String, String> mdjy_daixiao_c2d91 = generateSigncode(map, "mdjy_daixiao_c2d91", "4ffe106dc3d01f97c1441e351e476c62");
//        System.out.println(mdjy_daixiao_c2d91.get("signcode"));
//        System.out.println(mdjy_daixiao_c2d91.get("timestamp"));
//
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("uploadFile","/D:/安心签CFCA/a79ff421cfba4809be80aafd7892c0a1_1652866218129.pdf",
//                        RequestBody.create(MediaType.parse("application/octet-stream"),
//                                new File("/D:/安心签CFCA/a79ff421cfba4809be80aafd7892c0a1_1652866218129.pdf")))
//                .addFormDataPart("signInfos", null,
//                        RequestBody.create(MediaType.parse("application/json"), "{\"signInfos\":[{\"sealId\":\"b801e676233142959bbf711769e5a2b3\",\"userId\":\"DF327E3DEEF046EEE05311016B0A05E8\",\"signKeywords\":[{\"offsetCoordX\":\"-50\",\"keyword\":\"买受人（盖章）\"}]},{\"sealId\":\"9181509b5f284417a217ba562302a097\",\"userId\":\"DF351ADBBAFA4C86E05311016B0A374F\",\"signKeywords\":[{\"offsetCoordX\":\"50\",\"keyword\":\"出卖人（盖章）\"}]}]}".getBytes()))
//                .build();
//        Request request = new Request.Builder()
//                .url("http://localhost:9090/signature/signContract?appkey=mdjy_daixiao_c2d91&timestamp=1652930441052&signcode=DDB303B38AE1D70705F8CF41DDA019CE")
//                .method("POST", body)
//                .build();
//        Response response = client.newCall(request).execute();
//
//        byte[] bytes = response.body().source().readByteArray();
//        getFileByBytes(bytes,"/D:/安心签CFCA/","989898.pdf");


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uploadFile","/D:/安心签CFCA/4c9b3bc922674cc1.pdf",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("/D:/安心签CFCA/4c9b3bc922674cc1.pdf")))
                .addFormDataPart("signInfos", null,
                        RequestBody.create(MediaType.parse("application/json"), "{\"signInfos\":[{\"userId\":\"DF327E3DEEF046EEE05311016B0A05E8\",\"signKeywords\":[{\"offsetCoordY\":\"-30\",\"offsetCoordX\":\"50\",\"keyword\":\"买受人（盖章）\"}]},{\"sealId\":\"9181509b5f284417a217ba562302a097\",\"userId\":\"DF351ADBBAFA4C86E05311016B0A374F\",\"signKeywords\":[{\"offsetCoordY\":\"-30\",\"offsetCoordX\":\"50\",\"keyword\":\"出卖人（盖章）\"}]}]}".getBytes()))
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8081/signature/signContract/V1?appkey=mdjy_daixiao_c2d91&timestamp=1653446656000&signcode=A68C9A4B95EC213AA7A15DDD826CA870&signInfos={\"signInfos\":[{\"userId\":\"DF327E3DEEF046EEE05311016B0A05E8\",\"signKeywords\":[{\"offsetCoordY\":\"-30\",\"offsetCoordX\":\"50\",\"keyword\":\"买受人（盖章）\"}]},{\"sealId\":\"9181509b5f284417a217ba562302a097\",\"userId\":\"DF351ADBBAFA4C86E05311016B0A374F\",\"signKeywords\":[{\"offsetCoordY\":\"-30\",\"offsetCoordX\":\"50\",\"keyword\":\"出卖人（盖章）\"}]}]}")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        byte[] bytes = response.body().bytes();
        JSONObject object = JSONObject.parseObject(bytes, JSONObject.class, Feature.IgnoreAutoType);
        JSONObject data = (JSONObject)object.get("data");

        String fileBytes = (String) data.get("fileBytes");
        byte[] files = Base64.getDecoder().decode(fileBytes);
        getFileByBytes(files,"/D:/安心签CFCA/","989898.pdf");
        System.out.println(data);
    }

    public static void getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
