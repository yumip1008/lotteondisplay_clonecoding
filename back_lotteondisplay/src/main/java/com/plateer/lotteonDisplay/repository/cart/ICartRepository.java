package com.plateer.lotteonDisplay.repository.cart;


import com.plateer.lotteonDisplay.model.cart.Cart;
import com.plateer.lotteonDisplay.dto.cart.CartDto;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public interface ICartRepository extends ReactiveCrudRepository<Cart, String> {

    //회원 번호로 장바구니 찾기
    @Query("select * from om_cart where mb_no = :mbNo order by reg_dttm desc")
    Flux<Cart> findAllByMbNoOrderbyRegDttm(String mbNo);

    //회원번호, 상품번호, 단품번호로 동일 상품이 장바구니에 담겼는 지 확인
    @Query("select exists (select 1 from om_cart where mb_no = :#{#dto.mbNo} and spd_no = :#{#dto.spdNo} and sitm_no = :#{#dto.sitmNo})")
    Mono<Boolean> existsCart(CartDto dto);
    
    //카트번호로 장바구니 삭제
    @Query("delete from om_cart where cart_sn in (:cartSns)")
    Mono<Void> deleteAllByIds(List<String> cartSns);
}
