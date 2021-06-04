package domain.validators;

import domain.Sef;

public class SefValidator implements Validator<Sef>{
    @Override
    public void validate(Sef entity) throws ValidationException {

        String errMsg = "";

        if (entity.getId() == null || entity.getId() < 0) {
            errMsg += "Id error!\n";
        }

        if (entity.getNume() == null || "".equals(entity.getNume())) {
            errMsg += "Nume error!\n";
        }

        if (entity.getUsername() == null || "".equals(entity.getUsername())) {
            errMsg += "Username error!\n";
        }

        if (entity.getParola() == null || "".equals(entity.getParola())) {
            errMsg += "Parola error!\n";
        }

        if (!errMsg.equals("")) {
            throw new ValidationException(errMsg);
        }
    }
}
