<template>
    <select class="select-component jua">
        <option :value="null" disabled selected>미션 참여 인원을 설정하세요.</option>
        <option v-for="target in options" :value="target" :key="target.value">
            {{target.title}}
        </option>
    </select>
</template>

<script>
    export default {
        name: "select",
        props : ['options'],
    }
</script>
