<template>
  <div>
      <product-display-menu
      @getCategoryOption="setUpProductListByCategory"
      :categoryOptions="categoryOptions" />
      
      <product-display-sort
      @getSortOption="setUpProductListBySortOption"
      @getCountOption="setUpProductListByCount"
      :productLength="productLength"
      :sortOptions="sortOptions"
      :countOptions="countOptions" />
      
      <product-display-list
      @getClickedProduct="createCart"
      :dataList="dataList" />
    
      <app-pagination 
      @getCurrentPage="setUpProductListByPage"
      :pageLength="pageLength"  />
  </div>
</template>

<script>
import {ProductDisplayMenu, ProductDisplaySort, ProductDisplayList,  AppPagination} from '@/components'
import {ProductApi, CartApi} from '@/api'
import {CatePdList, ProductDisplayOption} from '@/model'
import {ObjectMapperUtil} from '@/util'

export default {
  components: { 
      ProductDisplayMenu,
      ProductDisplaySort,
      ProductDisplayList,
      AppPagination 
    },
    created : function(){
        this.setUpProductList();
    },
    data : function(){
        return {
            productDisplayOption : new ProductDisplayOption(),
            catePdList : new CatePdList(),
            dataList : [],
            productLength : null,
            categoryOptions : [
                {key : "22276", value : "스킨케어", img : "https://contents.lotteon.com/display/dshop/22276/PF6293D4093EFDF4F3F6FB6550350FE1E41EE8C9EC1DED58A3C784DF16490F48F/file"},
                {key : "22279", value : "베이스메이크업", img : "https://contents.lotteon.com/display/dshop/22279/PDEC5B6822486CB8A5A5D29194A3515F89F1BD71CF38DCBC24E9A19FE7F14AF89/file"},
                {key : "22280", value: "색조메이크업", img : "https://contents.lotteon.com/display/dshop/22280/P41A356C8680A3D4CB1825B5AC1AD82BFF37203BFBE3F9935A0212E899211851E/file"},
                {key : "22282", value : "선케어", img : "https://contents.lotteon.com/display/dshop/22282/PF99455EE030503AAEC3D0794F5BDF4A4243E94AA36B339521AE7D290299449E5/file"},
                {key : "22284", value : "바디케어", img : "https://contents.lotteon.com/display/dshop/22284/P702F709F33906D4249E62CAECCB7717B0A5EE20E1AC60EFBD826E434A4D4CF4D/file"},
                {key : "22286", value : "헤어케어", img : "https://contents.lotteon.com/display/dshop/22286/P405530B15223D89A4C037BA527822C4AB842D69AE26A86E5C70C1BE71C7C7AA1/file"},
                {key : "22296", value : "클렌징", img : "https://contents.lotteon.com/display/dshop/22296/P3F3DC68024FC027AC0FF7629D4095D0B581155CEC0C1BDB46AE4E8B5270E4BF9/file"},
                {key : "22289", value : "향수", img : "https://contents.lotteon.com/display/dshop/22289/P20495AE75B7780F677A9CB7EE0532441543425EC91D4018CFBB04049BF5C2A89/file"},
            ],
            sortOptions : [
                {key : '01', value : '핀매량 순'},
                {key : '02', value : '최근 등록순'},
                {key : '03', value : '낮은 가격순'},
                {key : '04', value : '높은 가격순'},
            ],
            countOptions : [
                {key : '60', value :'60개씩 보기'},
                {key : '120', value : '120개씩 보기'}
            ],
            pageLength : null,
        }
    },
    methods : {
        async setUpProductList(){
            const productList  = await this.getProductList();
            this.setProdutList(productList);
        },
        setProdutList(response){
           this.catePdList = response;
           this.dataList = this.catePdList.dataList;
           this.productLength = this.changeLengthFormat(response.totalCount);
           this.pageLength = Math.round(parseInt(this.productLength) / parseInt(this.productDisplayOption.rowsPerPage));
        },
        async getProductList(){
            const productList = await ProductApi.getAll(this.productDisplayOption);
            return productList;
        },
        setUpProductListByCategory(categoryOption){
            this.productDisplayOption.dshopNo = categoryOption;
            this.setUpProductList();
        },
        setUpProductListBySortOption(sortOption){
            this.productDisplayOption.pdSortCd = sortOption;
            this.setUpProductList();
        },
        setUpProductListByCount(count){
            this.productDisplayOption.rowsPerPage = count;
            this.setUpProductList();
        },
        setUpProductListByPage(page){
            this.productDisplayOption.pageNo = page;
            this.setUpProductList();
        },
        changeLengthFormat(productLengthListDto){
            return productLengthListDto >= 1000 ? '999+' : productLengthListDto.toString();
        },
        createCart(data){
            const cartDto = ObjectMapperUtil.convertCartDto(data);

            //임의의 회원 번호 등록
            cartDto.mbNo = 1;

            //수량 하나로 설정
            cartDto.odQty = 1;
            
            //카트 등록 API 호출
            CartApi.create(cartDto).then(() => {
               alert('해당 상품이 장바구니에 등록되었습니다!')
            }).catch(error => {
                alert(error.response.data);
            })
        }
    }

}
</script>

<style>

</style>