package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.enumeration;

public enum Status {

    ATIVO(1), DESATIVADO(2);

    private final int description;

    Status(int description) {
        this.description = description;
    }

    public int getDescription() {
        return description;
    }
}
