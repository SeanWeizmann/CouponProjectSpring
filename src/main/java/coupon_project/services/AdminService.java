package coupon_project.services;

import coupon_project.entities.Company;
import coupon_project.entities.Coupon;
import coupon_project.entities.Customer;
import coupon_project.exception.InvalidException;
import coupon_project.exception.NotFoundException;
import coupon_project.exception.SomethingWentWrongException;
import coupon_project.repositories.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminService extends ClientService {


    /**
     * Login to system
     * @param email email required
     * @param password password required
     * @return true if you logged in
     */
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     * This method allows the administrator to add a company
     * @param company need to initialize the object
     * @throws InvalidException if some values were already taken
     */
    public void addCompany(Company company) throws  InvalidException {
        if (companyRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new InvalidException("Name or Email already in use");
        }
        companyRepo.save(company);
    }

    /**
     * This method allows the administrator to update company's values
     * @param company update and choosing a company
     * @throws NotFoundException if company does not exist in the DB
     * @throws InvalidException if some values were already taken
     */
    public void updateCompany(Company company) throws NotFoundException, InvalidException {
        if (!companyRepo.existsById(company.getId())) {
            throw new NotFoundException("company with id- " + company.getId()+" does not exists");
        }
        if (companyRepo.existsByEmail(company.getEmail())) {
            if (!companyRepo.getById(company.getId()).getEmail().equals(company.getEmail())) {
                throw new InvalidException("Email already exists in a different company");
            }
        }
        if (!companyRepo.getById(company.getId()).getName().equals(company.getName())) {
            throw new InvalidException("you cannot update company's name");
        }
        companyRepo.save(company);
    }

    /**
     * This method allows the administrator to delete a company
     * @param companyId delete bu id
     * @throws NotFoundException if id not exists in the DB
     */
    public void deleteCompany(int companyId) throws NotFoundException {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("company with id " + companyId + " does not exist");
        }
        companyRepo.deleteById(companyId);
    }

    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    /**
     * This method allows to represent a single company
     * @param companyId choosing the company by id
     * @return an instance of object company
     * @throws NotFoundException if id not exists in the DB
     */
    public Company getOneCompany(int companyId) throws NotFoundException {
        if (!companyRepo.existsById(companyId)) {
            throw new NotFoundException("company with id " + companyId + " does not exist");
        }
        return companyRepo.findById(companyId).get();
    }

    /**
     * This method allows the administrator to add a customer
     * @param customer need to initialize the object
     * @throws InvalidException if some values were already taken
     */
    public void addCustomer(Customer customer) throws InvalidException {
        if (!customerRepo.existsByEmail(customer.getEmail())) {
            customerRepo.save(customer);
        }else {
        throw new InvalidException("Email already taken");
        }
    }

    /**
     * This method allows the administrator to update customer's values
     * @param customer choosing and update a customer
     * @throws NotFoundException if id not exists
     * @throws InvalidException if some values were already taken
     */
    public void updateCustomer(Customer customer) throws NotFoundException, InvalidException {
        if (!companyRepo.existsById(customer.getId())){
            throw new NotFoundException("customer with id " + customer.getId() + " does not exist");
        }
        if (customerRepo.existsByEmail(customer.getEmail()) && !companyRepo.getById(customer.getId()).getEmail().equals(customer.getEmail())){
            throw new InvalidException("email already taken by a different customer");
        }
        customerRepo.save(customer);
    }

    /**
     * This method allows the administrator to delete a customer
     * @param customerId choosing the customer by id
     * @throws NotFoundException if id not exists
     */
    public void deleteCustomer(int customerId) throws NotFoundException {
        if (!customerRepo.existsById(customerId)){
            throw new NotFoundException("customer with id " + customerId + " does not exist");
        }
        customerRepo.deleteById(customerId);
    }

    /**
     * This method allows the administrator to represent all customers
     * @return a list of customers
     */
    public List<Customer> getAllCustomers(){
       return customerRepo.findAll();
    }

    /**
     * This method allows the administrator to get a single customer
     * @param customerId choosing by id
     * @return an instance of object
     * @throws NotFoundException if id not exists
     */
    public Customer getOneCustomer(int customerId) throws NotFoundException {
        if (!customerRepo.existsById(customerId)){
            throw new NotFoundException("customer with id " + customerId + " does not exist");
        }
        return customerRepo.findById(customerId).get();
    }

    /**
     * This method allows the administrator to represent all customers
     * @return a list of customers
     */
    public List<Coupon> getAllCoupons(){
        return couponRepo.findAll();
    }
}
