package springboot.enu;

/**
 * Description: TODO
 * CreateTime: 2022/8/9 18:48
 *
 * @author: dxz
 */

import lombok.Getter;

/**
 * 当前设计不支持一个用户有多个角色，所以AND暂时用不到，而OR的作用更多的是提示当前逻辑是“符合其中一个角色即可”
 */
@Getter
public enum Logical {
    AND,
    OR;
}

