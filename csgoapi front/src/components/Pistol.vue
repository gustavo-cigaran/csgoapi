<template>
  <div id="pistol">
    <nav>
      <div class="nav-wrapper blue-grey darken-1">
        <a href="/home" class="brand-logo">CSGO API</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><a href="/grenades">GRENADES</a></li>
          <li><a href="/heavys">HEAVYS</a></li>
          <li class="active"><a href="/pistols">PISTOLS</a></li>
          <li><a href="/rifles">RIFLES</a></li>
          <li><a href="/smgs">SMGS</a></li>
        </ul>
      </div>
    </nav>
    <div class="container">
      <form @submit.prevent="storeAndUpdatePistol">
        <h1>PISTOLS</h1>
        <div class="input-field">
          <input id="model" type="text" class="validate" v-model="pistol.model" required>
          <label for="model">Model</label>
        </div>
        <label>Team</label>
          <select class="browser-default" v-model="pistol.team" required>
            <option value="" selected disabled>Choose a Team</option>
            <option value="CT">CT</option>
            <option value="TR">TR</option>
            <option value="BOTH">BOTH</option>
          </select>
        <div class="input-field">
          <input id="price" type="text" class="validate" v-mask="'$####'" v-model="pistol.price" required>
          <label for="price">Price (ex: $xxxx)</label>
        </div>
        <div class="input-field">
          <input id="munition" type="text" class="validate" v-mask="'##/###'" v-model="pistol.munition" required>
          <label for="munition">Munition (ex: xx/xxx)</label>
        </div>
        <button class="waves-effect waves-light btn-small" name="button">Save</button>
      </form>
      <br>
      <p class="flow-text" v-show="this.pistols.length==0">No pistols Registered!</p>
      <br>
      <br>

      <table class="highlight z-depth-5" v-show="this.pistols.length>0">
          <thead><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></thead>
          <tfoot><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></tfoot>
          <tbody>
            <tr v-for="pistol of pistols" :key="pistol.id">
              <td>{{pistol.id}}</td>
              <td>{{pistol.model}}</td>
              <td>{{pistol.team}}</td>
              <td>{{pistol.price}}</td>
              <td>{{pistol.munition}}</td>
              <td>
                <button @click="update(pistol)" class="waves-effect waves-light btn-small orange accent-2">Edit</button>
                <button @click="deletePistol(pistol)" class="waves-effect waves-light btn-small red">Delete</button>
              </td>
            </tr>
          </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import PistolService from '../../services/pistolservices'

export default {
   data() {
     return {
      pistol: {
        id: '',
        model: '',
        team: '',
        price: '',
        munition: ''
      },
      pistols: [],
     }
   },
   mounted() {
    this.listPistols();
    },
   methods: {
     listPistols() {
       PistolService.findAll().then(response => {
         this.pistols = response.data;

         console.log(this.pistols.length);
         console(response);
       })
     },
     storeAndUpdatePistol() {

       if(!this.pistol.id) {
         PistolService.store(this.pistol).then(response => {
           alert("Pistol added with success!");
           this.pistol = {};
           this.listPistols();
           this.errors = ["Erro 1", "Erro 2"];
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
           this.errors = ["Erro 1", "Erro 2"];
         })
       } else {
         PistolService.update(this.pistol).then(response => {
           alert("Pistol altered with success!");
           this.pistol = {};
           this.listPistols();
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
         })
       }
     },
     update(pistol) {
       this.pistol = pistol;
     },
     deletePistol(pistol) {
       if(confirm("Do you want to delete the pistol?!")) {
         PistolService.delete(pistol).then(response => {
           alert("Pistol deleted with success!");
           this.pistol = {};
           this.listPistols();
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
         })
       }
     }
   }
 }
</script>

<style>

</style>
