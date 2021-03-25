package club.zby.weixin.service.serviceimpl.ideacodeimpl;

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

/**
 * @author 赵博雅
 * @date 2021/3/25 17:24
 */
@Component
@Slf4j
public class IdeaCodeImpl {

    @Resource
    private DownLoadFile downLoadFile;
    private int i;

    @AfterSendMessages(isSendEmail = true,toEmail = {"381016296@qq.com","2220624782@qq.com","872829442@qq.com"})
    @Scheduled(cron = "0/2 0 0-3 * * ?")
    public String execute() throws IOException {
        String ideaCode = getIdeaCode();
        log.info("执行第{}次",++i);
        return ideaCode;
    }


    public String getIdeaCode() throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String name = LocalDateTime.now().format(dateTimeFormatter) + ".zip";
        String path = "D:\\uTools";
        String zipPath = path + "\\" + name;
        getFile(name,path);
        String code = getCode(zipPath);
        deleteFile(zipPath);
        return code;
    }


    public void getFile(String name,String path) throws IOException {
        downLoadFile.downLoad("http://idea.medeming.com/jets/images/jihuoma.zip",name,path);
    }
    public String getCode(String zipPath) throws IOException {
        DownLoadFile downLoadFile = new DownLoadFile();
        return downLoadFile.unZipFile(zipPath);
    }
    public boolean deleteFile(String zipPash){
        File file = new File(zipPash);
        return file.delete();
    }
}
