class CartDetailDto{
    constructor(cartSn, spdNo, spdNm, sitmNo, sitmNm, 
        trGrpCd, trNo, lrtrNo, brdNm, imgJsn, itmByMaxPurPsbJsn, itmByMinPurJsn,
        slPrc, estmtDlvTxt, mbNo, odQty){
        
        this.cartSn = cartSn;                   //장바구니 번호
        this.spdNo = spdNo;                     //상품번호
        this.spdNm = spdNm;                     //상품이름
        this.sitmNo = sitmNo                    //단품번호
        this.sitmNm = sitmNm;                   //단일상품 이름


        this.trGrpCd = trGrpCd;                 //거래처그룹코드
        this.trNo = trNo                        //거래처번호
        this.lrtrNo = lrtrNo;                   //하위거래처번호
        
        this.brdNm = brdNm;                     //브랜드 이름
        this.imgJsn = imgJsn;                   //이미지

        this.itmByMaxPurPsbJsn = itmByMaxPurPsbJsn; //최대 수량
        this.itmByMinPurJsn = itmByMinPurJsn;       //최소 수량


        this.slPrc = slPrc;                     //판매가
        this.estmtDlvTxt = estmtDlvTxt;         //도착문구

        this.mbNo = mbNo;                       //회원번호
        this.odQty = odQty;                     //수량
    }
}

export default CartDetailDto