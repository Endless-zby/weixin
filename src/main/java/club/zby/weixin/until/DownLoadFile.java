package club.zby.weixin.until;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author 赵博雅
 * @date 2021/3/25 17:40
 */
@Component
public class DownLoadFile {

    public void downLoad(String urlStr,String fileName,String savePath) throws IOException {
        downLoadFromUrl(urlStr,fileName,savePath);

    }


    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:"+url+" download success");

    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public String unZipFile(String zipFile) throws IOException {
        //获取文件输入流
        FileInputStream input = new FileInputStream(zipFile);

        //获取ZIP输入流(一定要指定字符集Charset.forName("GBK")否则会报java.lang.IllegalArgumentException: MALFORMED)
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(input), Charset.forName("GBK"));

        //定义ZipEntry置为null,避免由于重复调用zipInputStream.getNextEntry造成的不必要的问题
        ZipEntry ze = null;

        //循环遍历
        StringBuilder result = new StringBuilder();
        String line;
        while ((ze = zipInputStream.getNextEntry()) != null) {

            if(ze.getName().contains("txt") && ze.getName().contains("2018.2之后的版本用这个")){

                System.out.println("文件名：" + ze.getName() + " 文件大小：" + ze.getSize() + " bytes");
                System.out.println("文件内容：");

                //读取
                BufferedReader br = new BufferedReader(new InputStreamReader(zipInputStream,Charset.forName("GBK")));

                //内容不为空，输出
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                System.out.println(result);
                break;
            }

        }

        //一定记得关闭流
        zipInputStream.closeEntry();
        input.close();
        return result.toString();
    }

}
