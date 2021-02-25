package club.zby.weixin.service;

import club.zby.weixin.entity.ApiRespones;
import club.zby.weixin.entity.RobotTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 2:00
 */
@Service
public interface RobotService {

    ApiRespones robotToSendByMarkdown(List<RobotTemplate> robotTemplates);
}
