package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    // provider 사용
    // private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    private final MyLogger myLogger;
    public void log(String id) {

    // provider 사용
    // MyLogger myLogger = myLoggerObjectProvider.getObject();
    myLogger.log("service id = " + id);
    }
}
