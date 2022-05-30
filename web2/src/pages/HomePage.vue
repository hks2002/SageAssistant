<template>
  <q-page class="flex flex-center">
    <Vue3Lottie animationLink="/json/working.json" background="transparent" />
    <div class="text-h1 text-primary">
      {{ $t('Welcome to SageAssistant') }}
    </div>
  </q-page>
</template>

<script setup>
import { Vue3Lottie } from 'vue3-lottie'
</script>
