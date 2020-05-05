<template>
  <div id="heavy">
    <nav>
      <div class="nav-wrapper blue-grey darken-1">
        <a href="/home" class="brand-logo">CSGO API</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
          <li><a href="/grenades">GRENADES</a></li>
          <li class="active"><a href="/heavys">HEAVYS</a></li>
          <li><a href="/pistols">PISTOLS</a></li>
          <li><a href="/rifles">RIFLES</a></li>
          <li><a href="/smgs">SMGS</a></li>
        </ul>
      </div>
    </nav>
    <div class="container">
      <form @submit.prevent="storeAndUpdateHeavy">
        <h1>HEAVYS</h1>
        <div class="input-field">
          <input id="model" type="text" class="validate" v-model="heavy.model" required>
          <label for="model">Model</label>
        </div>
        <label>Team</label>
          <select class="browser-default" v-model="heavy.team" required>
            <option value="" selected disabled>Choose a Team</option>
            <option value="CT">CT</option>
            <option value="TR">TR</option>
            <option value="BOTH">BOTH</option>
          </select>
        <div class="input-field">
          <input id="price" type="text" class="validate" v-mask="'$####'" v-model="heavy.price" required>
          <label for="price">Price (ex: $xxxx)</label>
        </div>
        <div class="input-field">
          <input id="munition" type="text" class="validate" v-mask="'##/###'" v-model="heavy.munition" required>
          <label for="munition">Munition (ex: xx/xxx)</label>
        </div>
        <button class="waves-effect waves-light btn-small" name="button">Save</button>
      </form>
      <br>
      <p class="flow-text" v-show="this.heavys.length==0">No heavys Registered!</p>
      <br>
      <br>

      <table class="highlight z-depth-5" v-show="this.heavys.length>0">
          <thead><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></thead>
          <tfoot><tr><th>ID</th><th>Model</th><th>Team</th><th>Price</th><th>Munition</th></tr></tfoot>
          <tbody>
            <tr v-for="heavy of heavys" :key="heavy.id">
              <td>{{heavy.id}}</td>
              <td>{{heavy.model}}</td>
              <td>{{heavy.team}}</td>
              <td>{{heavy.price}}</td>
              <td>{{heavy.munition}}</td>
              <td>
                <button @click="update(heavy)" class="waves-effect waves-light btn-small orange accent-2">Edit</button>
                <button @click="deleteHeavy(heavy)" class="waves-effect waves-light btn-small red">Delete</button>
              </td>
            </tr>
          </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import HeavyService from '../../services/heavyservices'

export default {
   data() {
     return {
      heavy: {
        id: '',
        model: '',
        team: '',
        price: '',
        munition: ''
      },
      heavys: [],
     }
   },
   mounted() {
    this.listHeavys();
    },
   methods: {
     listHeavys() {
       HeavyService.findAll().then(response => {
         this.heavys = response.data;

         console.log(this.heavy.length);
         console(response);
       })
     },
     storeAndUpdateHeavy() {

       if(!this.heavy.id) {
         HeavyService.store(this.heavy).then(response => {
           alert("Heavy added with success!");
           this.heavy = {};
           this.listHeavys();
           this.errors = ["Erro 1", "Erro 2"];
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
           this.errors = ["Erro 1", "Erro 2"];
         })
       } else {
         HeavyService.update(this.heavy).then(response => {
           alert("Heavy altered with success!");
           this.heavy = {};
           this.listHeavys();
           return response;
         }).catch(error => {
           console.log("Erro! " + error);
         })
       }
     },
     update(heavy) {
       this.heavy = heavy;
     },
     deleteHeavy(heavy) {
       if(confirm("Do you want to delete the heavy?!")) {
         HeavyService.delete(heavy).then(response => {
           alert("Heavy deleted with success!");
           this.heavy = {};
           this.listHeavys();
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
