<template>
    <v-container>
      <v-row>
       <v-col v-for="(categoryOption, index) in categoryOptions" :key="index">
         <button class="imgBtn" @click="emitCategoryOption(categoryOption['key'])">
            <v-img
            :src="categoryOption['img']"
            max-width="72"
            max-height="72"
            />
          <span class="text">{{categoryOption['value']}}</span>
         </button>
       </v-col>
      </v-row>
    </v-container>
</template>

<script>
export default {
  props : {
    categoryOptions : Array,
  },
  methods : {
    emitCategoryOption(categoryOption){
      this.$emit('getCategoryOption', categoryOption);
    }
  }
}
</script>

<style>
.display-menu-option{
  display: block;
}
.text {
  font-size : 13px;
}
.imgBtn{
  margin: 0;
  background-color: transparent;
}

.imgBtn:focus {
    border: none;
    outline:none;
    font-weight: bold;
}
</style>