package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

     private final MyLogger myLogger;
     private final LogDemoService logDemoService;


    // <Provider 사용방법>
    // private final LogDemoService myLoggerObjectProvider;
    // private final LogDemoService logDemoService;


    // @Autowired
    // private final LogDemoService logDemoService;

    // 위 코드와 같음
    // @Autowired
    // public LogDemoController(MyLogger myLogger) {
    //     this.myLogger = myLogger;
    //  }


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        // MyLogger myLogger = myLoggerObjectProvider.getObject(); // provider 사용
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.log("testId");

        return "OK";
    }

}
