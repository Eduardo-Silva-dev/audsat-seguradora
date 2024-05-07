package br.com.audsat.seguradora.controller;


import br.com.audsat.seguradora.controller.mapper.InsuranceMapper;
import br.com.audsat.seguradora.controller.request.InsuranceRequest;
import br.com.audsat.seguradora.controller.response.BudgetResponse;
import br.com.audsat.seguradora.model.Insurance;
import br.com.audsat.seguradora.service.InsuranceQuoteService;
import br.com.audsat.seguradora.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insurance/budget")
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final InsuranceQuoteService insuranceQuoteService;
    private final InsuranceMapper insuranceMapper;

    @Operation(
            summary = "Retrieve a Budget by Id",
            description = "Get a Budget object by specifying its id. The response is Budget object with id, title, description and published status.",
            tags = { "budget", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = BudgetResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{insuranceId}")
    public ResponseEntity<?> findById(@PathVariable Long insuranceId) {
        Insurance insurance = insuranceService.findById(insuranceId);
        BudgetResponse budgetResponse = insuranceMapper.toBudgetResponse(insurance);
        return ResponseEntity.ok().body(toLinks(budgetResponse));
    }

    @Operation(
            summary = "Insert a new Insurance",
            description = "Insert a new insurance based on the provided information in the request body.",
            tags = { "budget", "insert" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = BudgetResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody InsuranceRequest insuranceRequest) {
        Insurance insurance = insuranceQuoteService.calculateInsuranceQuote(insuranceRequest);
        BudgetResponse budgetResponse = insuranceMapper.toBudgetResponse(insurance);
        return ResponseEntity.ok().body(toLinks(budgetResponse));
    }

    @Operation(
            summary = "Update an existing Insurance",
            description = "Update an existing insurance with the provided information in the request body.",
            tags = { "budget", "update" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = BudgetResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{insuranceId}")
    public ResponseEntity<?> update(@PathVariable Long insuranceId, @RequestBody Insurance insuranceRequest) {
        Insurance insurance = insuranceService.update(insuranceId, insuranceRequest);
        BudgetResponse budgetResponse = insuranceMapper.toBudgetResponse(insurance);
        return ResponseEntity.ok().body(toLinks(budgetResponse));
    }

    @Operation(
            summary = "Delete an existing Insurance",
            description = "Delete an existing insurance by its ID.",
            tags = { "budget", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Insurance deleted successfully"),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{insuranceId}")
    public ResponseEntity<Void> delete(@PathVariable Long insuranceId){
        insuranceService.delete(insuranceId);
        return ResponseEntity.noContent().build();
    }

    private BudgetResponse toLinks(BudgetResponse budgetResponse){
        if (budgetResponse == null) {
            throw new IllegalArgumentException("BudgetResponse cannot be null");
        }

        budgetResponse.add(linkTo(methodOn(InsuranceController.class)
                .findById(budgetResponse.getId())).withSelfRel());

        budgetResponse.add(linkTo(methodOn(InsuranceController.class)
                .update(budgetResponse.getId(), new Insurance())).withRel("Update"));

        budgetResponse.add(linkTo(methodOn(InsuranceController.class)
                .delete(budgetResponse.getId())).withRel("Delete"));

        return budgetResponse;
    }
}
