package cn.ecar.insurance.mvvm.view.act.main;

/**
 *
 * @author ding
 * @date 2018/1/25
 */

public interface ChoiceInterFace {

    /**
     * 返回stringID
     * @return
     */
    String getSelectString();
    /**
     * 返回intID
     * @return
     */
    Integer getSelectInteger();

    /**
     * 返回显示content
     * @return
     */
    String getSelectContent();
}
