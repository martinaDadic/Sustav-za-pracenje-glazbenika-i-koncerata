<template>
  <div class="container">
    <h2>Unos novog koncerta</h2>

    <form @submit.prevent="kreirajKoncert">
      <input type="text" v-model="naziv" placeholder="naslov" /><br>
      <input type="text" v-model="izvodac" placeholder="izvodac" /><br>
      <label>Organizator: </label>
      <input type="text" v-model="organizatorIme" placeholder="ime" />
      <input type="text" v-model="organizatorPrezime" placeholder="prezime" /><br>
      <textarea v-model="opis" placeholder="kratki opis" rows="5"></textarea><br>
      <label>Datum: </label>
      <input type="date" v-model="datum" /><br>
      <label>Vrijeme početka: </label>
      <input type="time" v-model="vrijeme" /><br>
      <input type="text" v-model="drzava" placeholder="drzava" /><br>
      <input type="text" v-model="grad" placeholder="grad" /><br>
      <input type="text" v-model="adresa" placeholder="adresa" /><br>
      <input type="text" v-model="postanskiBroj" placeholder="postanski broj" /><br>
      
      <p v-if="datumGreska" class="error">{{ datumGreska }}</p>

      <br>
      <button type="submit" :disabled="datumError">Kreiraj projekt</button>
      <p class="error" v-if="error">{{ error }}</p>
      <p class="success" v-if="success">{{ success }}</p>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      naziv: '',
      izvodac: '',
      organizatorIme: '',
      organizatorPrezime: '',
      opis: '',
      brojLjudi: 0,
      datum: '',
      vrijeme: '',
      drzava: '',
      grad: '',
      adresa: '',
      postanskiBroj: '',
      error: '',
      success: '',
      datumGreska: '',
    };
  },
  computed: {
    datumError() {
      this.datumGreska = '';
      if (this.datum) {
        const datum = new Date(this.datum);
        const danas = new Date();
        if (datum < danas) {
          this.datumGreska = 'Datum ne smije biti u prošlosti.';
          return true;
        }
      }
      return false;
    },
  },
  methods: {
    async kreirajKoncert() {
      if (this.datumGreska) {
        this.error = 'Molimo unesite ispravan datum.';
        return;
      }
      if (!this.naziv || !this.opis || !this.datum || !this.vrijeme) {
        this.error = 'Molimo unesite sve informacije.';
        return;
      }
      try {
        const response = await fetch('/api/concerts', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            name: this.naziv,
            dateTime: `${this.datum}T${this.vrijeme}:00`,
            description: this.opis,
            artistName: this.izvodac,
            country: this.drzava,
            city: this.grad,
            address: this.adresa,
            postalCode: this.postanskiBroj,
            organizerFirstName: this.organizatorIme,
            organizerLastName: this.organizatorPrezime,
          }),
        });
        this.success = 'Koncert uspješno kreiran!';
        this.clearForm();
        this.$router.push('/');
      } catch (error) {
        console.error(error);
        this.error = 'Neuspjeh slanja podataka';
      }
    },
    clearForm() {
      this.naziv = '';
      this.izvodac = '';
      this.organizator = '';
      this.opis = '';
      this.brojLjudi = '';
      this.datum = '';
      this.vrijeme = '';
      this.drzava = '';
      this.grad = '';
      this.error = '';
      this.success = '';
      this.datumGreska = '';
    },
  },
};
</script>

<style>

</style>
