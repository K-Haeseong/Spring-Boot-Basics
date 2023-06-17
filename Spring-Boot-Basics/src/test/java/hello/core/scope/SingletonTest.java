package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind(){

        AnnotationConfigApplicationContext
                ac = new AnnotationConfigApplicationContext(Singleton.class);

        Singleton singletonBean1 = ac.getBean(Singleton.class);
        Singleton singletonBean2 = ac.getBean(Singleton.class);
        System.out.println("bean1 = " + singletonBean1);
        System.out.println("bean1 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close(); //종료
    }



    @Scope("singleton")
    static class Singleton{

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }

    }
}
