<template>
  <div class="container">
    <h2>Unos novog koncerta</h2>

    <form @submit.prevent="kreirajKoncert">
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
      izvodacId: '',
      organizatorId: '',
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
      organizers: [],
      artists: [],
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
            artistId: this.izvodacId,
            country: this.drzava,
            city: this.grad,
            address: this.adresa,
            postalCode: this.postanskiBroj,
            organizerId: this.organizatorId,
          }),
        });
        if (!response.ok) throw new Error(`API error: ${response.status}`);
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
      this.organizatorId = '';
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
    async ucitajOrganizatore() {
      try {
        const response = await fetch('/api/organizers');
        if (!response.ok) throw new Error(`API error: ${response.status}`);
        const data = await response.json();
        this.organizers = data.map(org => ({
          id: org.id,
          ime: `${org.firstName} ${org.lastName}`,
        }));
      } catch (e) {
        console.error('error:', e);
        this.error = 'greška pri dohvatu organizatora';
      }
    },
    async ucitajIzvodace() {
      try {
        const response = await fetch('/api/artists');
        if (!response.ok) throw new Error(`API error: ${response.status}`);
        const data = await response.json();
        this.artists = data.map(artist => ({
          id: artist.id,
          ime: artist.name,
        }));
      } catch (e) {
        console.error('error:', e);
        this.error = 'greška pri dohvatu izvodaca.';
      }
    },
  },
  async created() {
    await this.ucitajOrganizatore();
    await this.ucitajIzvodace();
  },
};
</script>

<style>

</style>
