<template >
  <q-item-label class="relative-position">
    <q-btn v-for="file in files"
           :key="file.ROWID"
           type="a"
           :icon="getDocIcon(file.DocType)"
           class="q-mr-sm"
           @click="clickBtn(file.Path)"
           dense>
      <q-badge v-if="file.Cat==='Drawing'"
               floating>D</q-badge>
      <q-badge v-else-if="file.Cat==='Manual'"
               floating>M</q-badge>
      <q-badge v-else
               floating>?</q-badge>
      <q-tooltip v-if="file.Cat==='Drawing'"
                 floating>Drawing:{{file.File}}</q-tooltip>
      <q-tooltip v-else-if="file.Cat==='Manual'"
                 floating>Manual:{{file.File}}</q-tooltip>
      <q-tooltip v-else
                 floating>Unknown Category:{{file.File}}</q-tooltip>
      <q-menu touch-position
              context-menu>
        <q-btn icon="fas fa-trash-alt"
               color="teal"
               class="q-mr-sm"
               @click="doDeleteFile(file.Path)"
               dense />
      </q-menu>
    </q-btn>

    <q-btn v-if="pn != '' "
           icon="fas fa-file-medical"
           color="teal"
           class="q-mr-sm"
           dense
           @click="showFileUploader = true" />
    <q-dialog v-model="showFileUploader">
      <q-card>
        <q-card-section>
          <div class="text-h6">Choose the category of your files</div>
        </q-card-section>
        <q-card-section class="q-pt-none">
          <div class="q-gutter-sm">
            <q-option-group v-model="upLoadFileCategory"
                            :options="upLoadFileCategoryOptions"
                            color="teal" />
          </div>
          <q-uploader url="/Data/FileUpload"
                      :form-fields="setUploadParams(this.pn,upLoadFileCategory)"
                      field-name="file"
                      multiple
                      color="teal"
                      style="width: 100%" />
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat
                 label="Close"
                 color="teal"
                 v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>

    <q-inner-loading :showing="showLoadingFile">
      <q-spinner-ios size="50px"
                     color="primary" />
    </q-inner-loading>
  </q-item-label>
</template>

<script>

if (process.env.DEV) {
  require('../mock/AttachmentPath')
}
export default {
  name: 'QItemLabelFileList',
  props: {
    pn: String,
    isActive: Boolean
  },
  components: {
  },
  data () {
    return {
      files: [],
      showLoadingFile: false,
      showFileUploader: false,
      upLoadFileCategory: 'Drawing',
      upLoadFileCategoryOptions: [
        {
          label: 'Drawing',
          value: 'Drawing'
        },
        {
          label: 'Manual',
          value: 'Manual'
        }
      ]
    }
  },
  methods: {
    getDocIcon (docType) {
      if (docType === 'PDF') {
        return 'fas fa-file-pdf'
      } else if (docType === 'BMP' || docType === 'TIF' || docType === 'JPG' || docType === 'JPEG') {
        return 'fas fa-file-image'
      } else if (docType === 'ZIP' || docType === 'RAR' || docType === '7Z') {
        return 'fas fa-file-archive'
      } else if (docType === 'DOC' || docType === 'DOCX') {
        return 'fas fa-file-word'
      } else if (docType === 'XLS' || docType === 'XLSX') {
        return 'fas fa-file-excel'
      } else {
        return 'fas fa-file'
      }
    },
    clickBtn (path) {
      console.log(path)
      window.open(path)
    },
    setUploadParams (pn, cat) {
      let params = [
        { name: 'Pn', value: pn },
        { name: 'Cat', value: cat }]
      return params
    },
    doDeleteFile (path) {
      console.log(path)
      this.$axios.get('/Data/FileDelete?Path=' + path)
        .then((response) => {
        })
        .catch((e) => {
          console.error(e)
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Delete' + path + ' Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    doUpdate (pn) {
      this.showLoadingFile = true

      this.$axios.get('/Data/AttachmentPath?Pn=' + pn)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.files = response.data
          this.showLoadingFile = false
          this.showFileUploader = false
        })
        .catch((e) => {
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Attachment Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    doReset () {
      this.files = []
      this.showLoadingFile = false
      this.showFileUploader = false
    }
  },
  watch: {
    pn: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (newVal) {
          this.doUpdate(newVal)
        } else {
          this.doReset()
        }
      },
      immediate: true
    }
  },
  mounted: function () {
  }
}
</script>
