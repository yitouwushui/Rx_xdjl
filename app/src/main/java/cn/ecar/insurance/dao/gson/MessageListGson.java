package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.widget.jsbridge.Message;

/**
 * Created by ding on 2018/1/23.
 */

public class MessageListGson extends BaseGson {


    /**
     * responseCode : 0
     * status : 0
     * messageShowDto : []
     * responseMsg : 获取信息列表信息成功!
     */

    private List<Message> messageShowDto;

    public List<?> getMessageShowDto() {
        return messageShowDto;
    }

    public void setMessageShowDto(List<Message> messageShowDto) {
        this.messageShowDto = messageShowDto;
    }
}
