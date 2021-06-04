package domain.validators;

import domain.Angajat;

public class AngajatValidator implements Validator<Angajat>{
    @Override
    public void validate(Angajat entity) throws ValidationException {
        
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
        /*

        if (entity.getOraConectare().isAfter(entity.getOraDeconectare())){
            errMsg += "Ora conectare error!\n";
        }

        if(entity.getOraDeconectare().isBefore(entity.getOraConectare())){
            errMsg += "Ora deconectare error!\n";
        }*/

        if (!errMsg.equals("")) {
            throw new ValidationException(errMsg);
        }
    }
}
