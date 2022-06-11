package club.zby.weixin.controller.test;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class Enterprise implements Serializable {
    private static final long serialVersionUID = -8842723564441280759L;

//    /**
//     * 企业名称
//     */
//    @NotNull(message = "企业名称不能为空")
//    @Size(max = 150,message = "企业名称最多150字符")
//    private String enterpriseName;

    /**
     * 证件类型编码
     */
    @NotBlank(message = "证件类型编码不能为空")
    @Size(max = 10, message = "证件类型编码无效")
    private String identTypeCode;
    //
//    /**
//     * 证件号码
//     */
//    @NotNull(message = "证件号码不能为空")
//    @Length(max = 80,message = "证件号码无效")
//    private String identNo;
//
//    /**
//     * 证件开始日期
//     */
//    @NotNull(message = "证件开始日期不能为空")
//    @Length(max = 8,message = "证件开始日期无效")
//    private String certStartDate;
//
//    /**
//     * 证件失效日期
//     */
//    @NotNull(message = "证件失效日期不能为空")
//    @Length(max = 8,message = "证件失效日期无效")
//    private String certExpiryDate;
//
//    /**
//     * 邮箱
//     */
//    @NotNull(message = "邮箱不能为空")
//    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式错误")
//    private String email;
//
//    /**
//     * 手机号(企业电子签章的负责人或者经办人的手机号)
//     */
//    @NotNull(message = "企业电子签章的负责人或者经办人的手机号不能为空")
//    @Length(max = 20,message = "企业电子签章的负责人或者经办人的手机号无效")
//    private String mobilePhone;
//
//    /**
//     * 企业联系电话
//     */
//    @NotNull(message = "企业联系电话不能为空")
//    @Length(max = 20,message = "企业联系电话无效")
//    private String landlinePhone;
//
//    /**
//     * 认证方式
//     */
//    @NotNull(message = "企业认证方式不能为空")
//    @Length(max = 10,message = "企业认证方式无效")
//    private String authenticationMode;


    public static void main(String[] args) {
        String ss = "000330,180008,180009,003003,440005,000600,519517,519518,000366,040003,001909,HC0001,003363,003364,001386,0Q0738,0Q4554,400005,400006,100025,100028,070006,070088,002709,002710,5D0070,5D0410,000773,090005,091005,GY0000,GY0001,GY0002,GY9001,GY9002,GY9003,GY9004";
        System.out.println(ss.getBytes().length);
    }
}
