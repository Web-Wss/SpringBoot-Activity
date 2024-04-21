package com.ahnu.activity_api.service.impl;

import com.ahnu.activity_api.dao.ContractAdminDao;
import com.ahnu.activity_api.model.Agreement;
import com.ahnu.activity_api.model.Contract;
import com.ahnu.activity_api.service.ContractAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractAdminServiceImpl implements ContractAdminService {

    @Autowired
    private ContractAdminDao contractAdminDao;


    @Override
    public List<Agreement> GetAgreementList() {
        return contractAdminDao.GetAgreementList();
    }

    @Override
    public Integer AddAgreement(Agreement agreement) {
        return contractAdminDao.AddAgreement(agreement);
    }

    @Override
    public Integer EditAgreementById(Agreement agreement) {
        return contractAdminDao.EditAgreementById(agreement);
    }

    @Override
    public List<Contract> GetContractList() {
        return contractAdminDao.GetContractList();
    }

    @Override
    public Integer DeleteContractById(Integer id) {
        return contractAdminDao.DeleteContractById(id);
    }

    @Override
    public Integer SelectActivityUseAgreement(Integer agreement_id) {
        return contractAdminDao.SelectActivityUseAgreement(agreement_id);
    }

    @Override
    public Integer DeleteAgreementById(Integer agreement_id) {
        return contractAdminDao.DeleteAgreementById(agreement_id);
    }
}
