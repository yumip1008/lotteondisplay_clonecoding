import AxiosClient from "./AxiosClient";

class ProductApi {
    constructor(){
        this.instance = new AxiosClient('/api/product')
    }

    async getAll(productDisplayOption){
        const data =  (await this.instance.post(productDisplayOption)).data;
        return data;        
    }
}

const instance = new ProductApi();

export default instance;