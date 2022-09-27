/* this.queryString = location.search;
    this.params = new URLSearchParams(this.queryString)
    this.id = this.params.get("id");
    this.accountsLoaded(); */
const app = Vue.
  createApp({

    data() {
      return {
        //Shop
        nft: [],
        filteredNft: [],
        textSearch: "",
        clientRegister:[], 

        //Register
        firstName: "",
        lastName: "",
        email: "",
        password: "",

        //Sign in
        emailSignIn: "",
        passwordSignIn: "",

        //Edit Product
        description: "",
        price: "",
        sell: "",

        addProductId: "",
        deleteProductId: "",

        addFavoritesId: "",
        favorites: [],
        favoritesBag: [],

        bag: [],
      }
    },

    created() {
      axios.get('/api/products')
        .then(response => {
          this.nft = response.data.filter(p => p.active == true);
          this.filteredNft = response.data.filter(p => p.sell == true);
        })
        .catch(function (error) {
          console.log(error);
        });

      axios.get('/api/clients/current/cart')
        .then(response => {
          this.bag = response.data;

        })
        .catch(function (error) {
          console.log(error)
        });
      axios.get('/api/clients/current')
        .then(response => {
        this.clientRegister = response.data
        })
  },
    methods: {
      register() {
        axios.post('/api/clients/register', `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
          .then(response =>
            this.firstName = "",
            this.lastName = "",
            this.email = "",
            this.password = "",
          )
          .then(response => {
            swal("Welcome to Artics!", "Check your email to validate your account", "success", {
              timer: 3000,
            })
              .then(response => window.location.reload())
          })
          .catch(error => {
            swal("Error!", error.response.data, "error", {
              timer: 6000,
            })
          })
      },

      sigIn() {
        axios.post('/api/login', `email=${this.emailSignIn}&password=${this.passwordSignIn}`, {
          headers: { 'content-type': 'application/x-www-form-urlencoded' }
        })
          .then(response => {
            swal("Welcome back to Artics!", "Enjoy it", "success", {
              timer: 4000,
            })
              .then(response => location.href = '/web/Structure/profile.html')
          })
          .catch(error => {
            swal("Error!", error.response.data, "error", {
              timer: 6000,
            })
          })
      },
      logOut(){
        axios.post('/api/logout')
        .then(response => location.href = '/web/Structure/index.html')
     },
      newBalance(balance) {
        balance = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(balance);
        return balance;
      },

      formatNumber(dollarUSLocale) {
        dollarUSLocale = new Intl.NumberFormat('en-US').format(dollarUSLocale);
        return dollarUSLocale;
      },

      priceLow() {
        this.filteredNft = this.nft.sort((a, b) => {
          return a.price - b.price
        })
      },

      priceHight() {
        this.filteredNft = this.nft.sort((a, b) => {
          return b.price - a.price
        })
      },

      nameAz() {
        this.filteredNft = this.nft.sort((a, b) => {
          if (a.name.toLowerCase() < b.name.toLowerCase()) {
            return -1
          }
        })
      },

      nameZa() {
        this.filteredNft = this.nft.sort((a, b) => {
          if (a.name.toLowerCase() > b.name.toLowerCase()) {
            return -1
          }
        })
      },

      searchNft() {
        this.filteredNft = this.nft.filter((nft) =>
          nft.name.toLowerCase().includes(this.textSearch.toLowerCase()));
      },

      addBag(card) {
        axios.patch('/api/clients/current/cart', `productId=${card}`)
          .then(response => {
            swal("Success added to cart!", "See you cart", "success", {
              timer: 4000,
            })
            .then(response => location.reload())
          })
          .catch(error => {
            swal("Error!", error.response.data, "error", {
              timer: 6000,
            })
          })
      },

      deleteBag(id) {
        this.deleteProductId = id;
        axios.patch('/api/clients/current/cart/remove', `clientProductId=${this.deleteProductId}`)
          .then(response => {
            swal("Product remove of your cart!", "", "warning", {
              timer: 4000,
            })
              .then(response => location.reload())
          })
          .catch(error => {
            swal("Error!", error.response.data, "error", {
              timer: 6000,
            })
          })
      },

      payNow() {
        swal({
          title: "We are processing your purchase, this may take a few moments...",
          icon: "success"
        })
        axios.post("/api/clients/current/transaction")
          .then(response => {
            if (response.status == 201) {
              this.endPurchase()
            }
          })
         .catch(response => {
            swal(response.response.data, {
              title: "Something went wrong with the details you provided us, please retry.",
              buttons: "OK!",
              icon: "error"
            })
          }) 
      },

      endPurchase() {
        axios.post("/api/clients/current/endPurchase")
          .then(response => {
            if (response.status == 201) {
              swal({
                title: "We have sent you an email with your invoice and the details of your purchase",
                icon: "success"
              })

            }
          })
          .catch(error => {
            swal("Error!", error.response.data, "error", {
              timer: 6000,
            })
          })
      },

      addFavorites(card) {
        this.addFavoritesId = card.id
        axios.patch('/api/clients/current/favorite', `productId=${this.addFavoritesId}`)
          .then(response => {
            swal("Product add to favorites!", "Now you can see it in favorites", "success", {
              timer: 4000,
            })
          })
          .catch(error => {
            swal("Error!", error.response.data, "error", {
              timer: 6000,
            })
          })
      },

      emptyCart() {
        axios.delete('api/clients/current/cart/delete')
      },
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
const container = document.getElementById("container");
const signInEmail = document.getElementById("signInEmail");
const signUpEmail = document.getElementById("signUpEmail");

function openSignIn() {
  container.classList.remove("right-panel-active");
  if (signUpEmail.value !== "") {
    signInEmail.value = signUpEmail.value;
  }
}
function openSignUp() {
  container.classList.add("right-panel-active");
  if (signInEmail.value !== "") {
    signUpEmail.value = signInEmail.value;
  }
}
var slideIndex = 0;
showSlides();

function showSlides() {
  var i;
  var slides = document.getElementsByClassName("container__image");
  var dots = document.getElementsByClassName("dot");

  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }

  slideIndex++;
  if (slideIndex > slides.length) {
    slideIndex = 1;
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" activeviolet", "");
  }

  slides[slideIndex - 1].style.display = "block";
  dots[slideIndex - 1].className += " activeviolet";
  setTimeout(showSlides, 5000); // Change image every 2 seconds
}
