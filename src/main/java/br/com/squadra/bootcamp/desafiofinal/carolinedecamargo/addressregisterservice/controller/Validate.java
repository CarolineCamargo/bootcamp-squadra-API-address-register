package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller;

import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception.BusinessException;
import br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.model.entity.Address;
import org.springframework.util.StringUtils;


public class Validate {

    public static void validateStatus(int status){
        if (status != 1 && status != 2){
            throw new BusinessException("O campo status deve ser apenas 1 para ATIVO ou 2 para DESATIVADO");
        }
    }

    public static void validateNameSize(String name, int characters){
        if(name.length() > characters){
            throw new BusinessException("O campo nome deve ter até " + characters + " caracteres.");
        }
    }

    public static void validateLastNameSize(String lastName, int characters){
        if(lastName.length() > characters){
            throw new BusinessException("O campo sobrenome deve ter até " + characters + " caracteres.");
        }
    }

    public static void validateAgeSize(Integer age){
        if(age > 999){
            throw new BusinessException("O campo idade deve ter até 3 caracteres.");
        }
    }

    public static void validateLoginSize(String login, int characters){
        if(login.length() > characters){
            throw new BusinessException("O campo login deve ter até " + characters + " caracteres.");
        }
    }

    public static void validatePasswordSize(String password, int characters){
        if(password.length() > characters){
            throw new BusinessException("O campo senha deve ter até " + characters + " caracteres.");
        }
    }

    public static void validateInitialsSize(String initials){
        if(initials.length() > 3){
            throw new BusinessException("O campo sigla deve ter até 3 caracteres.");
        }
    }

    public static void validateInitialsRequired(String initials){
        if (!StringUtils.hasText(initials)){
            throw new BusinessException("O campo sigla é obrigatório.");
        }
    }

    public static void validateNameRequired(String name){
        if (!StringUtils.hasText(name)){
            throw new BusinessException("O campo nome é obrigatório.");
        }
    }

    public static void validateLastNameRequired(String lastName){
        if (!StringUtils.hasText(lastName)){
            throw new BusinessException("O campo sobrenome é obrigatório.");
        }
    }

    public static void validateAgeRequired(Integer age){
        if (age == 0){
            throw new BusinessException("O campo idade é obrigatório.");
        }
    }

    public static void validateLoginRequired(String login) {
        if (!StringUtils.hasText(login)){
            throw new BusinessException("O campo login é obrigatório.");
        }
    }

    public static void validatePasswordRequired(String password) {
        if (!StringUtils.hasText(password)){
            throw new BusinessException("O campo senha é obrigatório.");
        }
    }

    public static void validateAddress(Address address) {

        if (!StringUtils.hasText(address.getStreet())){
            throw new BusinessException("O campo nomeRua é obrigatório.");
        }

        if (!StringUtils.hasText(address.getNumber())){
            throw new BusinessException("O campo numero é obrigatório.");
        }

        if (!StringUtils.hasText(address.getCep())){
            throw new BusinessException("O campo cep é obrigatório.");
        }

        if(address.getStreet().length() > 256){
            throw new BusinessException("O campo nomeRua deve ter até 256 caracteres.");
        }

        if(address.getNumber().length() > 10){
            throw new BusinessException("O campo numero deve ter até 10 caracteres.");
        }

        if(address.getComplement().length() > 20){
            throw new BusinessException("O campo complemento deve ter até 20 caracteres.");
        }

        if(address.getCep().length() > 10){
            throw new BusinessException("O campo senha cep ter até 10 caracteres.");
        }
    }
}