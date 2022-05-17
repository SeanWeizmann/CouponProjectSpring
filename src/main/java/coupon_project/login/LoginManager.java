package coupon_project.login;

import coupon_project.exception.InvalidException;
import coupon_project.exception.LoginException;
import coupon_project.services.AdminService;
import coupon_project.services.ClientService;
import coupon_project.services.CompanyService;
import coupon_project.services.CustomerService;
import coupon_project.utils.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataHandler;

@Service
public class LoginManager {


    public static ClientService login(String email, String password, ClientType clientType, ApplicationContext ctx) throws LoginException {
        switch (clientType) {
            case ADMINISTRATOR:
                ClientService adminService = ctx.getBean(AdminService.class);
                if (!adminService.login(email, password)) {
                    throw new LoginException("Invalid user name or password");
                }

                System.out.println(DataUtil.getLocalDateTime() + " -" + email + "- was logged ");
                return adminService;

            case COMPANY:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (!companyService.login(email, password)) {
                    throw new LoginException("Invalid user name or password");
                }
                System.out.println(DataUtil.getLocalDateTime() + " -" + email + "- was logged ");
                return companyService;

            case CUSTOMER:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (!customerService.login(email, password)) {
                    throw new LoginException("Invalid user name or password");
                }
                System.out.println(DataUtil.getLocalDateTime() + " -" + email + "- was logged");
                return customerService;
        }
        throw new LoginException("please try again");
    }
}
