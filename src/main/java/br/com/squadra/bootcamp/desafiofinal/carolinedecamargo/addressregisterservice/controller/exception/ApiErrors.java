package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
public class ApiErrors {

    @JsonProperty("mensagem")
    private String message;
    private int status;

    public ApiErrors(ResponseStatusException ex) {
        this.message = ex.getReason();
        this.status = ex.getStatus().value();
    }
}

