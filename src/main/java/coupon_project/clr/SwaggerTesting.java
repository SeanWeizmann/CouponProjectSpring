package coupon_project.clr;

import coupon_project.config.MyRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class SwaggerTesting implements CommandLineRunner {

    private final MyRestTemplate myRestTemplate;

    @Override
    public void run(String... args) throws Exception {

    }
}
