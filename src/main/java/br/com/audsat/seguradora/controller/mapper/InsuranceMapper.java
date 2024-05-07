package br.com.audsat.seguradora.controller.mapper;

import br.com.audsat.seguradora.controller.response.BudgetResponse;
import br.com.audsat.seguradora.model.Insurance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    BudgetResponse toBudgetResponse(Insurance insurance);
}
