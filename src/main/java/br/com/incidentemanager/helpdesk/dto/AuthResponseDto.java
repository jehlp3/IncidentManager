package br.com.incidentemanager.helpdesk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    String username;
    String accessToken;
    Long expiresIn;

}
