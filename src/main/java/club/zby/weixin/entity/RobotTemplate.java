package club.zby.weixin.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/7 21:00
 */

@Data
@Builder
public class RobotTemplate {

    public static final String INFO = "info";
    public static final String COMMENT = "comment";
    public static final String WARNING = "warning";

    private String title;
    private String value;
    private String color;

    public static  String template = ">{1}<font color=\"{2}\">{3}</font>\n";


    public String buildRobotInfo(){
        return template.replace("{1}", title).replace("{3}", value).replace("{2}", color);
    }


}
