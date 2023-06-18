package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;



public class SingletonWithPrototypeTest1 {

    @Test
    void singletonClientUsePrototype() {

    AnnotationConfigApplicationContext ac
            = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        System.out.println("count1 = " + count1);
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        System.out.println("count2 = " + count2);
        Assertions.assertThat(count2).isEqualTo(2);

        ac.close();
    }


    @Scope("singleton")
    static class ClientBean {

        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
                this.prototypeBean = prototypeBean;
            }

            public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }


    @Scope("prototype")
    static class PrototypeBean {

                private int count = 0;

                public void addCount() {
                    count++;
                }

                public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }


    }
}
