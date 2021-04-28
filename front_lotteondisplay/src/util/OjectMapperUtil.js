import  {Cart, CartDto}  from '@/model';

class ObjectMapperUtil{
    
    convertCart(object){
        const cart = new Cart();
        Object.keys(cart).forEach(key => {
            if(object[key]){
                cart[key] = object[key];
            }
        })
        return cart;
    }

    convertCartDto(object){
        const cartDto = new CartDto();
        Object.keys(cartDto).forEach(key => {
            if(object[key]){
                cartDto[key] = object[key];
            }
        })
        return cartDto;
    }
}

const instance = new ObjectMapperUtil();

export default instance


