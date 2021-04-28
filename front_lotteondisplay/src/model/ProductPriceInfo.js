class ProductPriceInfo {
    constructor(price, quantity){
        this.price = price;
        this.quantity = quantity;
    }

    getTotalPrice(){
        return this.price * this.quantity;
    }
}

export default ProductPriceInfo;