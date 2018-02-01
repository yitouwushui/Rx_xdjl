package cn.ecar.insurance.dao.gson;

import com.google.gson.annotations.SerializedName;

import cn.ecar.insurance.dao.base.BaseGson;
import cn.ecar.insurance.dao.bean.SaveQuote;
import cn.ecar.insurance.dao.bean.UserInfo;

/**
 *
 * @author ding
 * @date 2018/2/1
 */

public class InsuranceInfoGson extends BaseGson {


    /**
     * reInfoDto : {"saveQuote":{"Shebeis":[],"boli":"0","bujimianchengke":0,"bujimianchesun":1,"bujimiandaoqiang":1,"bujimianhuahen":0,"bujimianjingshensunshi":0,"bujimiansanzhe":1,"bujimiansheshui":0,"bujimiansiji":0,"bujimianziran":0,"chengke":0,"chesun":60572,"daoqiang":60572,"hcjingshensunshi":0,"hcsanfangteyue":0,"huahen":0,"licenseno":"苏AS37S1","sanzhe":300000,"savequoteId":10,"shebeis":[],"sheshui":0,"siji":0,"source":4,"ziran":0},"userInfo":{"businessexpiredate":1504972800000,"carusedtype":"1","carvin":"LSJZ14E65DS034002","citycode":"1","clausetype":"2","credentislasnum":"320114198411191812","engineno":"B2GD3040576","fueltype":"1","holderidcard":"320114198411191812","holderidtype":"1","holdermobile":"","idtype":"1","insuredidcard":"320114198411191812","insuredidtype":"1","insuredmobile":"","insuredname":"徐文杰","ispublic":"2","licensecolor":"1","licenseno":"苏AS37S1","licenseowner":"徐文杰","modlename":"名爵CSA7153ACS轿车","nextbusinessstartdate":1516809600000,"postedname":"徐文杰","prooftype":"1","purchaseprice":79700,"queryDate":1517328000000,"ratefactor1":1,"ratefactor2":1,"ratefactor3":1,"ratefactor4":1,"registerdate":1367078400000,"runregion":"1","seatcount":5,"userinfoId":12},"saveQuote":{"$ref":"$.reInfoDto.saveQuote"},"userInfo":{"$ref":"$.reInfoDto.userInfo"}}
     */

    private ReInfoDtoBean reInfoDto;

    public ReInfoDtoBean getReInfoDto() {
        return reInfoDto;
    }

    public void setReInfoDto(ReInfoDtoBean reInfoDto) {
        this.reInfoDto = reInfoDto;
    }

    public static class ReInfoDtoBean {
        /**
         * saveQuote : {"Shebeis":[],"boli":"0","bujimianchengke":0,"bujimianchesun":1,"bujimiandaoqiang":1,"bujimianhuahen":0,"bujimianjingshensunshi":0,"bujimiansanzhe":1,"bujimiansheshui":0,"bujimiansiji":0,"bujimianziran":0,"chengke":0,"chesun":60572,"daoqiang":60572,"hcjingshensunshi":0,"hcsanfangteyue":0,"huahen":0,"licenseno":"苏AS37S1","sanzhe":300000,"savequoteId":10,"shebeis":[],"sheshui":0,"siji":0,"source":4,"ziran":0}
         * userInfo : {"businessexpiredate":1504972800000,"carusedtype":"1","carvin":"LSJZ14E65DS034002","citycode":"1","clausetype":"2","credentislasnum":"320114198411191812","engineno":"B2GD3040576","fueltype":"1","holderidcard":"320114198411191812","holderidtype":"1","holdermobile":"","idtype":"1","insuredidcard":"320114198411191812","insuredidtype":"1","insuredmobile":"","insuredname":"徐文杰","ispublic":"2","licensecolor":"1","licenseno":"苏AS37S1","licenseowner":"徐文杰","modlename":"名爵CSA7153ACS轿车","nextbusinessstartdate":1516809600000,"postedname":"徐文杰","prooftype":"1","purchaseprice":79700,"queryDate":1517328000000,"ratefactor1":1,"ratefactor2":1,"ratefactor3":1,"ratefactor4":1,"registerdate":1367078400000,"runregion":"1","seatcount":5,"userinfoId":12}
         * saveQuote : {"$ref":"$.reInfoDto.saveQuote"}
         * userInfo : {"$ref":"$.reInfoDto.userInfo"}
         */
        @SerializedName("SaveQuote")
        private SaveQuote saveQuote;
        @SerializedName("UserInfo")
        private UserInfo userInfo;

        public SaveQuote getSaveQuote() {
            return saveQuote;
        }

        public void setSaveQuote(SaveQuote SaveQuote) {
            this.saveQuote = SaveQuote;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo UserInfo) {
            this.userInfo = UserInfo;
        }

    }
}
