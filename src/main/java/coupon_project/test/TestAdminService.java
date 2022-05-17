package coupon_project.test;

import coupon_project.entities.Company;
import coupon_project.entities.Customer;
import coupon_project.exception.InvalidException;
import coupon_project.exception.LoginException;
import coupon_project.exception.NotFoundException;
import coupon_project.login.ClientType;
import coupon_project.login.LoginManager;
import coupon_project.services.AdminService;
import coupon_project.utils.ArtUtils;
import coupon_project.utils.TablePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import static coupon_project.test.Constants.*;

@Service
public class TestAdminService {

    @Autowired
    ApplicationContext ctx;
    private AdminService adminService;


    public void testAll() {
        System.out.println(ArtUtils.startTestingAdminFacade);
        testLogin();
        testAddCompany();
        testUpdateCompany();
        testDeleteCompany();
        testGetAllCompanies();
        testGetOneCompany();
        testAddCustomer();
        testUpdateCustomer();
        testDeleteCustomer();
        testGetAllCustomers();
        testGetOneCustomer();
    }


    public void testLogin() {
        System.out.println("========== test admin login ============");
        System.out.print("test login false: ");
        try {
            adminService = (AdminService) LoginManager
                    .login("admin@admi.com", "admin", ClientType.ADMINISTRATOR,ctx);
            System.out.print("FAilED");
        } catch (LoginException e) {
            System.out.println("(" + e.getMessage() + ")");
        }

        System.out.print("test login true: ");
        try {
            adminService = (AdminService) LoginManager
                    .login("admin@admin.com", "admin", ClientType.ADMINISTRATOR,ctx);
            System.out.println("successes");
        } catch (LoginException e) {
            System.out.println("FAILED " + "(" + e.getMessage() + ")");
        }


    }

    public void testAddCompany() {
        try {
            System.out.println("==========test successful add company==========");
            adminService.addCompany(COMPANY1);
            adminService.addCompany(COMPANY2);
            adminService.addCompany(COMPANY3);
            adminService.addCompany(COMPANY4);
            System.out.println("companies added successfully");
            System.out.println("==========test failed add company==========");
            adminService.addCompany(COMPANY1);
        } catch (InvalidException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testUpdateCompany() {
        try {
            System.out.println("==========test successful update company==========");
            adminService.updateCompany(new Company(COMPANY3.getId(), COMPANY3.getName(), "shekem@gmail.com", "7496"));
            System.out.println("Successful");
            System.out.println("==========test failed update company==========");
            adminService.updateCompany(new Company(8, "wrong", "vcx@gmail.com", "456789"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            adminService.updateCompany(new Company(COMPANY1.getId(), COMPANY1.getName(), COMPANY2.getEmail(), "00000"));
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void testDeleteCompany() {
        try {
            System.out.println("==========test successful delete company==========");
            adminService.deleteCompany(4);
            System.out.println("company deleted");
            System.out.println("==========test failed delete company==========");
            adminService.deleteCompany(10);
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCompanies() {
        printTest("get all companies");
//            admin.getAllCompanies().forEach(System.out::println);
        TablePrinter.print(adminService.getAllCompanies());
    }

    public void testGetOneCompany() {
        try {
            System.out.println("==========test successful get one company==========");
//            System.out.println(admin.getOneCompany(3));
            TablePrinter.print(adminService.getOneCompany(1));
            System.out.println("==========test failed get one company==========");
            TablePrinter.print(adminService.getOneCompany(10));
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testAddCustomer() {
        try {
            printTest("successful add customer");
            adminService.addCustomer(Constants.CUSTOMER1);
            adminService.addCustomer(Constants.CUSTOMER2);
            adminService.addCustomer(Constants.CUSTOMER3);
            System.out.println("successful");
            System.out.println("==========test failed add customer company==========");
            adminService.addCustomer(CUSTOMER1);
        } catch (InvalidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void testUpdateCustomer() {
        try {
            System.out.println("==========test successful update customer company==========");
            adminService.updateCustomer(new Customer(CUSTOMER2.getId(), "Elon", "Mask", "elonmask@gmail.com", "456000"));
            System.out.println(SU);
            System.out.println("==========test failed update customer company==========");
            adminService.updateCustomer(new Customer(10, "asdf", "ret", "sdf@asdf.com", "130"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            adminService.updateCustomer(new Customer(1, "roho", "loho", "elonmask@gmail.com", "123789"));
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void testDeleteCustomer() {
        try {
            System.out.println("==========test successful delete customer company==========");
            adminService.deleteCustomer(3);
            System.out.println(SU);
            System.out.println("==========test failed delete customer company==========");
            adminService.deleteCustomer(10);
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }

    public void testGetAllCustomers() {
        printTest("get all customers");
//            admin.getAllCustomer().forEach(System.out::println);
        TablePrinter.print(adminService.getAllCustomers());
    }

    public void testGetOneCustomer() {
        try {
            System.out.println("==========test successful get one customer==========");
//            System.out.println(admin.getOneCustomer(5));
            TablePrinter.print(adminService.getOneCustomer(1));
            System.out.println("==========test failed get one company==========");
            TablePrinter.print(adminService.getOneCustomer(10));
        } catch (NotFoundException err) {
            System.out.println(err.getMessage());
        }
    }
}


