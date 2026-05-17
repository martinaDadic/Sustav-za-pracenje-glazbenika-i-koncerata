const BASE_URL = 'http://localhost:8080/api'

export const concertApi = {
    async search(term) {
        const params = new URLSearchParams()
        params.append('term', term)
        
        const data = await fetch(`${BASE_URL}/concerts/search?${params}`)
            .then(res => res.json())
        
        return data.map(koncert => {
          const date = new Date(koncert.dateTime);
          return {
            id: koncert.id,
            naslov: koncert.name,
            artist: koncert.artistName,
            datum: date.toISOString().slice(0, 10),
            drzava: koncert.country,
          };
        })
    },

    async getAll() {
        const data = await fetch(`${BASE_URL}/concerts`)
            .then(res => res.json())

        return data.map(koncert => {
          const date = new Date(koncert.dateTime);
          return {
            id: koncert.id,
            naslov: koncert.name,
            artist: koncert.artistName,
            datum: date.toISOString().slice(0, 10),
            drzava: koncert.country,
          };
        })
    },

    async getById(id) {
        const data = await fetch(`${BASE_URL}/concerts/${id}`)
            .then(res => res.json())
        
        return {
            id: data.id,
            naziv: data.name,
            izvodac: data.artist.name,
            artistId: data.artist.id,
            organizerId: data.organizer.id,
            opis: data.description,
            grad: data.city,
            drzava: data.country,
            adresa: data.address,
            postalCode: data.postalCode,
            datum: data.dateTime,
            organizatorIme: data.organizer.firstName,
            organizatorPrezime: data.organizer.lastName,
            attendees: data.attendees,
        }
    },

    async create(dto) {
        return fetch(`${BASE_URL}/concerts`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        }).then(res => res.json())
    },

    update(id, dto) {
        return fetch(`${BASE_URL}/concerts/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        }).then(res => res.json())
    },

    delete(id) {
        return fetch(`${BASE_URL}/concerts/${id}`, {
            method: 'DELETE'
        })
    }
}