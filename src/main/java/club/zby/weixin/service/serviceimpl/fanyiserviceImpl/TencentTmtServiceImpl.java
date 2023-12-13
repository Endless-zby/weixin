package club.zby.weixin.service.serviceimpl.fanyiserviceImpl;

import club.zby.weixin.service.FanYiService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TencentTmtServiceImpl implements FanYiService {
    @Override
    public String textTranslate(String sourceText, String source, String target) throws TencentCloudSDKException {
        Credential cred = new Credential("AKID1zymV2Y0FHC6vm8KNCuUxGnEYnsslSxF", "BKJLDtMfyaBanncBZryqhdn0DKF89xZI");
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("tmt.tencentcloudapi.com");
        // 实例化一个client选项，可选的，没有特殊需求可以跳过
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        // 实例化要请求产品的client对象,clientProfile是可选的
        TmtClient client = new TmtClient(cred, "", clientProfile);
        // 实例化一个请求对象,每个接口都会对应一个request对象
        TextTranslateRequest req = new TextTranslateRequest();
        req.setSourceText(sourceText);
        req.setSource(source);
        req.setTarget(target);
        // 返回的resp是一个TextTranslateResponse的实例，与请求对象对应
        TextTranslateResponse resp = client.TextTranslate(req);
        // 输出json格式的字符串回包
        System.out.println(TextTranslateResponse.toJsonString(resp));
        return resp.getTargetText();
    }
}
