const app = Vue.
    createApp({

        data() {
            return {
                clientRegister:[], 
                name: "",
                description: "",
                price: "",
                image: "",
                currentClient: [],
                logged: false,

            }
        },

        created() {
            /* axios.get('/api/clients/current')
                .then(response => {
                    this.currentClient = response.data;
                    if (this.client != {}){this.logged = true}
                }); */
                axios.get('/api/clients/current')
                .then(response => {
                this.clientRegister = response.data
                })
        },

        methods: {
            newProduct() {
                axios.post('/api/clients/current/product/create', {
                    "name": `${this.name}`,
                    "description": `${this.description}`,
                    "price": `${this.price}`,
                    "image": `${this.image}`,
                })
                    .then(response => {
                        swal("NFT Created!", "You can see it in your collection", "success", {
                            timer: 4000,
                        });
                    })
                    .catch(error => {
                        swal(error.response.data, "Error", "error", {
                            timer: 4000,
                        })

                    });
            },
            logOut(){
                axios.post('/api/logout')
                .then(response => location.href = '/web/Structure/index.html')
             },

        }

    }).mount('#app')

document.onreadystatechange = function () {
    let lastScrollPosition = 0;
    const navbar = document.querySelector('.navbar');
    window.addEventListener('scroll', function (e) {
        lastScrollPosition = window.scrollY;
        if (lastScrollPosition > 100)
            navbar.classList.add('navbar-dark'); else
            navbar.classList.remove('navbar-dark');
    });
}