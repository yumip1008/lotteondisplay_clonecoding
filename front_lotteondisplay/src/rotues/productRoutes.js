import { ProductView, ProductDisplayView } from "@/views";


const productRoutes = [
    {
        path: '/product', component: ProductView,
        children: [
            {
                path: 'display',
                name: 'ProductDisplay',
                component: ProductDisplayView,
            },
        ]
    }

]

export default productRoutes;