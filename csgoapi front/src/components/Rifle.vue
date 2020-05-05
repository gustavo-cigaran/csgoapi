<template>
  <div id="rifle">
    <nav>
      <div class="nav-wrapper blue-grey darken-1">
        <a href="/home" class="brand-logo">CSGO API</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><a href="/grenades">GRENADES</a></li>
          <li><a href="/heavys">HEAVYS</a></li>
          <li><a href="/pistols">PISTOLS</a></li>
          <li class="active"><a href="/rifles">RIFLES</a></li>
          <li><a href="/smgs">SMGS</a></li>
        </ul>
      </div>
    </nav>
    <div class="container">
      <form @submit.prevent="storeAndUpdateRifle">
        <h1>RIFLES</h1>
        <div class="input-field">
          <input id="model" type="text" class="validate" v-model="rifle.model" required>
          <label for="model">Model</label>
        </div>
        <label>Team</label>
          <select class="browser-default" v-model="rifle.team" required>
            <option value="" selected disabled>Choose a Team</option>
            <option value="CT">CT</option>
            <option value="TR">TR</option>
            <option value="BOTH">BOTH</option>
          </select>
        <div class="input-field">
          <input id="price" type="text" class="validate" v-mask="'$####'" v-model="rifle.price" required>
          <label for="price">Price (ex: $xxxx)</label>
        </div>
        <div class="input-field">
          <input id="munition" type="text" class="validate" v-mask="'##/###'" v-model="rifle.munition" required>
          <label for="munition">Munition (ex: xx/xxx)</label>
        </div>
        <button class="waves-effect waves-light btn-small" name="button">Save</button>
      </form>
      <br>
      <p class="flow-text" v-show="this.rifles.length==0">No rifles Registered!</p>
      <br>
      <br>

      <table class="highlight z-depth-5" v-show="this.rifles.length>0">
          <thead><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></thead>
          <tfoot><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></tfoot>
          <tbody>
            <tr v-for="rifle of rifles" :key="rifle.id">
              <td>{{rifle.id}}</td>
              <td>{{rifle.model}}</td>
              <td>{{rifle.team}}</td>
              <td>{{rifle.price}}</td>
              <td>{{rifle.munition}}</td>
              <td>
                <button @click="update(rifle)" class="waves-effect waves-light btn-small orange accent-2">Edit</button>
                <button @click="deleteRifle(rifle)" class="waves-effect waves-light btn-small red">Delete</button>
              </td>
            </tr>
          </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import RifleService from '../../services/rifleservices'

export default {
   data() {
     return {
      rifle: {
        id: '',
        model: '',
        team: '',
        price: '',
        munition: ''
      },
      rifles: [],
     }
   },
   mounted() {
    this.listRifles();
    },
   methods: {
     listRifles() {
       RifleService.findAll().then(response => {
         this.rifles = response.data;

         console.log(this.rifles.length);
         console(response);
       })
     },
     storeAndUpdateRifle() {

       if(!this.rifle.id) {
         RifleService.store(this.rifle).then(response => {
           alert("Rifle added with success!");
           this.rifle = {};
           this.listRifles();
           this.errors = ["Erro 1", "Erro 2"];
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
           this.errors = ["Erro 1", "Erro 2"];
         })
       } else {
         RifleService.update(this.rifle).then(response => {
           alert("Rifle altered with success!");
           this.rifle = {};
           this.listRifles();
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
         })
       }
     },
     update(rifle) {
       this.rifle = rifle;
     },
     deleteRifle(rifle) {
       if(confirm("Do you want to delete the rifle?!")) {
         RifleService.delete(rifle).then(response => {
           alert("Rifle deleted with success!");
           this.rifle = {};
           this.listRifles();
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
