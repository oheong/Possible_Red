<template>
  <div class="wrapC p-0">
    <v-app>
      <div class="wrapC components-page p-1 jua">
        <HeaderComponent2 :headerTitle="category.categoryContent" :isBack="true" />
      </div>

      <!-- <v-btn small v-for="item in items" :key="item.missionNo" class="ma-2 mx-1" outlined color="indigo">{{item.missionTitle}}</v-btn> -->
      <SearchMissionCard :items="items" />
      <v-bottom-navigation v-model="bottomNav" black shift>
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
import "../../components/css/user.scss";
import "../../components/css/style.css";
import HeaderComponent2 from "../../components/common/Header2.vue";
import SearchMissionCard from "../../components/search/SearchMissionCard.vue";
import SearchApi from "../../api/SearchApi.js";

export default {
  name: "SearchByCategory",
  components: {
    HeaderComponent2,
    SearchMissionCard,
  },
  data() {
    return {
      category: Array,
      items: [],
      index: 0,
      imagePath: "http://i3d201.p.ssafy.io:8080/",
      imageSplit: [],
      missionImagePath: "http://i3d201.p.ssafy.io:8080/category/",
    };
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
  created() {
    this.category = this.$route.params.category;

    SearchApi.requestMissionByCategory(
      this.category.categoryNo,
      (res) => {
        this.items = res.data;
        for (let i in this.items) {
          this.items[i].missionPhoto =
            this.missionImagePath + this.items[i].missionCat + ".jpg";
        }
      },
      (error) => {
        console.error(error);
      }
    );
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
.v-item-group.v-bottom-navigation {
  max-width: 580px;
  width: 100%;
  margin: 0 auto;
}
.jua {
  font-family: "Jua", sans-serif;
}
</style>