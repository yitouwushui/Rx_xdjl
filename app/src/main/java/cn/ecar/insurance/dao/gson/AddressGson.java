package cn.ecar.insurance.dao.gson;


import java.util.List;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.CustomerAddress;

/**
 *
 * @author ding
 * @date 2018/3/7
 */
public class AddressGson extends BaseGson {


    private List<CustomerAddress> customerAddressDtoList;
    private CustomerAddress customerAddressDto;

    public CustomerAddress getCustomerAddressDto() {
        return customerAddressDto;
    }

    public void setCustomerAddressDto(CustomerAddress customerAddressDto) {
        this.customerAddressDto = customerAddressDto;
    }

    public List<CustomerAddress> getCustomerAddressDtoList() {
        return customerAddressDtoList;
    }

    public void setCustomerAddressDtoList(List<CustomerAddress> customerAddressDtoList) {
        this.customerAddressDtoList = customerAddressDtoList;
    }

}
