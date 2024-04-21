package com.ahnu.activity_api.service;

import com.ahnu.activity_api.model.Agreement;
import com.ahnu.activity_api.model.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractAdminService {

    //    获取协议列表
    List<Agreement> GetAgreementList();

    //    新增协议
    Integer AddAgreement(Agreement agreement);

    //    修改协议
    Integer EditAgreementById(Agreement agreement);

    //    获取合同列表
    List<Contract> GetContractList();

    //    删除合同根据id
    Integer DeleteContractById(@Param("id")Integer id);

    //    删除协议
//    查询活动表中是否使用了该协议
    Integer SelectActivityUseAgreement(@Param("agreement_id")Integer agreement_id);
    //    删除
    Integer DeleteAgreementById(@Param("agreement_id")Integer agreement_id);
}
