package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelAndView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }
}
