package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.controller.request.InsuranceRequest;
import br.com.audsat.seguradora.model.Claim;
import br.com.audsat.seguradora.model.Insurance;
import br.com.audsat.seguradora.service.ClaimService;
import br.com.audsat.seguradora.service.InsuranceQuoteService;
import br.com.audsat.seguradora.service.InsuranceService;
import br.com.audsat.seguradora.utils.AgeCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsuranceQuoteServiceImpl implements InsuranceQuoteService {

    private static final double BASE_PERCENTAGE = 0.06; // 6%
    private static final double RISK_FACTOR_PERCENTAGE = 0.02; // 2%

    private final InsuranceService insuranceService;
    private final ClaimService claimService;

    @Override
    public Insurance calculateInsuranceQuote(InsuranceRequest insuranceRequest) {
        Insurance insurance = insuranceService.save(insuranceRequest);

        double quote = BASE_PERCENTAGE * insurance.getCar().getFipeValue();

        if (AgeCalculator.isBetween18And25(insurance.getCustomer().getDriver().getBirthdate())) {
            quote += RISK_FACTOR_PERCENTAGE * insurance.getCar().getFipeValue();
        }

        if (claimService.checkDriverAccident(insurance.getCustomer().getDriver())) {
            quote += RISK_FACTOR_PERCENTAGE * insurance.getCar().getFipeValue();
        }

        if (claimService.checkCarAccident(insurance.getCar())) {
            quote += RISK_FACTOR_PERCENTAGE * insurance.getCar().getFipeValue();
        }

        insurance.setQuote(quote);
        return insuranceService.update(insurance);
    }

}
