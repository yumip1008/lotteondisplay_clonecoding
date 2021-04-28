class ProductListDataDto {
    constructor(spdNo, spdNm, sitmNo, trNo, lrtrNo, brdNm, stscrAvgScr, rvCnt, mmSlQty, dcVal, slPrc, onerFvrPrc,imgFullUrl){
        
        this.spdNo = spdNo;             //상품번호
        this.spdNm = spdNm;             //상품이름
        this.sitmNo = sitmNo;           //단품번호
        this.trNo = trNo;               //거래처번호
        this.lrtrNo  = lrtrNo;          //하위거래처번호
        this.brdNm = brdNm;             //브랜드이름
        this.stscrAvgScr = stscrAvgScr; //평균별점
        this.rvCnt = rvCnt;             //별점 개수
        this.mmSlQty = mmSlQty;         //구매 수량
        this.dcVal = dcVal;             //할인율
        this.slPrc = slPrc;             //본 가격
        this.onerFvrPrc = onerFvrPrc;   //할인된 가격
        this.imgFullUrl = imgFullUrl;   //이미지 경로
    }
}

export default ProductListDataDto