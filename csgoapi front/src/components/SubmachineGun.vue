<template>
  <div id="smg">
    <nav>
      <div class="nav-wrapper blue-grey darken-1">
        <a href="/home" class="brand-logo">CSGO API</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><a href="/grenades">GRENADES</a></li>
          <li><a href="/heavys">HEAVYS</a></li>
          <li><a href="/pistols">PISTOLS</a></li>
          <li><a href="/rifles">RIFLES</a></li>
          <li class="active"><a href="/smgs">SMGS</a></li>
        </ul>
      </div>
    </nav>
    <div class="container">
      <form @submit.prevent="storeAndUpdateSubmachineGun">
        <h1>SUBMACHINE GUNS</h1>
        <div class="input-field">
          <input id="model" type="text" class="validate" v-model="smg.model" required>
          <label for="model">Model</label>
        </div>
        <label>Team</label>
          <select class="browser-default" v-model="smg.team" required>
            <option value="" selected disabled>Choose a Team</option>
            <option value="CT">CT</option>
            <option value="TR">TR</option>
            <option value="BOTH">BOTH</option>
          </select>
        <div class="input-field">
          <input id="price" type="text" class="validate" v-mask="'$####'" v-model="smg.price" required>
          <label for="price">Price (ex: $xxxx)</label>
        </div>
        <div class="input-field">
          <input id="munition" type="text" class="validate" v-mask="'##/###'" v-model="smg.munition" required>
          <label for="munition">Munition (ex: xx/xxx)</label>
        </div>
        <button class="waves-effect waves-light btn-small" name="button">Save</button>
      </form>
      <br>
      <p class="flow-text" v-show="this.smgs.length==0">No SMGs Registered!</p>
      <br>
      <br>

      <table class="highlight z-depth-5" v-show="this.smgs.length>0">
          <thead><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></thead>
          <tfoot><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></tfoot>
          <tbody>
            <tr v-for="smg of smgs" :key="smg.id">
              <td>{{smg.id}}</td>
              <td>{{smg.model}}</td>
              <td>{{smg.team}}</td>
              <td>{{smg.price}}</td>
              <td>{{smg.munition}}</td>
              <td>
                <button @click="update(smg)" class="waves-effect waves-light btn-small orange accent-2">Edit</button>
                <button @click="deleteRifle(smg)" class="waves-effect waves-light btn-small red">Delete</button>
              </td>
            </tr>
          </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import SubmachineGunService from '../../services/submachinegunservices'

export default {
   data() {
     return {
      smg: {
        id: '',
        model: '',
        team: '',
        price: '',
        munition: ''
      },
      smgs: [],
     }
   },
   mounted() {
    this.listSubmachineGuns();
    },
   methods: {
     listSubmachineGuns() {
       SubmachineGunService.findAll().then(response => {
         this.smgs = response.data;

         console.log(this.smgs.length);
         console(response);
       })
     },
     storeAndUpdateSubmachineGun() {

       if(!this.smg.id) {
         SubmachineGunService.store(this.smg).then(response => {
           alert("SMG added with success!");
           this.smg = {};
           this.listSubmachineGuns();
           this.errors = ["Erro 1", "Erro 2"];
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
           this.errors = ["Erro 1", "Erro 2"];
         })
       } else {
         SubmachineGunService.update(this.smg).then(response => {
           alert("SMG altered with success!");
           this.smg = {};
           this.listSubmachineGuns();
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
         })
       }
     },
     update(smg) {
       this.smg = smg;
     },
     deleteSubmachineGun(smg) {
       if(confirm("Do you want to delete the SMG?!")) {
        SubmachineGunService.delete(smg).then(response => {
           alert("SMG deleted with success!");
           this.smg = {};
           this.listSubmachineGuns();
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
