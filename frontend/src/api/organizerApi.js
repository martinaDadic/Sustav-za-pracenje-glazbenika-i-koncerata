const BASE_URL = 'http://localhost:8080/api'

export const organizerApi = {
    async getAll() {
        const data = await fetch(`${BASE_URL}/organizers`)
            .then(res => res.json())

        return data.map(organizer => {
          return {
            id: organizer.id,
            ime: `${organizer.firstName} ${organizer.lastName}`,
          };
        })
    },
}
