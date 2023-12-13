package club.zby.weixin.entity;

import lombok.Data;

@Data
public class FanYiRequest {

   private String sourceText;
   private String source = "auto";
   private String target = "zh";


}
