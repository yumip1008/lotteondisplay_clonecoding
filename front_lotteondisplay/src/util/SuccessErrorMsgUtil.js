import SwalUtil from './SwalUtil'

class SuccessErrorMsgUtil {
    handleSuccess(text){
        SwalUtil.success(text)
    }
    
    handleError(error){
        if(error.code === "ECONNABORTED"){
            SwalUtil.serverError();
        }else{
            SwalUtil.error(error.response.data);
        }
    }
}


const instance = new SuccessErrorMsgUtil();

export default instance;
