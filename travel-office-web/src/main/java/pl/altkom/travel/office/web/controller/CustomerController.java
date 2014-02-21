/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.altkom.travel.office.web.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.altkom.travel.office.web.dao.CustomerDAO;
import pl.altkom.travel.office.web.model.Customer;

/**
 *
 * @author altkom
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping("/display-customers")
    public ModelAndView displayCustomers() {

        Iterable<Customer> customers = customerDAO.findAll();
        ModelAndView model = new ModelAndView("display-customers");
        model.addObject("customers", customers);

        return model;
    }

    @RequestMapping("/customer_details")
    public ModelAndView customerDetails(@RequestParam int id) {

        Customer customer = customerDAO.findOne(id);

        ModelAndView model = new ModelAndView("customer_details");
        model.addObject("customer", customer);

        return model;
    }

    @RequestMapping(value = "/save_customer", method = RequestMethod.POST)
    public String saveCustomer(@Valid Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer_details";
        } else {
            customerDAO.save(customer);
            return "redirect:/display-customers.htm";
        }

    }

}
