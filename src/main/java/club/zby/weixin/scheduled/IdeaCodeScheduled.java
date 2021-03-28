package club.zby.weixin.scheduled;

import club.zby.weixin.entity.interfaces.AfterSendMessages;
import club.zby.weixin.until.DownLoadFile;
import lombok.extern.slf4j.Slf4j;
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

    private static final String DOWNLOAD_PATH = "D:\\uTools";
    private static final String DOWNLOAD_NAME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".zip";
    private static final String PARSING_ZIP_FILE = DOWNLOAD_PATH + "\\" + DOWNLOAD_NAME;
    private static final String DOWNLOAD_URL = "http://idea.medeming.com/jets/images/jihuoma.zip";

    @Resource
    private DownLoadFile downLoadFile;
    private int i;

    @AfterSendMessages(isSendEmail = true, topic = "idea激活码", toEmail = {"381016296@qq.com", "2220624782@qq.com", "872829442@qq.com"})
    @Scheduled(cron = "0/2 0 0-3 * * ?")
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
        deleteFile(PARSING_ZIP_FILE);
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
