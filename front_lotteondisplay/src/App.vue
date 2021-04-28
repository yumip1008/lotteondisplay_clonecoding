<template>
  <div class="wrapper">
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


export default {
  name: 'App',
  components: {
    AppHeaderView,
    AppContentView
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
</style>
