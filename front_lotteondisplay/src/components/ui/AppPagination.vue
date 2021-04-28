<template>
 <div class="text-center">
    <v-pagination
      v-model="page"
      :length=pageLength
      :total-visible="9"
    ></v-pagination>
  </div>
</template>

<script>
export default {
  props : {
    pageLength : Number,
  },
  data : function(){
    return {
      page : 1,
    }
  },
  watch : {
    page : function(newVal){
      this.$emit('getCurrentPage', newVal)
    }
  }
}
</script>

<style>

</style>