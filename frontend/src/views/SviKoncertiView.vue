<script setup>
import { RouterLink } from 'vue-router'
import KoncertItem from '../components/KoncertItem.vue'

</script>

<template>
  <RouterLink to="/koncert/1">
    <KoncertItem>
      <template #naslov>Naziv prvog</template>
      <template #datum>1.1.2026.</template>
      Neki opis.
    </KoncertItem>
  </RouterLink>
  <RouterLink to="/koncert/2">
    <KoncertItem>
      <template #naslov>Naziv drugog</template>
      <template #datum>1.1.2026.</template>
      Neki opis.
    </KoncertItem>
  </RouterLink>

</template>
