const spinnerStore = {
    state : {
        loading : false
    },
    getters : {
        getLoading(state){
            return state.loading;
        }
    },
    mutations : {
        setLoading(state, loading){
            state.loading = loading;
        }
    },
}

export default spinnerStore