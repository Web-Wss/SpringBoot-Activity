package com.ahnu.activity_api.controller;

import com.ahnu.activity_api.dao.ContractAdminDao;
import com.ahnu.activity_api.model.Agreement;
import com.ahnu.activity_api.model.Contract;
import com.ahnu.activity_api.model.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contractadmin")
public class ContractAdminController {

    @Autowired
    private ContractAdminDao contractAdminDao;

//    获取协议列表
    @GetMapping("/getagreemenetlist")
    public Result GetAgreementList(){
        Result result = new Result();
        List<Agreement> agreements = contractAdminDao.GetAgreementList();
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取协议列表");
        result.setData(agreements);
        return result;
    }

//    新增协议
    @PostMapping("/addagreement")
    public Result AddAgreement(@Param("name")String name,
                               @Param("content")String content){
        Result result = new Result();
        Agreement agreement = new Agreement();
        agreement.setAgreementName(name);
        agreement.setAgreementContent(content);
        Integer integer = contractAdminDao.AddAgreement(agreement);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("新增协议成功");
        result.setData(integer);
        return result;
    }

//    修改协议
    @PostMapping("/editagreementbyid")
    public Result EditAgreementById(@Param("id")Integer id,
                                    @Param("name")String name,
                                    @Param("content")String content){
        Result result = new Result();
        Agreement agreement = new Agreement();
        agreement.setAgreementId(id);
        agreement.setAgreementName(name);
        agreement.setAgreementContent(content);
        Integer integer = contractAdminDao.EditAgreementById(agreement);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("协议修改成功");
        result.setData(integer);
        return result;
    }


//    获取合同列表
    @GetMapping("/getcontractlist")
    public Result GetContractList(){
        Result result = new Result();
        List<Contract> contracts = contractAdminDao.GetContractList();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取合同列表");
        result.setData(contracts);
        return result;
    }

//    删除合同
    @PostMapping("/deletecontractbyid")
    public Result DeleteContractById(@Param("id")Integer id){
        Result result = new Result();
        Integer integer = contractAdminDao.DeleteContractById(id);
        result.setCode(200);
        result.setMsg("删除合同成功");
        result.setSuccess(true);
        result.setData(integer);
        return result;
    }

//    删除协议
    @PostMapping("/deleteagreementbyid")
    public Result DeleteAgreementById(@Param("agreement_id")Integer agreement_id) {
        Result result = new Result();
//        查询活动中是否使用了该协议，使用了返回不能删除，反之可以删除
        System.out.println(agreement_id);
        Integer integer = contractAdminDao.SelectActivityUseAgreement(agreement_id);
        if (integer == 0) {
//            可以删除
            Integer integer1 = contractAdminDao.DeleteAgreementById(agreement_id);
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("可以删除");
            result.setData(integer1);
        }else {
//            不能删除
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("不能删除");
            result.setData(0);
        }
        return result;
    }


}
