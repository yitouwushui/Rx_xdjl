package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Insurance;
import cn.ecar.insurance.dao.bean.Team;

/**
 * @author ding
 * @date 2018/2/7
 */
public class TeamGson extends BaseGson {

    private List<Team> data;

    public List<Team> getData() {
        return data;
    }

    public void setData(List<Team> data) {
        this.data = data;
    }
}
