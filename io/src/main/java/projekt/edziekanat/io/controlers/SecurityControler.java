package projekt.edziekanat.io.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityControler {

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "login";
    }
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
