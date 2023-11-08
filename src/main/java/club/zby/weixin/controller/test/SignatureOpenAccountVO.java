package club.zby.weixin.controller.test;

import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
public class SignatureOpenAccountVO implements Serializable {

    private static final long serialVersionUID = -5995080649503025461L;

    /**
     * 企业基本信息
     */
    @Valid
    private Enterprise enterprise;
    /**
     * 交易设备信息（证联开户用）
     */
    private TrxDevcInf trxDevcInf;

    private String platformId;
}
