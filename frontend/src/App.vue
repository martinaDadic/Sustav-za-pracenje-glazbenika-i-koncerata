<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div id="container">
    <header>

      <div class="wrapper">
        <h1>SPGK</h1>

        <nav>
          <RouterLink to="/">Svi koncerti</RouterLink>
          <RouterLink to="/novi-koncert">Novi koncert</RouterLink>
        </nav>
      </div>
    </header>

    <RouterView />
  </div>
</template>

<style scoped>
  a{
    background-color:rgb(108, 160, 255);
    border: 2px solid;
    border-radius: 5px;
    margin:1rem;
    color:black;
    text-decoration:none;
    padding: 5px;
  }
  a:hover {
    background-color:rgb(168, 198, 255);
  }
  a.router-link-active {
    background-color:rgb(36, 113, 255);
  }
</style>
