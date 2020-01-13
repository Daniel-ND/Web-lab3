import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "YValidator")
public class YValidator implements javax.faces.validator.Validator {
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            double parsedValue = Double.parseDouble(o.toString());
            if (!(parsedValue > -5 && parsedValue < 3)) {
                FacesMessage message = new FacesMessage("Y is not in (-5 ... 3)");
                throw new ValidatorException(message);
            }
        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage( "Y is not a number");
            throw new ValidatorException(message);
        }
    }

}