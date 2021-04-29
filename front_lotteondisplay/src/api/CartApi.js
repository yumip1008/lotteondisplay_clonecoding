import AxiosClient from "./AxiosClient";

class CartApi {
    constructor(){
        this.client = new AxiosClient('/api/cart')
    }

    async create(cartDto){
        const status = (await this.client.post(cartDto)).status;
        return status;
    }

    async getAllCartsbymbNo(mbNo){
        const response = (await this.client.get({mbNo : mbNo})).data;
        return response;
    }

    async update(cartDto){
        const response = (await this.client.put(cartDto)).status;
        return response;
    }

    async delete(cartSn){
        const response = (await this.client.delete({cartSn : cartSn})).status;
        return response;
    }

    async deleteCarts(cartSnArr){
        const response = (await this.client.postURL("/deleteCarts", cartSnArr)).status;
        return response;
    }
}

const instance = new CartApi();

export default instance;