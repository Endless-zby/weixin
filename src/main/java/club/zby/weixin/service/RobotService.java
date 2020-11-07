package club.zby.weixin.service;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.RobotTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RobotService {

    ApiRespones robotToSendByMarkdown(List<RobotTemplate> robotTemplates);
}
