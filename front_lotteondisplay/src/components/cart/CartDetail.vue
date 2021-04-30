<template>
  <div>
    <div class="item-control-area">
      <div class="item-all-check">
        <input type="checkbox" class="item-checkbox" 
                v-model="isAllSelected"
                @change="emitSelectedAllProduct">
        <span class="item-all-check-text">전체선택</span>
      </div>

      <div class="delete-btn-area">
        <button class="delete-btn">품절상품삭제</button>
        <button class="delete-btn" @click="emitDeleteClicked">선택삭제</button>
      </div>
    </div>

    <div class="cartlist-area">
      <div class="cartlist-by-Irtr" v-for="(value, key, index) in cartMap" :key="index">
        <div class="cart-header">
          <span class="cart-header-text">{{key}}</span>
        </div>

        <div class="cart-content-area" v-for="(cartDetail, index) in value" :key="index">
          <div class="cart-content">
            <div class="cart-content-checkbox">
              <input  type="checkbox"
                      class="item-checkbox"
                      name="single-item" 
                      :value="cartDetail.sitmNo"
                      :id="cartDetail.cartSn"
                      v-model="selectedProducts"
                      @change="emitSelectedProduct"
              >
            </div>
            
            <div class="cart-content-img-area">
              <img :src="cartDetail.imgJsn[0].origImgFileNm" class="cart-content-img">
            </div>

            <div class="cart-content-info">
              <div class="cart-content-top">
              <span class="cart-content-brand">
                {{cartDetail.brdNm}}
              </span>
              <span class="cart-content-title">
                {{cartDetail.spdNm}}
              </span>
              </div>
              <span class="cart-content-delivery">
                {{cartDetail.estmtDlvTxt}}
              </span>
            </div>
          </div>

          <div class="cart-quantity">
            <div class="quantity-box">
            <button class="quantity-minus-btn" 
                    @click="emitMinusOdQty(cartDetail)" 
                    :disabled="isLessThanMinOdQty(cartDetail.odQty, cartDetail.itmByMinPurJsn.itmByMinPurQty)" ></button>
            <span class="quantity">{{cartDetail.odQty}}</span>
            <button 
                    class="quantity-plus-btn" 
                    @click="emitPlustOdQty(cartDetail)"
                    :disabled="isGreaterThanMaxOdQty(cartDetail.odQty, cartDetail.itmByMaxPurPsbJsn.maxPurQty)"></button>
            </div>
          </div>

          <div class="cart-price">
            <span class="cart-price-text"><strong>{{getConvertedPrice(getTotalPrice(cartDetail))}}</strong>원</span>            
          </div>

          <div class="cart-delete">
            <button class="cart-delete-btn" @click="emitDelete(cartDetail)"></button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {ObjectMapperUtil} from '@/util'
import {convertedPrice} from '@/mixins'
import _ from 'lodash'
export default {
  mixins : [convertedPrice],
  props : {
    cartMap : Object,
    clickedProducts : Array,
  },
  data : function(){
    return {
      selectedProducts : this.clickedProducts,
      originSelectedProducts : this.clickedProducts,
      isAllSelected : true,
    }
  },
  methods :{
    emitSelectedProduct(){
      this.$emit('getClickProduct', this.selectedProducts);
    },
    emitSelectedAllProduct(){
      if(this.isAllSelected){
        this.selectedProducts = this.originSelectedProducts;
      }else{
        this.selectedProducts = [];
      }
      this.$emit('getClickProduct', this.selectedProducts);
    },
    emitMinusOdQty : _.debounce(function(cartDetail) {
      const cartDto = ObjectMapperUtil.convertCartDto(cartDetail);
      cartDto.odQty--;
      this.$emit('updateCart', cartDto, cartDetail);
    }, 500),
    emitPlustOdQty : _.debounce(function(cartDetail){
      const cartDto = ObjectMapperUtil.convertCartDto(cartDetail);
      cartDto.odQty++;
      this.$emit('updateCart', cartDto, cartDetail);
    }, 500),
    emitDelete : _.debounce(function(cartDetail){
      this.$emit('deleteCart', cartDetail);
    }, 500),
    emitDeleteClicked : _.debounce(function(){
      let cartSnArr = [];
      document.querySelectorAll('input[name=single-item]:checked').forEach(element => cartSnArr.push(element.id));
      this.$emit('deleteClickedCart', cartSnArr);
    }, 500),
    isLessThanMinOdQty(odQty, minOdQty){
      return odQty <= minOdQty;
    },
    isGreaterThanMaxOdQty(odQty,maxOdQty){
      return odQty >= maxOdQty;
    },
    getTotalPrice(cartDetail){
      return cartDetail.slPrc * cartDetail.odQty;
    },
    
  },
}
</script>

<style>
.item-control-area {
  display: block;
}
.item-all-check {
    float: left;  
}
.item-checkbox {
  margin-right: 10px;
}
.item-all-check-text{
  color: #333;
  font-size: 14px;
}
.delete-btn-area{
  float :right;
}
.delete-btn{
    height: 32px;
    padding: 0 12px;
    margin-left: 8px;
    font-size: 13px;
    line-height: 32px;
    letter-spacing: -.2px;
    text-align: center;
    color: #333;
    border-radius: 6px;
    border: 1px solid #ddd;
}
.cartlist-by-Irtr{
  margin-top : 50px;
  border-radius: 10px;
  border: 1px solid #ddd;
}
.cart-by-tr {
  margin-bottom : 10px;
}
.cart-header{
  height: 53px;
  padding: 0 20px 0 19px;
  border-bottom: 1px solid #eee;
}
.cart-header-text{
  display: inline-block;
  font-size: 18px;
  font-weight: 700;
  line-height: 53px;
  letter-spacing: -.5px;
  color: #333;
  vertical-align: middle;
}
.cart-content-area {
  display: grid;
  grid-template-columns: 9fr 2fr 2fr 1fr;
  padding : 24px 4px 24px 4px;
  border-bottom: 1px solid #eee;
}
.cart-content {
  position : relative;
  padding-left : 28px;
  display: flex;
}
.cart-content-checkbox{
  margin-right : 5px;
}
.cart-content-img-area{
  display: inline;  
}
.cart-content-img{
  width: 80px;
  min-width: 80px;
  height: 80px;
  margin-right: 12px;
  border-radius: 6px;
}
.cart-content-info{
  display: inline;
  margin-bottom: 4px;
  letter-spacing: normal;
  color: #333;
}
.cart-content-top {
 margin-bottom: 5px;
  line-height: 1.47;
  letter-spacing: -.3px;
  color: #333
}
.cart-content-brand{
  font-weight: bold;
}
.cart-content-delivery{
  display: block;
  margin-top: 6px;
  font-size: 14px;
  line-height: 1.57;
  letter-spacing: -.3px;
  color: #4185ed;
}
.cart-quantity{
  position : relative;
   display: flex;
  justify-content: center;
  min-width: 140px;
  padding-left : 10px;
  padding-right : 10px;
  border-left: 1px solid #ddd;
  border-right: 1px solid #ddd;
}
.quantity-box{
  width: 110px;
  height: 32px;
  border-radius: 6px;
  border: 1px solid #ddd;
  background-color: #fff;
  display: flex;
}
.quantity-minus-btn{
  width: 30px;
  height: 30px;
  background: url(//static.lotteon.com/p/order/assets/img/btn_minus.svg) no-repeat 50%;
}
.quantity-minus-btn:disabled {
 opacity: .25; 
}
.quantity-plus-btn{
   width: 30px;
  height: 30px;
  background: url(//static.lotteon.com/p/order/assets/img/btn_plus.svg) no-repeat 50%;
}
.quantity-plus-btn:disabled{
  opacity: .25;
}
.quantity {
  border-left: 1px solid #ddd;
  border-right: 1px solid #ddd;
  flex: 1;
  line-height: 30px;
  text-align: center;
  width: 42px;
  font-size: 13px;
}
.cart-price{
  position : relative;
  text-align: center;
}
.cart-price-text{
    display: inline-block;
    font-size: 14px;
    line-height: 26px;
    letter-spacing: -.3px;
    color: #333;
}
.cart-price-text > strong{
   font-size: 18px;
    letter-spacing: normal;
    color: #333;
    vertical-align: top;
}
.cart-delete{
  padding-right : 10px;
}
.cart-delete-btn{
  float : right;
  width: 22px;
  min-width: 22px;
  height: 22px;
  background-repeat: no-repeat;
  background-position: 50%;
  background-size: 100% 100%; 
  background-image: url(//static.lotteon.com/p/order/assets/img/icon_delete-item_new.svg);
}
   
</style>