const app = Vue.
    createApp({

        data() {
            return {
                //Current Client
                nft: [],
                favorites:[],
                favoritesBag: [],
                filteredNft: [],
                clientRegister:[], 


                collections: [],
                created: [],
                favorites: [],

                currentClient: [],
                firstName: [],
                lastName: [],
                email: [],
                balance: [],
                logged: false,
            }
        },

        created() {
            axios.get('/api/products')
                .then(response => {
                    this.filteredNft = response.data.filter(p => p.active == true);
                })
                .catch(function (error) {
                    console.log(error);
                });

            axios.get('/api/clients/current')
                .then(response => {
                    this.currentClient = response.data;
                    if (this.client != {}){this.logged = true}
                    this.firstName = this.currentClient.firstName;
                    this.lastName = this.currentClient.lastName;
                    this.email = this.currentClient.email;
                    this.balance = this.currentClient.balance;
                    this.product = this.currentClient.product;
                    this.nft = this.currentClient.product.filter(p => p.active == true);
                    this.created = this.nft.filter(p => this.currentClient.created.includes(p.id));
                    this.favorites = response.data.favorites;
                    this.favoritesBag = this.filteredNft.filter(p => this.favorites.includes(p.id))
                });
                axios.get('/api/clients/current')
                .then(response => {
                this.clientRegister = response.data
                })


        },


        methods: {
            deleteProduct(card) {
                this.productIdDelete = card.id;
                axios.patch('/api/clients/current/product/delete', `productId=${this.productIdDelete}`)
                    .then(response => {
                        swal("The NFT has been deleted!", "If you want to undo the change, please contact us", "warning", {
                            timer: 4000,
                        })
                            .then(response => window.location.reload())
                    })
                    .catch(error => {
                        swal("Error!", error.response.data, "error", {
                            timer: 6000,
                        })
                    })
            },

            editProduct(card) {
                this.productIdEdit = card.id;
                axios.patch('/api/clients/current/product/edit', `productId=${this.productIdEdit}&description=${this.description}&price=${this.price}&sell=${this.sell}`)
                    .then(response => {
                        swal("The NFT has been edited!", "If you want to undo the change, please contact us", "warning", {
                            timer: 4000,
                        })
                            .then(response => window.location.reload())
                    })
            },
        
            currentDateTime() {
                const time = new Date().getHours();

                if (time >= 6 && time < 12) {
                    return this.welcome = "Good Morning";
                }
                else if (time >= 12 && time < 18) {
                    return this.welcome = "Good Afternoon";
                }
                else if (time >= 18 && time < 22) {
                    return this.welcome = "Good Evening";
                } else {
                    return this.welcome = "Good Night";
                }
            },

            newBalance(balance) {
                balance = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(balance);
                return balance;
            },

            formatNumber(dollarUSLocale) {
                dollarUSLocale = new Intl.NumberFormat('en-US').format(dollarUSLocale);
                return dollarUSLocale;
            },

        },
        logOut(){
            axios.post('/api/logout')
            .then(response => location.href = '/web/Structure/index.html')
         },


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