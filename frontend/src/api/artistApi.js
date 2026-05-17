const BASE_URL = 'http://localhost:8080/api'

export const artistApi = {
    async getAll() {
        const data = await fetch(`${BASE_URL}/artists`)
            .then(res => res.json())

        return data.map(artist => {
          return {
            id: artist.id,
            ime: artist.name,
          };
        })
    },
}
