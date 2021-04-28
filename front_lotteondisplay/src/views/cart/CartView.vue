<template>
  <div class="cart-wrapper">
      <cart-menu class="cart-menu"
      :totalCount="totalCount"/>

      <cart-detail class="cart-detail"
      :cartMap="cartMap"
      :clickedProducts="clickedProducts"
      @getClickProduct="setClickProduct"
      @updateCart="updateCart"
      @deleteCart="deleteCart"/>

      <total-price class="cart-payment" 
      :totalPrice="totalPrice"
      />
  </div>
</template>

<script>
import {CartMenu, CartDetail, TotalPrice} from '@/components'
import {CartApi} from '@/api'
import {ProductPriceInfo} from '@/model'

export default {
  components: { 
    CartMenu, 
    CartDetail, 
    TotalPrice 
  },
  created : async function(){
    await this.setUpCartList();
    this.initClickedProducts();
    this.setProductPriceMap();
  },
  data : function(){
    return {
      cartMap : new Object(),
      totalCount : null,
      totalPrice : null,
      productPriceMap : new Object(),
      clickedProducts : new Array(),
    }
  },
  methods : {
    setCartList(response){
      this.cartMap = response;
    },

    async setUpCartList(){
      const cartMap = await this.getCartList();
      this.setCartList(cartMap);
    },

    async getCartList(){
      const mbNo = 1;
      const cartMap = await CartApi.getAllCartsbymbNo(mbNo);
      return cartMap;
    },

    setTotalCartCount(){
      this.totalCount = this.clickedProducts.length;
    },

    setProductPriceMap(){
      Object.keys(this.cartMap).forEach(key => {
        this.cartMap[key].forEach(product => {
          const productPriceInfo = new ProductPriceInfo(product.slPrc, product.odQty);
          this.productPriceMap[product.sitmNo] = productPriceInfo;
        })
      })
    },

    initClickedProducts(){
      Object.keys(this.cartMap).forEach(key => {
        this.cartMap[key].forEach(product => {
          this.clickedProducts.push(product.sitmNo);
        })
      })
    },

    setTotalPrice(){
      let computedPrice = 0;
      const clickedProductsLength = this.clickedProducts.length;
      
      if(clickedProductsLength !== 0){
        this.clickedProducts.forEach(sitmNo => {
          const price = this.productPriceMap[sitmNo].getTotalPrice();
          computedPrice += price;
        })
      }

      this.totalPrice = computedPrice;
    },

    setClickProduct(selectedProducts){
      this.clickedProducts = selectedProducts;
    },

    updateCart(cartDto, cartDetail){
      CartApi.update(cartDto).then(() => {
        cartDetail.odQty = cartDto.odQty;
        //productPriceMap에서 수량정보 update
        this.$set(this.productPriceMap[cartDetail.sitmNo], 'quantity', cartDetail.odQty);
        this.setTotalPrice();
      }).catch(error => console.log(error));
    },

    deleteCart(cartDetail){
      CartApi.delete(cartDetail.cartSn).then(() => {
        const lrtrNo = cartDetail.lrtrNo;
        const lengthOfCarts = this.cartMap[lrtrNo].length;
        if(lengthOfCarts > 1){
          //해당 그룹의 장바구니 개수가 1보다 크면 해당 장바구니만 삭제
           this.$delete(this.cartMap[lrtrNo], this.cartMap[lrtrNo].indexOf(cartDetail));
        }else{
          //해당 그룹의 장바구니 개수가 1이면, 해당 그룹을 cartMap에서 삭제
          this.$delete(this.cartMap, lrtrNo);
        }
        //삭제된 장바구니 clickedProducts에서 삭제 (-> watch에 의해 전체 가격, 상품 개수 정보 update 됨)
        this.$delete(this.clickedProducts, this.clickedProducts.indexOf(cartDetail.sitmNo));       
        alert("삭제가 완료되었습니다!");
      }).catch(error => console.log(error));
    }
  },
  watch : {
    clickedProducts : function(){
      this.setTotalPrice();
      this.setTotalCartCount();
    },
  }
}
</script>

<style>
.cart-wrapper {
  display : grid;
  grid-template-columns: repeat(8,2f);
  grid-template-areas: 
  "cm cm cm cm cm cm cm cm"
  "cd cd cd cd cd cd cd cp";
  column-gap : 60px;
}

.cart-menu {
  grid-area: cm;
  margin-bottom : 10px;
}

.cart-detail {
  grid-area : cd;
}

.cart-payment {
  grid-area : cp;
}
</style>