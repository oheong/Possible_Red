<template>
  <div class="wrapC p-0">
    <v-app>
      <v-card style="margin-bottom: 10rem; box-shadow: none;">
        <v-tabs v-model="tab">
          <v-tab :key="item1">미션</v-tab>

          <v-tab :key="item2">게시글</v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item :key="item1">
            <v-card flat>
              <v-card-text>
                <MissionAdd />
              </v-card-text>
            </v-card>
          </v-tab-item>

          <v-tab-item :key="item2">
            <v-card flat>
              <v-card-text>
                <PostAdd />
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
      <v-bottom-navigation black shift>
        <v-btn @click="post">
          <span>POST</span>
          <v-icon>mdi-text</v-icon>
        </v-btn>

        <v-btn @click="mission">
          <span>Mission</span>
          <v-icon>mdi-clipboard</v-icon>
        </v-btn>

        <v-btn @click="writing">
          <span>Writing</span>
          <v-icon>mdi-pencil</v-icon>
        </v-btn>

        <v-btn @click="search">
          <span>Search</span>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn @click="profile">
          <span>Profile</span>
          <v-icon>mdi-account</v-icon>
        </v-btn>
      </v-bottom-navigation>
    </v-app>
  </div>
</template>

<script>
import MissionAdd from "../../components/add/MissionAdd.vue";
import PostAdd from "../../components/add/PostAdd.vue";
import "../../components/css/style.css";

export default {
  data() {
    return {
      tab: null,
    };
  },
  components: {
    MissionAdd,
    PostAdd,
  },
  methods: {
    post() {
      this.$router.push("/posts");
    },
    mission() {
      this.$router.push("/missionmain");
    },
    writing() {
      this.$router.push("/add");
    },
    search() {
      this.$router.push("/search");
    },
    profile() {
      this.$router.push("/profile");
    },
  },
};
</script>

<style scoped>
.v-bottom-navigation {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 3rem;
}
.v-tab.v-tab--active {
  color: blueviolet;
}
.v-item-group.v-bottom-navigation {
  max-width:580px;
  width: 100%;
  margin: 0 auto;
}
</style>
