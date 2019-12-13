package com.Bank.System.service.validation;

import com.Bank.System.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidation {

    public void validateCustomer(Customer customer){
        validateCustomerName(customer);
        validateCustomerEmail(customer);
        validateCustomerPhoneNumber(customer);
    }


    private void validateCustomerName(Customer customer) {
        String name = customer.getCustomerName();
        String patternName = "[A-Z][a-z]{0,25}\\s[A-Z][a-z]{0,25}\\s[A-Z][a-z]{0,25}";
         if(!name.matches(patternName)){
             throw new RuntimeException("Invalid Customer Name! Name pattern must be (Firstname Surname Familyname)");

         }
    }

    private void validateCustomerEmail(Customer customer) {
        String email = customer.getEmail();
        String patternName = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b";
        if(!email.matches(patternName)){
            throw new RuntimeException("Invalid E-mail");
        }
    }

    private void validateCustomerPhoneNumber(Customer customer) {
        String mobilePhone = customer.getPhoneNumber();
        String patternName = "(\\+)?(359|0)8[3456789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}";
        if(!mobilePhone.matches(patternName)){
            throw new RuntimeException("Invalid mobile phone! Mobile phone must be +3598XX XXX XXX, +3598XXXXXXXX,+3598XX-XXX-XXX or 08XX XXX XXX, 08XXXXXXXX, 08XX-XXX-XXX ");
        }

    }

  }
