import { test, expect, vi } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import SviKoncertiView from '../SviKoncertiView.vue'

vi.mock('../../api/concertApi.js', () => ({
  concertApi: {
    getAll: () =>
      Promise.resolve([
        {
          id: 1,
          naslov: 'ime koncerta',
          artist: 'ime izvodaca',
          datum: '2027-01-01',
          drzava: 'Hrvatska',
        },
      ]),
  },
}))

test('prikazuje koncert na stranici', async () => {
  const wrapper = mount(SviKoncertiView, {
    global: {
      stubs: {
        RouterLink: { template: '<a><slot /></a>' },
      },
    },
  })
  await flushPromises()
  expect(wrapper.text()).toContain('ime koncerta')
  expect(wrapper.text()).toContain('ime izvodaca')
})
