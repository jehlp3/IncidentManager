package br.com.incidentemanager.helpdesk.controller;


import br.com.incidentemanager.helpdesk.dto.AuthResponseDto;
import br.com.incidentemanager.helpdesk.dto.LoginRequestDto;
import br.com.incidentemanager.helpdesk.mapper.UsuarioMapper;
import br.com.incidentemanager.helpdesk.security.CustomUserDetails;
import br.com.incidentemanager.helpdesk.security.JwtUtil;
import br.com.incidentemanager.helpdesk.service.UsuarioService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@OpenAPIDefinition
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthenticationController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper mapper;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authManager;

    @Operation(description = "This method get a bearer token to be used in the system")
    @PostMapping(value = "/token")
    public ResponseEntity doLogin(@RequestBody LoginRequestDto request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            AuthResponseDto tokenResponse = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok().body(tokenResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}