<template>
  <div>
    <Navbar />
    <div class="components-page">
        <ToastComponent toastTitle="Oops!!! Page Not Found" :isCancel="true" />
    </div>
  </div>
</template>

<script>
import ToastComponent from "../components/common/Toast";
import Navbar from "../components/common/Navbar.vue";
import "../assets/css/components.scss";

export default {
  name: "NotFound",
  components: {
    ToastComponent,
    Navbar,
  }
};
</script>

<style scoped>
</style>