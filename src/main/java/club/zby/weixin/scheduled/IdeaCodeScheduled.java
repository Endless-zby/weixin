package club.zby.weixin.scheduled;

import club.zby.weixin.entity.interfaces.AfterSendMessages;
import club.zby.weixin.until.DownLoadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class IdeaCodeScheduled {

    private static final String DOWNLOAD_PATH = "/root/ideaTemp";
    private static final String DOWNLOAD_NAME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".zip";
    private static final String PARSING_ZIP_FILE = DOWNLOAD_PATH + "\\" + DOWNLOAD_NAME;
    private static final String DOWNLOAD_URL = "http://idea.medeming.com/a/jihuoma1.zip";
    @Value("${deleteFile}")
    private Boolean deleteFile;

    @Resource
    private DownLoadFile downLoadFile;
    private int i;

    @AfterSendMessages(topic = "idea激活码", isSendWeiXin = true)
    @Scheduled(cron = "0 0 18 * * ?")
    // 每天18点执行一次
    public String execute() throws IOException {
        String ideaCode = getIdeaCode();
        log.info("执行第{}次",++i);
        return ideaCode;
    }

    public String getIdeaCode() throws IOException {
        // 下载压缩文件
        getFile(DOWNLOAD_NAME,DOWNLOAD_PATH);
        // 解压文件 获取ideaCode
        String code = getCode(PARSING_ZIP_FILE);
        // 删除源文件
        if(deleteFile){
            deleteFile(PARSING_ZIP_FILE);
        }
        return code;
    }

    /**
     * 下载压缩文件
     * @param name
     * @param path
     * @throws IOException
     */
    public void getFile(String name,String path) throws IOException {
        downLoadFile.downLoad(DOWNLOAD_URL,name,path);
    }

    /**
     * 解压文件 获取ideaCode
     * @param zipPath
     * @return
     * @throws IOException
     */
    public String getCode(String zipPath) throws IOException {
        DownLoadFile downLoadFile = new DownLoadFile();
        return downLoadFile.unZipFile(zipPath);
    }

    /**
     * 删除源文件
     * @param zipPash
     * @return
     */
    public boolean deleteFile(String zipPash){
        File file = new File(zipPash);
        return file.delete();
    }

}
