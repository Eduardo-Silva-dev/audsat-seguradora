package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.controller.request.InsuranceRequest;
import br.com.audsat.seguradora.model.Insurance;

public interface InsuranceQuoteService {

    Insurance calculateInsuranceQuote(InsuranceRequest insuranceRequest);

}
