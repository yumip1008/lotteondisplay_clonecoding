package com.plateer.lotteonDisplay.util;

import com.plateer.lotteonDisplay.model.cart.Cart;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CartBeforeConvertCallback implements BeforeConvertCallback<Cart>
{
    private static AtomicInteger cartSequence = new AtomicInteger();

    @Override
    public Publisher<Cart> onBeforeConvert(Cart entity, SqlIdentifier table) {
        if(entity.getCartSn() == null) {
            return Mono.just(setUpCartSn(entity));
        }
        return Mono.just(entity);
    }

    private Cart setUpCartSn(Cart cart){
        cart.setCartSn(getCurrentTime() + getCartSequence());
        return cart;
    }

    private String getCartSequence(){
        int seqInt = cartSequence.incrementAndGet();
        return String.format("%04d",seqInt);
    }

    private String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }
}
