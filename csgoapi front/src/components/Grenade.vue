<template>
  <div id="grenade">
    <nav>
      <div class="nav-wrapper blue-grey darken-1">
        <a href="/home" class="brand-logo">CSGO API</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li class="active"><a href="/grenades">GRENADES</a></li>
          <li><a href="/heavys">HEAVYS</a></li>
          <li><a href="/pistols">PISTOLS</a></li>
          <li><a href="/rifles">RIFLES</a></li>
          <li><a href="/smgs">SMGS</a></li>
        </ul>
      </div>
    </nav>
    <div class="container">
      <form @submit.prevent="storeAndUpdateGrenade">
        <h1>GRENADES</h1>
        <div class="input-field">
          <input id="model" type="text" class="validate" v-model="grenade.model" required>
          <label for="model">Model</label>
        </div>
        <label>Team</label>
          <select class="browser-default" v-model="grenade.team" required>
            <option value="" selected disabled>Choose a Team</option>
            <option value="CT">CT</option>
            <option value="TR">TR</option>
            <option value="BOTH">BOTH</option>
          </select>
        <div class="input-field">
          <input id="price" type="text" class="validate" v-mask="'$####'" v-model="grenade.price" required>
          <label for="price">Price (ex: $xxxx)</label>
        </div>
        <div class="input-field">
          <input id="munition" type="text" class="validate" v-mask="'##/###'" v-model="grenade.munition" required>
          <label for="munition">Munition (ex: xx/xxx)</label>
        </div>
        <button class="waves-effect waves-light btn-small" name="button">Save</button>
      </form>
      <br>
      <p class="flow-text" v-show="this.grenades.length==0">No grenades Registered!</p>
      <br>
      <br>

      <table class="highlight z-depth-5" v-show="this.grenades.length>0">
          <thead><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></thead>
          <tfoot><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></tfoot>
          <tbody>
            <tr v-for="grenade of grenades" :key="grenade.id">
              <td>{{grenade.id}}</td>
              <td>{{grenade.model}}</td>
              <td>{{grenade.team}}</td>
              <td>{{grenade.price}}</td>
              <td>{{grenade.munition}}</td>
              <td>
                <button @click="update(grenade)" class="waves-effect waves-light btn-small orange accent-2">Edit</button>
                <button @click="deleteGrenade(grenade)" class="waves-effect waves-light btn-small red">Delete</button>
              </td>
            </tr>
          </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import GrenadeService from '../../services/grenadeservices'

export default {
   data() {
     return {
      grenade: {
        id: '',
        model: '',
        team: '',
        price: '',
        munition: ''
      },
      grenades: [],
     }
   },
   mounted() {
    this.listGrenades();
    },
   methods: {
     listGrenades() {
       GrenadeService.findAll().then(response => {
         this.grenades = response.data;

         console.log(this.grenades.length);
         console(response);
       })
     },
     storeAndUpdateGrenade() {

       if(!this.grenade.id) {
         GrenadeService.store(this.grenade).then(response => {
           alert("Grenade added with success!");
           this.grenade = {};
           this.listGrenades();
           this.errors = ["Erro 1", "Erro 2"];
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
           this.errors = ["Erro 1", "Erro 2"];
         })
       } else {
         GrenadeService.update(this.grenade).then(response => {
           alert("Grenade altered with success!");
           this.grenade = {};
           this.listGrenades();
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
         })
       }
     },
     update(grenade) {
       this.grenade = grenade;
     },
     deleteGrenade(grenade) {
       if(confirm("Do you want to delete the grenade?!")) {
         GrenadeService.delete(grenade).then(response => {
           alert("Grenade deleted with success!");
           this.grenade = {};
           this.listGrenades();
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
