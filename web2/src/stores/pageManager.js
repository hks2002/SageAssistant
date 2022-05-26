import { ebus } from '@/boot/ebus'
import { i18n } from '@/boot/i18n'
import { defineStore } from 'pinia'

const { t } = i18n.global

const usePagesStore = defineStore('pages', {
  state: () => ({
    activeId: 'Home',
    activeName: 'Home',
    activeLabel: 'Home',
    activeQuery: {},
    activeParams: {},
    activeModel: {},
    // { name:'Home', label: 'Home'}
    pages: [
      {
        name: 'Home',
        label: 'Home',
        id: 'Home',
        query: {},
        params: {},
        model: {}
      }
    ]
  }),

  getters: {},

  actions: {
    addPage(page) {
      if (!this.pages.find((r) => r.id === page.id)) {
        page.label = t(page.name)
        this.pages.push(page)
      }
      this.activeId = page.id
      this.activeName = page.name
      this.activeQuery = page.query
      this.activeParams = page.params
      this.activeLabel = page.label
      this.activeModel = null
    },
    removePage(pageId) {
      if (pageId === this.activeId) {
        const idx = this.pages.findIndex((r) => r.id === pageId)

        if (idx + 1 == this.pages.length) {
          // it's the last page
          this.activeId = this.pages[idx - 1].id
          this.activeName = this.pages[idx - 1].name
          this.activeLabel = this.pages[idx - 1].label
          this.activeQuery = this.pages[idx - 1].query
          this.activeParams = this.pages[idx - 1].params
          this.activeModel = this.pages[idx - 1].model
        } else {
          // it's not the last page
          this.activeId = this.pages[idx + 1].id
          this.activeName = this.pages[idx + 1].name
          this.activeLabel = this.pages[idx + 1].label
          this.activeQuery = this.pages[idx + 1].query
          this.activeParams = this.pages[idx + 1].params
          this.activeModel = this.pages[idx + 1].model
        }
      }
      // remove the page
      this.pages.splice(
        this.pages.findIndex((r) => r.id === pageId),
        1
      )
    },
    setActivePage(pageId) {
      const pageFind = this.pages.find((r) => r.id === pageId)
      if (pageFind) {
        this.activeId = pageFind.id
        this.activeName = pageFind.name
        this.activeLabel = pageFind.label
        this.activeQuery = pageFind.query
        this.activeParams = pageFind.params
        this.activeModel = pageFind.model
      }
    },
    setPageModel(pageId, model) {
      const idx = this.pages.findIndex((r) => r.id === pageId)
      if (idx >= 0) {
        this.pages[idx].model = model
      }
    },
    hasPage(pageId) {
      const idx = this.pages.findIndex((r) => r.id === pageId)
      return idx < 0 ? false : true
    },
    updateLabel() {
      this.pages.forEach((r, idx) => {
        this.pages[idx].label = t(r.name)
      })
    }
  }
})

// language change handing
ebus.on('changeLanguage', () => {
  usePagesStore().updateLabel()
})

export { usePagesStore }
