<template>
  <div class="total-price-wrapper">
    <div class="total-price-title">
      결제예정금액
    </div>

    <div class="price-list">
       총 {{getConvertedPrice(totalPrice)}} 원
    </div>
  </div>
</template>

<script>
import {convertedPrice} from '@/mixins'

export default {
  mixins : [convertedPrice],
  props : {
    totalPrice : Number
  },
}
</script>

<style>
.total-price-wrapper {
  text-align: center;
  width : 298px;
  background: #fff;
  box-sizing: border-box;
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 24px 20px 20px 20px
}

.total-price-title{
  font-size: 20px;
  line-height: 1.4;
  letter-spacing: -.06em;
  margin-bottom: 28px;
}

.price-list {
  border-bottom: 1px solid #eee;
  padding-bottom: 18px;
}


</style>