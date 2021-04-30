import swal from 'sweetalert'

class SwalUtil{
    serverError(){
        swal({
            title : "에러",
            text : "서버의 상태가 좋지 않습니다. \n 잠시 후 다시 시도해주세요.",
            icon : "error",
        })
    }

    error(text){
        swal({
            title : "에러",
            text : text,
            icon : "error"
        })
    }

    success(text){
        swal({
            title : "성공",
            text : text,
            icon : "success",
            timer : 1500,
            buttons : {
                confirm : false
            }
        })
    }  
}


const instance = new SwalUtil()

export default instance