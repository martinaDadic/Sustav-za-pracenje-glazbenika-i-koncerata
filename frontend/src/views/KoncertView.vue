<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()
</script>

<template>
  <div>
    <h1>Podaci o koncertu:</h1>
    <p>id: {{ route.params.id }}</p>
  </div>
</template>

<style>

</style>
