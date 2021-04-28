<template>
  <div>
      <v-divider></v-divider>
      <v-container fluid>
          <v-row>
              <v-col v-for="(data, index) in dataList" :key="index" cols="2"> 
                <v-card
                    class="mx-auto"
                    max-width="204"
                    height="400"   
                >

                <v-img
                    :src="data.imgFullUrl"
                    max-height="204"
                    max-width="204"
                    >
                </v-img>
                
                <v-card-text class="info">
                    <div class="block-with-text">
                    <strong>{{data.brdNm}}</strong>
                    {{data.spdNm}}
                    </div>

                    <div class="evalutaion" v-if="data.mmSlQty">
                        <span class="rating" v-if="data.rvCnt">
                            <v-icon 
                                x-small
                                color="orange darken-2">fas fa-star</v-icon>
                            {{data.stscrAvgScr}}
                            ({{getConvertedPrice(data.rvCnt)}})
                            </span>
                        <span class="monthly-sold">{{getConvertedPrice(data.mmSlQty)}}개 구매</span>
                    </div>

                    <div class="price">
                        <span class="sale">{{data.dcVal}}%</span>
                        <span class="real">{{getConvertedPrice(data.slPrc)}}원</span>
                    </div>

                    <div>
                        <strong class="final">{{getConvertedPrice(data.onerFvrPrc)}}</strong>
                        <span>원</span>
                    </div>

                    <div class="cart">
                        <button @click="emitClickedProduct(data)">장바구니 담기</button>
                    </div>
                </v-card-text>
                </v-card>
              </v-col>
          </v-row>
      </v-container>
  </div>
</template>

<script>
import _ from 'lodash'
import {convertedPrice} from '@/mixins'

export default {
    mixins : [convertedPrice],
    props : {
        dataList : Array,
    },
    methods : {
        emitClickedProduct : _.debounce(function(data){
            this.$emit('getClickedProduct', data);
        },500),
    }
}
</script>

<style>

.block-with-text{
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-wrap: break-word;
    white-space: normal;
    widows: 2;
    overflow: hidden;
    text-overflow: ellipsis;
    font-size: 14px;
    line-height: 21px;
}

.block-with-text > strong{
    color : #333333;
    font-weight: 700;
}

.rating {
    font-size: 12px;
    line-height: 16px;
    letter-spacing: 0;
}

.monthly-sold{
    padding-left: 12px;
    font-size: 11px;
    line-height: 16px;
    letter-spacing: -.1px;
    color: #999;
}

.price{
    font-size: 13px;
    margin-top: 5px;
    line-height: 1;
}

.sale{
    color: #ef2a23;
    display: inline-block;
    font-size: 12px;
    vertical-align: top;
    margin: 0 4px 0 0;
    line-height: 17px;
}

.real{
    display: inline-block;
    margin-bottom: 1px;
    color: #999;
    line-height: 17px;
    font-size: 12px;
    text-decoration: line-through;
}

.final{
    line-height: 27px;
    font-size: 19px;
    display: inline-block;
    font-weight: 700;
}

.cart {
    padding-top : 10px;
}
</style>