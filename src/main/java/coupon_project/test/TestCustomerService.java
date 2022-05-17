package coupon_project.test;

import coupon_project.entities.Categories;
import coupon_project.exception.LoginException;
import coupon_project.exception.NotFoundException;
import coupon_project.login.ClientType;
import coupon_project.login.LoginManager;
import coupon_project.services.CustomerService;
import coupon_project.utils.ArtUtils;
import coupon_project.utils.TablePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import static coupon_project.test.Constants.*;

@Service
public class TestCustomerService {

    @Autowired
    ApplicationContext ctx;
    private CustomerService customerService;

    public void testAll(){
        System.out.println(ArtUtils.startTestingCustomerFacade);
        testLogin();
        testAddCouponPurchase();
        testGetAllCustomerCoupons();
        testGetAllCustomerCouponsByCategory();
        testGetAllCustomerCouponByMaxPrice();
        testGetCustomerDetail();
    }

    public void testLogin(){
        try {
            System.out.println("========== test customer login ============");
            System.out.print("test customer login false: ");
            customerService = (CustomerService) LoginManager.login("asd@gmail.com", "45612", ClientType.CUSTOMER,ctx);
        }catch (LoginException err){
            System.out.println(err.getMessage());
        }try {
            System.out.print("test customer login true: ");
            customerService = (CustomerService) LoginManager.login(CUSTOMER1.getEmail(), CUSTOMER1.getPassword(), ClientType.CUSTOMER,ctx);
        }catch (LoginException err){
            System.out.println(err.getMessage());
        }
    }

    public void testAddCouponPurchase(){
        try{
            System.out.println("=========test successful add coupon purchase==========");
            customerService.addCouponPurchase(1, 1);
            customerService.addCouponPurchase(1, 2);
            customerService.addCouponPurchase(2, 6);
            customerService.addCouponPurchase(2, 5);
            System.out.println(SU);
            System.out.println("=========test failed add coupon purchase==========");
            customerService.addCouponPurchase(1,1);
        }catch (Exception err){
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCustomerCoupons(){
        try {
            printTest("successful get all customer's coupons");
//            System.out.println(customerFacade.getAllCustomerCoupon(5));
            TablePrinter.print(customerService.getAllCustomerCoupons(1));
        }catch (NotFoundException err){
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCustomerCouponsByCategory(){
        try {
            printTest("successful get all customer's coupons by category");
//            System.out.println(customerFacade.getAllCustomerCouponsByCategory(2, Category.RESTAURANT));
            TablePrinter.print(customerService.getAllCustomerCouponsByCategory(1, Categories.ELECTRICITY));
        }catch (NotFoundException err){
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCustomerCouponByMaxPrice(){
        try {
            printTest("successful get all customer's coupons by max price");
//            System.out.println(customerFacade.getAllCustomerCouponsByMaxPrice(2, 22));
            TablePrinter.print(customerService.getAllCustomerCouponsByMacPrice(1, 22));
        }catch (NotFoundException err){
            System.out.println(err.getMessage());
        }
    }

    public void testGetCustomerDetail(){
        try {
            printTest("successful get customer detail");
//            System.out.println(customerFacade.getCustomerDetail(10));
            TablePrinter.print(customerService.getCustomerDetails(1));
        }catch (NotFoundException err){
            System.out.println(err.getMessage());
        }
    }
}
