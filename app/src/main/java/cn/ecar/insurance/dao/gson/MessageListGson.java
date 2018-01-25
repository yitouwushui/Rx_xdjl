package cn.ecar.insurance.dao.gson;

import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.Message2;

/**
 *
 * @author ding
 * @date 2018/1/23
 */

public class MessageListGson extends BaseGson {


    /**
     * responseCode : 0
     * status : 0
     * messageShowDto : []
     * responseMsg : 获取信息列表信息成功!
     */

    private List<Message2> messageShowDto;

    public List<Message2> getMessageShowDto() {
        return messageShowDto;
    }

    public void setMessageShowDto(List<Message2> messageShowDto) {
        this.messageShowDto = messageShowDto;
    }

    @Override
    public String toString() {
        return "MessageListGson{" +
                "messageShowDto=" + messageShowDto +
                '}';
    }
}
