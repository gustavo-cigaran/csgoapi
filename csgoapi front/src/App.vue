<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>

@font-face {
  font-family: CS;
  src: url(./assets/cs_regular.ttf);
}

#app {
  font-family: CS;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #ffffff;
  height: auto;
  background-color: #eeeeee;
  background-image: url(./assets/background.jpg);
  background-size: cover;
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
}

input{
  color: white;
}

</style>
