package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    // url 지정
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 연결시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + ", message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect = " + url);
    }

    @PostConstruct
    public void init(){
        connect();
        call("초기화 연결 메시지");
        System.out.println("NetworkClient.init");
    }

    @PreDestroy
    public void close() {
        disconnect();
    }


// -------------- 빈 등록 초기화, 소멸메서드 지정---------------------
//    public void init(){
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    public void close() {
//        disconnect();
//    }


// -------------------인터페이스 활용----------------------------
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메시지");
//    }
}





