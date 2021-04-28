<template>
<div class="header-wrapper">
  <div class="top-menu-content">
    <div class="account-area">
      <router-link 
        v-for="(account, index) in accountList" :key="index"
        class="router-link-with-text"
        :to="account['to']">
      {{account['value']}}
      </router-link>
    </div>
  </div>

  <div class="center-menu-content">
     <div class="log-area">
       <router-link to="/">
       <img 
          src="https://static.lotteon.com/p/common/foCommon/assets/img/logo_lotte-on@2x.png"
          width="185"
          height="38">
       </router-link>
     </div>  

      <div class="search-area">
        <input class="search-input" type="search">
        <i class="fas fa-search fa-lg"></i>
      </div>

      <div class="button-area">
        <router-link class="router-link-with-btn-icon" 
            v-for="(button, index) in buttonList" :key="index"
                :to="button['to']">
          <i :class="button['icon']"></i>
          <span class="icon-bottom-text">{{button['value']}}</span>
        </router-link>
      </div>
  </div>
  <div class="bottom-menu-content">
    <router-link to="/product/display" class="router-link-with-btn-icon">뷰티넘버원</router-link>
  </div>  
</div>
</template>

<script>
export default {
  props : {
    accountList : Array,
    buttonList : Array
  }
}
</script>

<style>
.header-wrapper {
  display : grid;
  grid-template-areas: 
  "top-menu-content"
  "center-menu-content"
  "bottom-menu-content";
}

.account-area {
  padding-top: 8px;
  text-align: right;
}

.router-link-with-text{
  text-decoration: none;
  font-size : 14px;;
  margin-right : 10px;
  color: #999
}

.center-menu-content {
  display : grid;
  grid-template-columns : 1fr 2fr 2fr;
  padding : 15px 0px 15px;
}

.log-area {
  display : inline;
  padding-top : 5px;
}

.search-area {
  width: 450px;
  height: 48px;
  padding: 10px 20px 10px 20px;
  border-radius: 25px;
  border-style: solid;
  border-width: 2px;
  border-color: inherit;
  color :#ef2a23
}

.search-input {
  width : 350px;
  margin-right : 25px;
}

.search-input:focus{
  border: none;
  outline:none;
  font-weight: bold;
}

.button-area {
  font-size : 14px;
  text-align: right;
}

.router-link-with-btn-icon {
  margin: 10px;
  display : inline-block;
  text-align: center;
  text-decoration: none;
  color : black;
}

.icon-bottom-text{
  display : block;
  margin-top : 5px;
  text-decoration: none;
}



</style>