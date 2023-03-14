package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.dtos.*;
import com.technocrats.aa.model.ConsentReqTemplates;
import com.technocrats.aa.model.ConsentRequestDetail;
import com.technocrats.aa.repo.ConsentReqTemplatesRepo;
import com.technocrats.aa.services.rules.IProcessConsentRequest;
import com.technocrats.aa.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrepareConsentReq implements IProcessConsentRequest {

    private final ConsentReqTemplatesRepo consentReqTemplatesRepo;

    @Override
    public Integer getExecutionSeq() {
        return 1;
    }

    @Override
    public Boolean execute(ConsentRequestDetail consentRequestDetail) {
        try {
            Purpose purpose = consentRequestDetail.getUiConsentReq().getPurpose();
            String aaId = consentRequestDetail.getUiConsentReq().getAa().getId();
            ConsentReqTemplates consentReqTemplate = consentReqTemplatesRepo.findByPurposeCode(purpose.getCode());
            ConsentReq consentReq = consentReqTemplate.getConsentReqTemplate();
            Date dateNow = new Date();
            consentReq.setTimestamp(dateNow);
            consentReq.setTxnid(UUID.randomUUID().toString());
            ConsentReqValues consentReqValues = consentReq.getConsentDetail();
            consentReqValues.setConsentStart(dateNow);
            consentReqValues.setConsentExpiry(DateTimeUtils.dateXMonthsLater(3, dateNow));
            consentReqValues.setCustomer(new Customer(aaId));
            consentReqValues.setDataConsumer(new DataConsumerFIU("GOL0134"));
            consentReqValues.setFIDataRange(new FIDataRange(DateTimeUtils.dateXMonthsLater(-3, dateNow), dateNow));
            consentRequestDetail.setConsentReq(consentReq);
            return true;
        } catch (Exception ex) {
            log.error("Error in Preparing the Consent Request: {}", ex.getMessage());
            consentRequestDetail.setErrorInfo(new ErrorInfo("1", ex.getMessage()));
            return false;
        }
    }
}
