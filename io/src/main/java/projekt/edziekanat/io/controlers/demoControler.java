package projekt.edziekanat.io.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class demoControler {

    @GetMapping("/hjg")
    String HelloWord()
    {
        return "Hello";
    }
}
