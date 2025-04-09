package Agendador.example.Agendador.validacao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CEPValidator implements ConstraintValidator<CEP, String> {

    private static final String CEP_REGEX = "^\\d{5}-?\\d{3}$";

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || cep.isBlank()) {
            return true;
        }
        return cep.matches(CEP_REGEX);
    }
}
