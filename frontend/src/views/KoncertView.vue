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
        <select v-model="izvodacId">
            <option disabled value="">Izvodac: </option>
            <option v-for="izvodac in artists" :key="izvodac.id" :value="izvodac.id">
                {{ izvodac.ime }}
            </option>
        </select><br>

        <label>Organizator: </label>
        <select v-model="organizatorId">
            <option disabled value="">Organizator: </option>
            <option v-for="organizator in organizers" :key="organizator.id" :value="organizator.id">
                {{ organizator.ime }}
            </option>
        </select><br>
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
import { concertApi } from '../api/concertApi.js'
import { artistApi } from '../api/artistApi.js'
import { organizerApi } from '../api/organizerApi.js'

export default {
  data() {
    return {
      koncert: null,
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
      artists: [],
      organizers: [],
      izvodacId: '',
      organizatorId: '',
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
    async ucitajIzvodace() {
      this.artists = await artistApi.getAll();
    },
    async ucitajOrganizatore() {
      this.organizers = await organizerApi.getAll();
    },
    async ucitajKoncert() {
      try {
        const data = await concertApi.getById(this.$route.params.id);
        this.koncert = data;
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
      this.izvodacId = this.koncert.artistId;
      this.organizatorId = this.koncert.organizerId;
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
        await concertApi.update(this.koncert.id, {
            name: this.naziv,
            dateTime: `${this.datum}T${this.vrijeme}`,
            description: this.opis,
            artistId: this.izvodacId,
            organizerId: this.organizatorId,
            country: this.drzava,
            city: this.grad,
            postalCode: this.koncert.postalCode,
            address: this.adresa,
        });
        this.success = 'Koncert uspješno ažuriran!';
        this.uredivanje = false;
        await this.ucitajKoncert();
      } catch (error) {
        console.error(error);
        this.error = 'Neuspjeh slanja podataka';
      }
    },
  },
  async created() {
    await this.ucitajKoncert();
    await this.ucitajIzvodace();
    await this.ucitajOrganizatore();
  },
};
</script>

<style>

</style>
