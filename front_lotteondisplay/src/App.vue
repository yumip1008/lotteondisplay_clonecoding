<template>
  <div class="wrapper">
    <div class="spinner">
      <moon-loader :loading="loading" :color="color" :size="size"></moon-loader >
    </div>

    <div class="header">
    <app-header-view/>
    </div>

    <div class="content">
    <app-content-view />
    </div>

    <div class="footer">
    </div>
  </div>
</template>

<script>
import {AppHeaderView, AppContentView} from '@/views'
import {mapGetters} from 'vuex'
import MoonLoader from 'vue-spinner/src/MoonLoader.vue'

export default {
  name: 'App',
  components: {
    AppHeaderView,
    AppContentView,
    MoonLoader
  },
  data : function() {
    return {
        color: '#1678C2',
        size : '80px'
      }
  },
  computed : {
    ...mapGetters({loading : 'getLoading'})
  }
}
</script>

<style>
.wrapper {
  display : grid;
  grid-template-columns: repeat(6, 1fr);
  grid-template-areas : 
  ". header header header header ."
  ". content content content content ."
  "footer footer footer footer footer footer"
}

.header {
  grid-area: header;
}

.content {
  grid-area: content;
}

.footer {
  grid-area: footer;
}

.spinner {
  position: absolute;
  top : 50%;
  left: 50%;
  margin: -50px 0 0 -50px;
  z-index : 3;
}
</style>
