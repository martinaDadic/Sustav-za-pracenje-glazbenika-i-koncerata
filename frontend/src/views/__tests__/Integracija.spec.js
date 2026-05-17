import { test, expect, vi, beforeEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import { concertApi } from '../../api/concertApi.js'
import SviKoncertiView from '../SviKoncertiView.vue'

let listaKoncerata = []

beforeEach(() => {
  listaKoncerata = []

  vi.stubGlobal('fetch', (url, opcije = {}) => {
    if (opcije.method === 'POST' && url.includes('/concerts')) {
      const podaci = JSON.parse(opcije.body)
      const novi = {
        id: listaKoncerata.length + 1,
        name: podaci.name,
        dateTime: podaci.dateTime,
        artistName: 'Jakov Jozinovic',
      }
      listaKoncerata.push(novi)
      return Promise.resolve({ json: () => Promise.resolve(novi) })
    }

    if (url.includes('/concerts')) {
      return Promise.resolve({ json: () => Promise.resolve(listaKoncerata) })
    }
  })
})

test('upisan koncert može se pročitati kroz concertApi', async () => {
  await concertApi.create({
    name: 'Jakov Jozinovic u Areni',
    dateTime: '2027-01-01T21:00:00',
    description: 'opis',
    artistId: 1,
    organizerId: 1,
    country: 'Hrvatska',
    city: 'Zagreb',
    address: 'Jarunska ulica 2',
    postalCode: '10000',
  })
  const koncerti = await concertApi.getAll()
  expect(koncerti[0].naslov).toBe('Jakov Jozinovic u Areni')
  expect(koncerti[0].artist).toBe('Jakov Jozinovic')
  expect(koncerti[0].id).toBe(1)
})

test('frontend prikazuje što vrati concertApi', async () => {
  listaKoncerata.push({
    id: 1,
    name: 'Vojko V u Tvornici',
    dateTime: '2027-01-02T20:00:00',
    artistName: 'Vojko V',
  })
  const ekran = mount(SviKoncertiView, {
    global: {
      stubs: {
        RouterLink: { template: '<a><slot /></a>' },
      },
    },
  })
  await flushPromises()
  const prikazaniTekst = ekran.text()
  expect(prikazaniTekst).toContain('Vojko V u Tvornici')
  expect(prikazaniTekst).toContain('2027-01-02')
})
