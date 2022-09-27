const urlParams = new URLSearchParams(window.location.search);
const urlName = urlParams.get('id');

const app = Vue.
    createApp({

        data() {
            return {
                currentClient: [],
                nft: [],
                nftId: [],
                clientRegister:[], 

                priceBtc: [],
                priceEth: [],

                productIdEdit: "",
                description: "",
                price: "",
                
                sell: "false",
                sell: "true",

                logged: false,
            }
        },

        created() {
            axios.get('/api/clients/current')
                .then(response => {
                    this.currentClient = response.data
                    if (this.client != {}){this.logged = true}
                    this.nft = this.currentClient.product.filter(p => p.active == true);
                    this.nftId = this.nft.find(n => n.id == urlName);
                    this.created = this.nft.filter(p => this.currentClient.created.includes(p.id));
                    this.favorites = this.nft.filter(p => this.currentClient.favorites.includes(p.id));
                });

            axios.get('https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false')
                .then((response) => {
                    this.coins = response.data;
                    console.log(this.coins)
                    this.priceBtc = this.coins.filter(a => a.name == "Bitcoin")[0].current_price;
                    this.priceEth = this.coins.filter(a => a.name == "Ethereum")[0].current_price;

                    console.log(this.priceEth)
                })
                .catch(function (error) {
                    console.log(error);
                });
                axios.get('/api/clients/current')
                .then(response => {
                this.clientRegister = response.data
                })
                

        },

        methods: {
            editProduct(nftId) {
                this.productIdEdit = nftId.id;
                axios.patch('/api/clients/current/product/edit', `productId=${this.productIdEdit}&description=${this.description}&price=${this.price}&sell=${this.sell}`)
                    .then(response => {
                        swal("The NFT has been edited!", "If you want to undo the change, please contact us", "warning", {
                            timer: 4000,
                        })
                            .then(response => window.location.reload())
                    })
            },
            newBalance(balance) {
                balance = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(balance);
                return balance;
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
