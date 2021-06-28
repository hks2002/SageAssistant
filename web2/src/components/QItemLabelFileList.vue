<template >
  <q-item-label class="relative-position">
    <q-btn
      v-for="file in files"
      :key="file.ROWID"
      type="a"
      :icon="getDocIcon(file.DocType)"
      class="q-mr-sm"
      @click="clickBtn(file.Path)"
      dense
    >
      <q-badge
        v-if="file.Cat==='Drawing'"
        floating
      >D</q-badge>
      <q-badge
        v-else-if="file.Cat==='Manual'"
        floating
      >M</q-badge>
      <q-badge
        v-else-if="file.Cat==='Certificate'"
        floating
      >C</q-badge>
      <q-badge
        v-else
        floating
      >?</q-badge>
      <q-tooltip
        v-if="file.Cat==='Drawing'"
        floating
      >Drawing:{{file.File}}</q-tooltip>
      <q-tooltip
        v-else-if="file.Cat==='Manual'"
        floating
      >Manual:{{file.File}}</q-tooltip>
      <q-tooltip
        v-else-if="file.Cat==='Certificate'"
        floating
      >Certificate:{{file.File}}</q-tooltip>
      <q-tooltip
        v-else
        floating
      >Unknown Category:{{file.File}}</q-tooltip>
      <q-menu
        touch-position
        context-menu
      >
        <q-btn
          icon="fas fa-trash-alt"
          color="teal"
          class="q-mr-sm"
          @click="doDeleteFile(file.Path)"
          dense
        />
      </q-menu>
    </q-btn>

    <q-btn
      v-if="pn"
      icon="fas fa-file-medical"
      color="teal"
      class="q-mr-sm"
      dense
      @click="showFileUploader = true"
    />
    <q-dialog v-model="showFileUploader">
      <q-card>
        <q-card-section>
          <div class="text-h6">Choose the category of your files</div>
        </q-card-section>
        <q-card-section class="q-pt-none">
          <div class="q-gutter-sm">
            <q-option-group
              v-model="upLoadFileCategory"
              :options="upLoadFileCategoryOptions"
              color="teal"
            />
          </div>
          <q-uploader
            url="/Data/FileUpload"
            :form-fields="setUploadParams(this.pn,upLoadFileCategory)"
            field-name="file"
            multiple
            color="teal"
            style="width: 100%"
          />
        </q-card-section>
        <q-card-actions align="right">
          <q-btn
            flat
            label="Close"
            color="teal"
            v-close-popup
          />
        </q-card-actions>
      </q-card>
    </q-dialog>

    <q-inner-loading :showing="showLoading">
      <q-spinner-ios
        size="50px"
        color="primary"
      />
    </q-inner-loading>
  </q-item-label>
</template>

<script>
import { defineComponent, ref, onMounted, watch } from 'vue'
import { notifyError, notifySuccess } from 'assets/common'
import { axios } from 'boot/axios'

export default defineComponent({
  name: 'QItemLabelFileList',

  props: {
    pn: String,
    isActive: Boolean
  },

  components: {
  },

  setup(props, ctx) {
    const files = ref([])
    const showLoading = ref(false)
    const showFileUploader = ref(false)
    const upLoadFileCategory = ref('Drawing')
    const upLoadFileCategoryOptions = ref([
      {
        label: 'Drawing',
        value: 'Drawing'
      },
      {
        label: 'Manual',
        value: 'Manual'
      }
    ])

    const getDocIcon = (docType) => {
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
    }

    const clickBtn = (path) => {
      console.debug('open:' + path)
      window.open(path)
    }

    const setUploadParams = (pn, cat) => {
      const params = [
        { name: 'Pn', value: pn },
        { name: 'Cat', value: cat }]
      return params
    }

    const doDeleteFile = (path) => {
      console.debug('deleteFile:' + path)
      $axios.get('/Data/FileDelete?Path=' + path)
        .then((response) => {
          notifySuccess(response.data)
        })
        .catch((e) => {
          console.error(e)
          notifyError('Delete' + path + ' Failed!')
        })
    }

    const doUpdate = (pn) => {
      showLoading.value = true

      axios.get('/Data/AttachmentPath?Pn=' + pn)
        .then((response) => {
          files.value = response.data
          showFileUploader.value = false
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading Attachment Failed!')
        }).finally(() => {
          showLoading.value = false
        })
    }

    onMounted(() => {
      console.debug('onMounted')
      if (props.pn) {
        doUpdate(props.pn)
      }
    })

    // Don't use watchEffect, it run before Mounted.
    watch(() => props.pn, (newVal, oldVal) => {
      console.debug('watch:' + oldVal + ' ---> ' + newVal)
      if (newVal) {
        doUpdate(newVal)
      }
    })

    return {
      files,
      showLoading,
      showFileUploader,
      upLoadFileCategory,
      upLoadFileCategoryOptions,
      getDocIcon,
      clickBtn,
      doDeleteFile,
      setUploadParams
    }
  }
})
</script>
