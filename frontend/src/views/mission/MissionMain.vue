<template>
  <div class="wrap">
    <v-app>
      <v-card class="mx-auto overflow-hidden missions" color="white">
        <v-app-bar color="deep-purlple" dark>
          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

          <v-toolbar-title>Mission</v-toolbar-title>
        </v-app-bar>
        <br />

        <MissionItem :missions="missionList" :followings="followings" />
        <br />
        <br />

        <!--미션 props를 MissionItem으로 넘김-->

        <v-navigation-drawer v-model="drawer" absolute temporary>
          <v-list nav dense>
            <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
              <div class="px-3 py-2">
                <div class="thumbnail">
                  <div class="centered">
                    <img :src="imagePath" />
                  </div>
                </div>
              </div>
              <hr />

              <v-list-item @click="mymission">
                <v-list-item-icon>
                  <i class="far fa-list-alt"></i>
                </v-list-item-icon>
                <p class="jua">내가 참여한 미션</p>
              </v-list-item>

              <v-list-item @click="mypost">
                <v-list-item-icon>
                  <i class="fas fa-user-edit"></i>
                </v-list-item-icon>
                <p class="jua">내가 쓴 글</p>
              </v-list-item>

              <v-list-item @click="mypoint">
                <v-list-item-icon>
                  <i class="fas fa-coins"></i>
                </v-list-item-icon>
                <p class="jua">내 포인트</p>
              </v-list-item>

              <v-list-item @click="myaccount">
                <v-list-item-icon>
                  <i class="fas fa-users-cog"></i>
                </v-list-item-icon>
                <p class="jua">내 계정설정</p>
              </v-list-item>

              <v-list-item @click="logout">
                <v-list-item-icon>
                  <i class="fas fa-sign-out-alt"></i>
                </v-list-item-icon>
                <p class="jua">로그아웃</p>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-navigation-drawer>
      </v-card>
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
import MissionItem from "../../components/mission/MissionItem.vue";
import "../../components/css/style.css";
import MissionApi from "../../api/MissionApi";
import FollowApi from "../../api/FollowApi"

export default {
  data: () => ({
    drawer: false,
    activeBtn: 1,
    missionList: [],
    user: Object,
    imagePath: "http://i3d201.p.ssafy.io:8080/profile/",
    index: 0,
    imageSplit: [],
    missionImagePath: "http://i3d201.p.ssafy.io:8080/category/",
    followings: [],
    members: [],
  }),
  components: {
    MissionItem,
  },
  created() {
    this.user = this.$session.get("user");
    this.imageSplit = this.user.memberPhoto.split("/");
    this.index = this.imageSplit.length - 1;
    this.imagePath += this.imageSplit[this.index];
    MissionApi.requestAllMission(
      // 미션 전체 리스트 받아오기
      (res) => {
        this.missionList = res.data;
        for (let i in this.missionList) {
          this.missionList[i].missionPhoto =
            this.missionImagePath + this.missionList[i].missionCat + ".jpg";
        }
      },
      (error) => {}
    );

    FollowApi.requestAllFollowingByNo(
      this.user.memberNo,
      (res) => {
        this.members = res.data;
        for (let i in this.members){
          this.followings.push(this.members[i].you)
        }
      },
      (error) => {},
    )
  },
  methods: {
    logout() {
      this.$router.push("/");
    },
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
    mymission() {
      this.$router.push("/mymission");
    },
    mypost() {
      this.$router.push("/mypost");
    },
    mypoint() {
      this.$router.push("/mypoint");
    },
    myaccount() {
      this.$router.push("/changeuser");
    },
  },
};
</script>

<style scoped>
.theme--dark.v-app-bar.v-toolbar.v-sheet {
  background: linear-gradient(to left, #f48fb1, #3949ab);
}
.thumbnail {
  position: relative;
  padding-top: 100%; /*1:1 ratio */
  overflow: hidden;
}
.thumbnail .centered {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  -webkit-transform: translate(50%, 50%);
  -ms-transform: translate(50%, 50%);
  transform: translate(50%, 50%);
}
.thumbnail .centered img {
  position: absolute;
  top: 0;
  left: 0;
  max-width: 100%;
  height: 90%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  border-radius: 50%;
  overflow: hidden;
}
.v-bottom-navigation {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 3rem;
}
.missions {
  margin-bottom: 3.5rem;
}
.v-item-group.v-bottom-navigation {
  max-width: 580px;
  width: 100%;
  margin: 0 auto;
}
.white {
  background-color: whitesmoke !important;
}
.v-toolbar__title {
  font-family: "Luckiest Guy", cursive;
  font-size: x-large;
}
.jua {
  font-family: "Jua", sans-serif;
}
.v-application .deep-purple--text.text--accent-4 {
  color: white !important;
  background: navy !important;
}
.v-responsive__content {
  background-color: transparent;
}
.v-app-bar__nav-icon.v-btn.v-btn--flat.v-btn--icon.v-btn--round.theme--dark.v-size--default {
  color: white !important;
}
.v-icon.notranslate.mdi.mdi-menu.theme--dark {
  color: white;
}
</style>
