<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div id="container">
    <header>

      <div class="wrapper">
        <h1>SPGK</h1>

        <nav>
          <RouterLink to="/">Home</RouterLink>
          <RouterLink to="/novi-koncert">Novi koncert</RouterLink>
        </nav>
      </div>
    </header>

    <RouterView />
  </div>
</template>

<style scoped>

</style>
