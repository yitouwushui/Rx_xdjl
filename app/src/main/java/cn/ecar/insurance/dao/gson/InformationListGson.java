package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Information;

/**
 * Created by ding on 2018/1/23.
 */

public class InformationListGson extends BaseGson {


    /**
     * informationDto : [{"categoriesId":1,"createBy":"1","createTime":1516377600000,"infoId":1,"publicTime":1516377600000,"sort":1,"source":"1","status":"1","title":"车行易保APP上线啦"}]
     * status : 0
     */

    private List<Information> informationDto;


    public List<Information> getInformationDto() {
        return informationDto;
    }

    public void setInformationDto(List<Information> informationDto) {
        this.informationDto = informationDto;
    }

}
