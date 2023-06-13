package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

    @Test
    public void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA",10000);

        //ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService1.order("userB",30000);


        //ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();

        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력 ->  StatefulService 클래스 고쳐서 적용
        System.out.println("price = " + userAPrice);
        System.out.println("price = " + userBPrice);

//        System.out.println(statefulService1);
//        System.out.println(statefulService2);
        assertThat(statefulService1).isSameAs(statefulService2);

    }



    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }


}
