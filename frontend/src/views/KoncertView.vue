<template>
  <div>
    <h1>Podaci o koncertu:</h1>
    <div v-if="koncert">
      <p>naziv: {{ koncert.naziv }}</p>
      <p>izvodac: {{ koncert.izvodac }}</p>
      <p>opis: {{ koncert.opis }}</p>
      <p>lokacija: {{ koncert.grad }}, {{ koncert.drzava }}</p>
      <p>adresa: {{ koncert.adresa }}</p>
      <p>organizator: {{ koncert.organizatorIme }} {{ koncert.organizatorPrezime }}</p>

    </div>
    <div v-if="error">
      {{ error }}
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      koncert: {
        id: 1,
        naziv: 'Jakov Jozinović u Areni',
        izvodac: 'Jakov Jozinović',
        datum: '2026-06-10T20:00:00',
        grad: 'Zagreb',
        drzava: 'Hrvatska',
        adresa: 'Jarunska ul. 2',
        opis: 'Jakov Jozinović pjevač je tog i tog žanra, dolazi u arenu.',
        organizatorIme: 'Martina',
        organizatorPrezime: 'Dadić',
      },
      error: null,
    };
  },
  methods: {
    async ucitajKoncert() {
      try {
        const response = await fetch(`/api/concerts/${this.$route.params.id}`);
        this.koncert = await response.json();
      } catch (e) {
        console.error('error:', e);
        this.error = 'Greška pri dohvatu podataka';
      }
    },
  },
  async created() {
    await this.ucitajKoncert();
  },
};
</script>

<style>

</style>
