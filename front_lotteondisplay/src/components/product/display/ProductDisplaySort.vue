<template>
<div class="sort-area">
    <div class="left-set">총 <strong>{{productLength}}</strong> 개</div>
    <div class="right-set">
        <button
        v-for="(sortOption,index) in sortOptions" 
        :key="index"
        @click="emitSortOption(sortOption['key'])"
        class="sort-option-btn">
            {{sortOption['value']}}
        </button>

        <select @change="emitCountOption" class="sort-select-count">
            <option 
                v-for="(option,index) in countOptions" 
                :key="index"
                :value="option['key']">
                {{option['value']}}
            </option>
        </select>
       
    </div>
    
</div>
</template>

<script>
export default {
    props : {
        productLength : String,
        sortOptions : Array,
        countOptions : Array
    },
    methods : {
       emitSortOption(option){
           console.log(option);
            this.$emit('getSortOption', option);
        },
        
        emitCountOption(event){
            this.$emit('getCountOption', event.target.value);
        }
    }
}
</script>

<style>
.sort-area{
    font-size: 14px;
    height: 40px;
    padding: 12px 11px;
    margin: 18px 0 10px;
}

.left-set{
    float : left;
}

.right-set{
    float: right;
}

.sort-option-btn{
    margin-right: 10px;
}

.sort-option-btn:focus {
    border: none;
    outline:none;
    font-weight: bold;
}

.sort-select-count {
    border: 1px solid #ddd;
    border-radius: 6px;
}

</style>