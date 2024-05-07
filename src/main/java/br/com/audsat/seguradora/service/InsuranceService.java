package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.controller.request.InsuranceRequest;
import br.com.audsat.seguradora.model.Insurance;

public interface InsuranceService {

    Insurance findById(Long id);

    Insurance save(InsuranceRequest insurance);

    Insurance update(Long id, Insurance updatedInsurance);

    Insurance update(Insurance insurance);

    void delete(Long id);
}
