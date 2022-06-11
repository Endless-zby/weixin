package club.zby.weixin.controller.test;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class TrxDevcInf implements Serializable {

    private static final long serialVersionUID = -8547172000570054482L;


    @NotBlank(message = "IP不能为空")
    private String ip;
    @NotBlank(message = "终端类型不能为空")
    private String terminal;
    @NotBlank(message = "交易设备交易设备MAC不能为空")
    private String mac;
    private String imei;
    private String imsi;
    private String wifiMac;
    private String gps;



}
