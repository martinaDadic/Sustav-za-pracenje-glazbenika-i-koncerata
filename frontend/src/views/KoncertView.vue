<template>
  <div>
    <h1>Podaci o koncertu:</h1>
    <div v-if="koncert && uredivanje === false">
      <p>naslov: {{ koncert.naziv }}</p>
      <p>izvodac: {{ koncert.izvodac }}</p>
      <p>opis: {{ koncert.opis }}</p>
      <p>lokacija: {{ koncert.grad }}, {{ koncert.drzava }}</p>
      <p>adresa: {{ koncert.adresa }}</p>
      <p>datum: {{ koncertDatum }}</p>
      <p>vrijeme: {{ koncertVrijeme }}</p>
      <p>organizator: {{ koncert.organizatorIme }} {{ koncert.organizatorPrezime }}</p>
      <p>broj ljudi s namjerom dolaska: {{ koncert.attendees?.length ?? 0 }}</p>
      <button @click="ukljuciUredivanje()">Uredi detalje</button>
    </div>
    <div v-if="error">
      {{ error }}
    </div>
    <div v-if="uredivanje">
      <form @submit.prevent="urediKoncert">
        <label>Naslov: </label>
        <input type="text" v-model="naziv" placeholder="naslov" /><br>
        <label>Izvodac: </label>
        <input type="text" v-model="izvodac" placeholder="izvodac" /><br>
        <label>Organizator: </label>
        <input type="text" v-model="organizatorIme" placeholder="ime" />
        <input type="text" v-model="organizatorPrezime" placeholder="prezime" /><br>
        <label>Kratki opis: </label>
        <textarea v-model="opis" placeholder="kratki opis" rows="5"></textarea><br>
        <label>Datum: </label>
        <input type="date" v-model="datum" /><br>
        <label>Vrijeme početka: </label>
        <input type="time" v-model="vrijeme" /><br>
        <label>Država: </label>
        <input type="text" v-model="drzava" placeholder="drzava" /><br>
        <label>Grad: </label>
        <input type="text" v-model="grad" placeholder="grad" /><br>
        <label>Adresa: </label>
        <input type="text" v-model="adresa" placeholder="adresa" /><br>
        
        <p v-if="datumGreska" class="error">{{ datumGreska }}</p>
        <br>
        <button type="submit" :disabled="datumError">Uredi projekt</button>
        <p class="error" v-if="error">{{ error }}</p>
        <p class="success" v-if="success">{{ success }}</p>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      error: null,
      success: '',
      koncertDatum: '',
      koncertVrijeme: '',
      uredivanje: false,
      naziv: '',
      izvodac: '',
      organizatorIme: '',
      organizatorPrezime: '',
      opis: '',
      datum: '',
      vrijeme: '',
      drzava: '',
      grad: '',
      adresa: '',
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
    async ucitajKoncert() {
      try {
        const response = await fetch(`/api/concerts/${this.$route.params.id}`);
        this.koncert = await response.json();
        this.formatirajDatum(this.koncert.datum);
      } catch (e) {
        console.error('error:', e);
        this.error = 'Greška pri dohvatu podataka';
      }
    },
    formatirajDatum(datum){
      var pom=datum.split("T");
      this.koncertDatum=pom[0];
      this.koncertVrijeme=pom[1];
    },
    ukljuciUredivanje() {
      this.naziv = this.koncert.naziv;
      this.izvodac = this.koncert.izvodac;
      this.organizatorIme = this.koncert.organizatorIme;
      this.organizatorPrezime = this.koncert.organizatorPrezime;
      this.opis = this.koncert.opis;
      this.datum = this.koncertDatum;
      this.vrijeme = this.koncertVrijeme;
      this.drzava = this.koncert.drzava;
      this.grad = this.koncert.grad;
      this.adresa = this.koncert.adresa;
      this.error = '';
      this.success = '';
      this.uredivanje = true;
    },
    async urediKoncert() {
      if (this.datumError) {
        this.error = 'Molimo unesite ispravan datum.';
        return;
      }
      if (!this.naziv || !this.opis || !this.datum || !this.vrijeme) {
        this.error = 'Molimo unesite sve informacije.';
        return;
      }
      try {
        await fetch(`/api/concerts/${this.koncert.id}`, {
          method: 'PUT',
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
            organizerFirstName: this.organizatorIme,
            organizerLastName: this.organizatorPrezime,
          }),
        });
        if (!response.ok) throw new Error(`API error: ${response.status}`);
        this.koncert.naziv = this.naziv;
        this.koncert.izvodac = this.izvodac;
        this.koncert.organizatorIme = this.organizatorIme;
        this.koncert.organizatorPrezime = this.organizatorPrezime;
        this.koncert.opis = this.opis;
        this.koncert.datum = `${this.datum}T${this.vrijeme}:00`;
        this.koncert.drzava = this.drzava;
        this.koncert.grad = this.grad;
        this.koncert.adresa = this.adresa;
        this.formatirajDatum(this.koncert.datum);

        this.success = 'Koncert uspješno ažuriran!';
        this.uredivanje = false;
      } catch (error) {
        console.error(error);
        this.error = 'Neuspjeh slanja podataka';
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
