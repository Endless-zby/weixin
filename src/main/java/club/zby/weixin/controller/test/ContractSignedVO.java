package club.zby.weixin.controller.test;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.List;

@Data
@Validated
public class ContractSignedVO implements Serializable {
    private static final long serialVersionUID = -5322102549650677106L;

    private List<SignDataInfo> signInfos;



}
