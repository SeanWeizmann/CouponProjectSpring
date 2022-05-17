package coupon_project.repositories;

import coupon_project.entities.Coupon;
import coupon_project.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    /**
     * This method allows you to check if customer exists in the DB by his details
     * @param email checking by his email
     * @param pass checking by his password
     * @return true/false- Boolean
     */
    boolean existsByEmailAndPassword(String email, String pass);

    /**
     * This method allows you to check if customer exists in the DB by his email only
     * @param email checking by email
     * @return true/false- Boolean
     */
    boolean existsByEmail(String email);

    /**
     * This method allows you to get customer id
     * @param email get id by email
     * @return customer's id  number- Integer
     */
    int getCustomerIdByEmail(String email);

    /**
     * This method allows you to delete customer by id
     * @param customerId choosing customer by id number
     */
    void deleteCustomerById(int customerId);

}
