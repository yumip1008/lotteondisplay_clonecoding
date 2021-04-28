<template>
  <app-header 
  :accountList="accountList"
  :buttonList="buttonList"
  />
</template>

<script>
import {AppHeader} from '@/components'
export default {
  components: { AppHeader },
    data : function(){
    return {
      accountList : [
        {value : '로그인', to : '/'},
        {value : '회원가입', to : '/'},
        {value : '주문배송', to : '/'},
        {value : '고객센터', to : '/'}
      ],
      buttonList :[
        {value : '챗봇', icon : 'far fa-comment-dots fa-2x', to : '/cart'},
        {value : '나의 찜', icon : 'far fa-user fa-2x', to : '/cart'},
        {value : '마이롯데', icon : 'far fa-heart fa-2x', to : '/cart'},
        {value : '장바구니', icon : 'fas fa-shopping-cart fa-2x', to : '/cart'}
      ]
    }
  }

}
</script>

<style>

</style>