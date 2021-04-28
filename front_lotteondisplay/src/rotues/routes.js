import VueRouter from 'vue-router'
import productRoutes from './productRoutes'
import cartRoutes from './cartRoutes'

export default new VueRouter({
    routes : [
        ...productRoutes,
        ...cartRoutes
    ]
});