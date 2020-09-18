<template>
  <div>
    <v-row style="padding:0%" class="mx-auto">
      <v-col cols="12" sm="12">
        <v-card>
          <v-container fluid>
            <v-row style="padding: 0;">
              <v-col
                v-for="(item, i) in this.items"
                :key="i"
                class="d-flex child-flex"
                cols="6"
                style="padding:1%;"
              >
                <v-card flat tile class="d-flex">
                  <v-img
                    :src="item.missionPhoto"
                    aspect-ratio="1"
                    class="grey lighten-2"
                  >
                    <v-card-title class="jua" v-text="item.missionTitle"></v-card-title>
                    <template v-slot:placeholder>
                        <v-row class="fill-height ma-0" align="center" justify="center">
                        <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                        </v-row>
                    </template>
                  </v-img>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
    name: "MissionCards",
    props: {
      items: Array,
    }
}
</script>

<style>
.jua{
    font-family: 'Jua', sans-serif;
}
</style>