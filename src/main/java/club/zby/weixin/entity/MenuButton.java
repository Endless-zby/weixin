package club.zby.weixin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author byzhao
 * @version 1.0
 * @date 2020/11/8 11:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuButton extends ApiRespones  {

    private List<MenuTemplate> button;

}
