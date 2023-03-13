package com.technocrats.aa.services.rules.impl;

import com.technocrats.aa.dtos.ErrorInfo;
import com.technocrats.aa.dtos.FIFetchResp;
import com.technocrats.aa.model.FIFetchDetail;
import com.technocrats.aa.services.AAClientSvc;
import com.technocrats.aa.services.rules.IProcessGeneratedSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FetchFIData implements IProcessGeneratedSession {

    private final AAClientSvc aaClientSvc;

    @Override
    public Integer getExecutionSeq() {
        return 1;
    }

    @Override
    public Boolean execute(FIFetchDetail fiFetchDetail) {
        try {
            String sessionId = fiFetchDetail.getSessionId();
            FIFetchResp fetchedData = aaClientSvc.fetchDataForSession(sessionId);
            log.info("Fetched Encrypted Data from AA for session: {}", sessionId);
            fiFetchDetail.setData(fetchedData);
            return true;
        } catch (Exception ex) {
            String errorMessage = String.format("Error in Fetching the Financial Data: %s", ex.getMessage());
            log.error(errorMessage);
            fiFetchDetail.setErrorInfo(new ErrorInfo("ERROR IN FETCHING FI DATA", errorMessage));
            return false;
        }
    }
}
