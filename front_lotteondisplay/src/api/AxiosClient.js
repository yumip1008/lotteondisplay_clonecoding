import axios from 'axios';
import store from '@/stores';

class AxiosClient {
    constructor(baseURL){
        this.baseURL = baseURL,
        this.client = axios.create({
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*"
            },
            timeout : 5000
        })
    }
    
    async post(data) {
        store.commit('setLoading', true);
        try {
            const response =  await this.client.post(this.baseURL, data);
            store.commit('setLoading', false);  
            return response;
            
        }catch(error) {
            store.commit('setLoading', false);  
            throw error;
        }
              
    }

    async postURL(url, data) {
        store.commit('setLoading', true);
        try{
            const response = await this.client.post(this.baseURL+url, data);
            store.commit('setLoading', false);
            return response;
        }catch(error){
            store.commit('setLoading', false);  
            throw error;
        }
    }

    async get(params) {
        store.commit('setLoading', true);
        try{
            const response = await this.client.get(this.baseURL, {params: params});
            store.commit('setLoading', false);
            return response;
        }catch(error){
            store.commit('setLoading', false);
            throw error;
        }
    }

    async put(data) {
        store.commit('setLoading', true);
        try{
            const response = await this.client.put(this.baseURL, data);
            store.commit('setLoading', false);
            return response;
        }catch(error){
            store.commit('setLoading', false);
            throw error;
        }
    }

    async delete(params) {
        store.commit('setLoading', true);
        try{
            const response = await this.client.delete(this.baseURL, {params : params});
            store.commit('setLoading', false);
            return response;
        }catch(error){
            store.commit('setLoading', false);
            throw error;
        }
    }
}

export default AxiosClient;