<template>
  <div>
    <v-card style="box-shadow: none; padding-top:3%">
      <v-tabs background-color="white" color="deep-purple accent-4" centered>
        <v-tab style="font-family: 'Jua', sans-serif;">포스트</v-tab>
        <v-tab style="font-family: 'Jua', sans-serif;">미션</v-tab>

        <v-tab-item>
          <v-row style="padding:0 2%" class="mx-auto">
            <v-col cols="12" sm="12" class="mx-auto">
              <v-card>
                <v-container fluid>
                  <v-row style="padding: 0;">
                    <v-col
                      v-for="(post, i) in this.myPost"
                      :key="i"
                      class="d-flex child-flex"
                      cols="4"
                      style="padding:1%;"
                    >
                      <v-card flat tile class="d-flex">
                        <v-img
                          @click="goPostDetail(post.postNo)"
                          :src="post.postPhoto"
                          aspect-ratio="1"
                          class="grey lighten-2"
                        >
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
        </v-tab-item>

        <v-tab-item>
          <v-row style="padding:0 2%" class="mx-auto">
            <v-col cols="12" sm="12" class="mx-auto">
              <v-card>
                <v-container fluid>
                  <v-row style="padding: 0;">
                    <v-col
                      v-for="(mission, i) in this.myMission"
                      :key="i"
                      class="d-flex child-flex"
                      cols="4"
                      style="padding:1%;"
                    >
                      <v-card flat tile class="d-flex">
                        <v-img :src="mission.missionPhoto" aspect-ratio="1" class="grey lighten-2">
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
        </v-tab-item>
      </v-tabs>
    </v-card>
  </div>
</template>

<script>
import PostApi from "../../api/PostApi";
import MissionApi from "../../api/MissionApi";

export default {
  name: "ProfileBody",
  props: {
    user: {},
  },
  created() {
    PostApi.requestSelectPostByMember(
      this.user.memberNo,
      (res) => {
        this.myPost = res.data;
        for (let i in this.myPost) {
          this.imageSplit = this.myPost[i].postPhoto.split("/");
          this.index = this.imageSplit.length - 1;
          this.myPost[i].postPhoto =
            this.imagePath + this.imageSplit[this.index];
        }
      },
      (error) => {}
    );

    MissionApi.requestMissionByMember(
      this.user.memberNo,
      (res) => {
        this.myMission = res.data;
        for (let i in this.myMission) {
          this.myMission[i].missionPhoto =
            this.missionImagePath + this.myMission[i].missionCat + ".jpg";
        }
      },
      (error) => {}
    );
  },
  methods: {
    goPostDetail(num) {
      this.$router.push({
        name: "PostDetail",
        params: {
          num: num,
        },
      });
    },
  },
  data() {
    return {
      myPost: [],
      myMission: [],
      imagePath: "http://i3d201.p.ssafy.io:8080/",
      index: 0,
      imageSplit: [],

      missionImagePath: "http://i3d201.p.ssafy.io:8080/category/",
    };
  },
};
</script>

<style>
.v-slide-group__content.v-tabs-bar__content {
  background: transparent;
}
.v-application .deep-purple--text.text--accent-4 {
  color: #8e24aa;
}
.v-slide-group__content {
  background: white;
}
.v-tab.v-tab--active {
  color: #8e24aa;
}
.v-tab {
  color: #ce93d8;
}
.v-tabs-slider {
  color: #7e57c2;
}
</style>