
package tooldemo;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.model.User;
@Controller
@ContextConfiguration
public class beanValidation {
    @RequestMapping("/save")
    public String save(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "error";
        }
        return "success";
    }
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();  
        context.setValidating(false);  
        context.load("classpath:spring*.xml");  
        context.refresh();  
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		User user = new User();
		Set<ConstraintViolation<User>> constraintViolations = validator
				.validate(user);

		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			System.out.println(constraintViolation.getMessage());
		}
	}
	}
