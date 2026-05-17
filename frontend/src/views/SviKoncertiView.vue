<template>
  <div class="home-container">
    <h2>Projekti</h2>
    <div class="pretrazivac">
      <input type="text" v-model="trazilicaPojam" placeholder="pretraži..." />
      <button @click="pretrazi">Pretraži</button>
    </div>
    <div class="filteri">
      <label for="datumPoc">Datum od:</label>
      <input type="date" id="datumPoc" v-model="filterDatumPocetka" />
      <label for="datumKraj">Datum do:</label>
      <input type="date" id="datumKraj" v-model="filterDatumKraja" />
      <button @click="filtrirano">Filtriraj!</button>
    </div>
    <div v-if="error">
      {{ error }}
    </div>
  </div>
  <div v-if="filtriraniKoncerti.length">
    <ul>
      <li v-for="koncert in filtriraniKoncerti" :key="koncert.id" :class="{ 'zavrsen': jeZavrsen(koncert.datum) }">
        <KoncertItem>
          <template #naslov>
            <RouterLink :to="{ name: 'koncert', params: { id: koncert.id } }">
              {{ koncert.naslov }}
            </RouterLink>
          </template>
          <template #artist>{{koncert.artist}}</template>
          <template #datum>{{koncert.datum}}</template>
          <button @click="izbrisiKoncert(koncert.id)">Izbriši</button>
        </KoncertItem>
      </li>
    </ul>
  </div>
  <div v-else>
    <p>Nema projekata.</p>
  </div>
</template>

<script>
import { RouterLink } from 'vue-router'
import KoncertItem from '../components/KoncertItem.vue'
import { concertApi } from '../api/concertApi.js'

export default {
  components: {
    RouterLink,
    KoncertItem,
  },
  data() {
    return {
      filtriraniKoncerti: [],
      filterDatumKraja: '',
      filterDatumPocetka: '',
      error: null,
      trazilicaPojam: '',
    };
  },
  methods: {
    filtrirano() {
      this.filtriraniKoncerti = [];
      const filterDatumPocetka = this.filterDatumPocetka ? new Date(this.filterDatumPocetka) : null;
      const filterDatumKraja = this.filterDatumKraja ? new Date(this.filterDatumKraja) : null;
      for (const koncert of this.koncerti) {
        const datum = new Date(koncert.datum);
        if ((filterDatumPocetka === null || datum >= filterDatumPocetka) &&
            (filterDatumKraja === null || datum <= filterDatumKraja)) {
          this.filtriraniKoncerti.push(koncert);
        }
      }
    },
    async pretrazi() {
      this.filtriraniKoncerti = await concertApi.search(this.trazilicaPojam);
    },
    jeZavrsen(datum) {
      const koncertDatum = new Date(datum);
      const danasnji = new Date();
      if (koncertDatum < danasnji)
        return true;
      return false;
    },
    async ucitajKoncerte() {
      try {
        this.koncerti = await concertApi.getAll()
      } catch (e) {
        console.error('error:', e);
        this.error = 'greška pri dohvatu podataka';
      } finally {
        this.filtriraniKoncerti = this.koncerti;
      }
    },
    async izbrisiKoncert(id) {
      try {
        const response = await fetch(`/api/concerts/${id}`, {
          method: 'DELETE',
        });
        if (!response.ok) throw new Error(`API error: ${response.status}`);
        await this.ucitajKoncerte();
      } catch (e) {
        console.error('error:', e);
        this.error = 'greška pri brisanju koncerta';
      }
    },
  },
  async created() {
    await this.ucitajKoncerte();
  },
};
</script>

<style scoped>
li.zavrsen {
  opacity: 0.5;
}
</style>
