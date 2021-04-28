<template>
  <div>
    <div class="title-area">
      장바구니
    </div>
    <div class="tab-area">
      <v-tabs fixed-tabs>
        <v-tab>일반 ({{totalCount}})</v-tab>
        <v-tab>장보기 ()</v-tab>
        <v-tab>정기배송 ()</v-tab>
      </v-tabs>
    </div>
  </div>
</template>

<script>
export default {
  props : {
    totalCount : Number,
  }
}
</script>

<style>
.title-area {
    padding-top: 14px;
    margin-bottom: 30px;
    font-size: 36px;
    line-height: 52px;
    letter-spacing: -1.1px;
    text-align: center;
    color: #333;

}
</style>