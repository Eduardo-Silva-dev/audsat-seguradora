package br.com.audsat.seguradora.controller.request;

import br.com.audsat.seguradora.model.enums.UserRole;

public record RegisterRequest(String login, String password, UserRole role)  {
}
