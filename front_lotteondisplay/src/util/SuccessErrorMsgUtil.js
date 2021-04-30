import SwalUtil from './SwalUtil'

class SuccessErrorMsgUtil {
    handleSuccess(text){
        SwalUtil.success(text)
    }
    
    handleError(error){
        if(error.code === "ECONNABORTED"){
            SwalUtil.error("서버 상태가 좋지 않습니다. 다시 시도해주세요.");
        }else{
            SwalUtil.error(error.response.data);
        }
    }
}


const instance = new SuccessErrorMsgUtil();

export default instance;
