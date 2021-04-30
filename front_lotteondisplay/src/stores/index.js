import Vue from 'vue'
import Vuex from 'vuex'

import spinnerStore from './modules/spinnerStore'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules : {
        spinnerStore : spinnerStore,
    }
})

export default store