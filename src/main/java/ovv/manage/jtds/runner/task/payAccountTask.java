package ovv.manage.jtds.runner.task;

import ovv.manage.jtds.entity.PayAccount;
import ovv.manage.jtds.entity.PayInfo;
import ovv.manage.jtds.entity.UserInfo;
import ovv.manage.jtds.serviceimpl.LoginServiceImpl;
import ovv.manage.jtds.serviceimpl.PayInfoServiceImpl;
import ovv.manage.jtds.utils.JedisUtil;
import ovv.manage.jtds.utils.JtdsCommon;
import ovv.manage.jtds.utils.JtdsUtils;
import ovv.manage.jtds.utils.springBeanUtil;

import java.util.HashMap;
import java.util.List;

public class payAccountTask implements Runnable {

    PayInfoServiceImpl payService;
    LoginServiceImpl loginService;

    public payAccountTask(){
        this.payService = springBeanUtil.getBean(PayInfoServiceImpl.class);
        /*if(payService == null){
            payService = new PayInfoServiceImpl();
        }*/
        this.loginService = springBeanUtil.getBean(LoginServiceImpl.class);
        /*if(payService == null){
            payService = new PayInfoServiceImpl();
        }*/
    }

    @Override
    public void run() {
        //存放统计数据
        HashMap<String, PayAccount> accountMap = new HashMap<String,PayAccount>();
        HashMap<String,String> hasPayMap = new HashMap();
        HashMap<String,String> shouldPayMap = new HashMap();
        try{
            PayInfo info =new PayInfo();
            info.setIsAccount(0);
            List<PayInfo> list = payService.queryPayInfo(info);
            for(PayInfo pay: list)
            {
                String userId = pay.getRecordUserId();
                //计算已支付
                hasPayMap.put(pay.getRecordUserId(), JtdsUtils.add(JtdsUtils.getMapValue(hasPayMap,userId,"0"),pay.getAmt()));
                //计算应收付
                String[] userIds = pay.getInvolveUserId().split(",");
                int n = userIds.length;
                String avgAmt = JtdsUtils.divide(pay.getAmt(),n+"",2);
                for(String id : userIds){
                    String shouldPay;
                    if(id.equals(userId))
                        shouldPay = JtdsUtils.sub(pay.getAmt(),avgAmt);
                    else
                        shouldPay = "-" + avgAmt;
                    shouldPayMap.put(id,JtdsUtils.add(JtdsUtils.getMapValue(shouldPayMap,id,"0"),shouldPay));
                }
            }

            List<UserInfo> users = loginService.queryUserName();
            for(UserInfo user: users ){
                PayAccount oneUser = new PayAccount();
                oneUser.setUserId(user.getId());
                oneUser.setIsAccount(JtdsCommon.NO);
                oneUser.setUserName(user.getUserName());
                oneUser.setAccountNo(JedisUtil.getJedis().get("accountNo"));
                oneUser.setHasPay(JtdsUtils.getMapValue(hasPayMap,user.getId(),"0"));
                oneUser.setShouldPay(JtdsUtils.getMapValue(shouldPayMap,user.getId(),"0"));
                oneUser.setUpdateTime(JtdsUtils.getCurrentDate());

                List userAccount = payService.queryPayAccount(oneUser);
                if(!userAccount.isEmpty()){
                    if(userAccount.size()>1) {
                        System.out.println(user.getUserName()+"的账单数据异常！");
                        continue;
                    }
                    oneUser.setId(((PayAccount)userAccount.get(0)).getId());
                    payService.updatePayAccount(oneUser);
                }else{
                    oneUser.setId(JtdsUtils.getPayAccountId());
                    payService.insertPayAccount(oneUser);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
