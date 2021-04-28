import axios from 'axios';

class AxiosClient {
    constructor(baseURL){
        this.baseURL = baseURL,
        this.client = axios.create({
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*"
            }
        })
    }
    
    async post(data) {
        return await this.client.post(this.baseURL, data);
    }

    async get(params) {
        return await this.client.get(this.baseURL, {params: params});
    }

    async put(data) {
        return await this.client.put(this.baseURL, data);
    }

    async delete(params) {
        return await this.client.delete(this.baseURL, {params : params});
    }
}

export default AxiosClient;