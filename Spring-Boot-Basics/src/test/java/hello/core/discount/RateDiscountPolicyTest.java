package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP에게 10% 할인이 적용되도록 하기")
    void vip_o() {
        //given
        Member member = new Member(1L, "vipMember", Grade.VIP);

        //when
        int discount = discountPolicy.discountPrice(member, 10000);

        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

        @Test
        @DisplayName("VIP가 아니면 10% 할인이 적용되지 않게 하기")
        void vip_x(){
            //given
            Member member = new Member(2L, "vipMember", Grade.Basic);

            //when
            int discount = discountPolicy.discountPrice(member, 10000);

            //then
            Assertions.assertThat(discount).isEqualTo(0);

    }

}