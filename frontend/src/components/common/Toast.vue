<template>
    <div class="toast-component" :class="{error:isError}">
        <h5>
            {{toastTitle}}
        </h5>
        <button v-if="isCancel" @click="goBack">
            취소
        </button>
    </div>
</template>

<script>
    export default {
        name: "toast",
        props : ['toastTitle', 'isError', 'isCancel'],
        methods: {
            goBack(){ this.$router.go(-1) },
        }
    }
</script>
