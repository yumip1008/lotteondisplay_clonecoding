class Cart {
    constructor(cartSn, trNo, lrtrNo, mbNo, spdNo, sitmNo, odQty, regDttm, modDttm){
        this.cartSn = cartSn;   //장바구니 순번
        this.trNo = trNo;       //거래처 번호
        this.lrtrNo = lrtrNo;   //하위거래처 번호
        this.mbNo = mbNo;       //회원번호
        this.spdNo = spdNo;     //상품번호
        this.sitmNo = sitmNo;   //단품번호
        this.odQty = odQty;     //주문수량
        this.regDttm = regDttm;
        this.modDttm = modDttm;
    }
}

export default Cart