package cn.ecar.insurance.net;

/**
 * Created by ding on 2018/1/26.
 */

public class JsonTest {

/*
 {
        "responseCode":"EC0000", "status":"0", "responseMsg":"获取信息成功!", "customerInfo":{
        "activationDate":1515403234000, "cashAccountDto":{
            "accountType":"2", "activateTime":1515400526000, "balance":-1200.00, "cashAccountId":
            3, "customerId":4, "ecarBalance":0.00, "ecarFrozenBalance":0.00, "frozenBalance":
            0.00, "mobile":"13818178881", "opendate":1515400526000, "pw":
            "aea15b2d0d22b31ffb95e35c89744379", "salt":
            "78b6612e-032d-49ab-9917-1ed0d566931a", "status":"1", "totalBalance":0.00
        },"customerCode":"C18010800017", "customerId":4, "endDate":1546939234000, "firstAgentId":
        2, "identity":"1", "phoneNo":"13818178881", "pw":
        "e88dba5e278e3dffedd726e9443de08d", "registTime":1515400526000, "salt":
        "84657455-a07d-4da7-9831-7e245f778f25", "secondAgentId":-1, "status":"1", "thirdAgentId":
        -1, "type":"2"
    }
    }

  */


/*
    http://192.168.2.38:8282/ecar-front/getBranchBankList.do?bankCode=102&cityCode=11001100

    {
        "responseCode":"EC0000", "status":"0", "bankNumberDtoList":[{
        "bankId":1, "bankNo":"102", "binId":237, "branchName":"中国工商银行股份有限公司天津北城支行", "branchNo":
        "102110001639"
    },{
        "bankId":1, "bankNo":"102", "binId":723, "branchName":"中国工商银行股份有限公司天津海福支行", "branchNo":
        "102110087180"
    }],"responseMsg":"获取信息成功!"
    }

    http://192.168.2.38:8282/ecar-front/bindBank.do?name=张三&certificateCode=413026198709091818&bankId=1&bankCardNo=6222021001098555503&branchName=滨江外滩支行&branchNo=10922644

    {
        "responseCode":"EC0000", "status":"0", "responseMsg":"绑定银行卡成功!"
    }
*/

}
