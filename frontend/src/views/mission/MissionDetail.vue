<!--미션 디테일 하나만 뽑아와서 미션 디테일카드로 값 넘겨줌-->
<template>
  <div class="wrapC p-0">
    <v-app>
      <v-card class="mx-auto overflow-hidden missions" color="white">
        <v-app-bar color="deep-purlple" dark>
          <HeaderComponent :isBack="true" />

          <v-toolbar-title>{{mission.missionTitle}}</v-toolbar-title>
        </v-app-bar>
        <br />

        <!--넣고 싶은거 넣으세요~-->

        <MissionDetailCard
          v-if="this.mission"
          :mission="mission"
          :num="num"
          :entryNum="entryNum"
          :entryList="entryList"
        />
      </v-card>

      <v-bottom-navigation v-model="bottomNav" black shift style="z-index:2;">
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
import "../../components/css/style.css";
import PostingDetailCard from "../../components/post/PostingDetailCard.vue";
import MissionDetailCard from "../../components/mission/MissionDetailCard.vue";
import MissionApi from "../../api/MissionApi";
import UserApi from "../../api/UserApi";
import HeaderComponent from "../../components/common/Header.vue";
import EntryApi from "../../api/EntryApi.js";

export default {
  data: () => ({
    drawer: false,
    activeBtn: 1,
    num: 0,
    entryNum: 0,
    mission: {
      missionNo: 0,
      missionTitle: "",
      startDate: "",
      endDate: "",
      isStart: false,
      memberNo: 0,
      point: 0,
      minusPoint: 0,
      cutCnt: 0,
      joinMem: 0,
      missionPhoto: "",
      missionCat: "",
      master: "",
    },
    entryList: [],
    missionImagePath: "http://i3d201.p.ssafy.io:8080/category/",
  }),
  created() {
    this.num = this.$route.params.num; // 상세하게 찾아올 미션 넘버

    MissionApi.requestMissionDetail(
      this.num,
      (res) => {
        this.mission.missionNo = this.num;
        this.mission.missionTitle = res.data.missionTitle;
        this.mission.startDate = res.data.startDate;
        this.mission.endDate = res.data.endDate;
        this.mission.isStart = res.data.isStart;
        this.mission.memberNo = res.data.memberNo;
        this.mission.point = res.data.point;
        this.mission.minusPoint = res.data.minusPoint;
        this.mission.cutCnt = res.data.cutCnt;
        this.mission.joinMem = res.data.joinMem;
        this.mission.missionPhoto =
          this.missionImagePath + res.data.missionCat + ".jpg";
        this.mission.missionCat = res.data.missionCat;
        UserApi.requestMemberByNo(
          // 사용자의 상세 정보를 반환한다.
          this.mission.memberNo,
          (res) => {
            this.mission.master = res.data.nickname;
          },
          (error) => {}
        );
      },
      (error) => {}
    );

    // 엔트리정보
    EntryApi.requestEntryCountByMissionNo(
      this.num,
      (res) => {
        this.entryNum = res.data; // 엔트리 카운트
      },
      (error) => {}
    );

    // 지금 미션 참여하고 있는 사람 리스트 가져오기
    EntryApi.requestEntryListByMissionNo(
      this.num,
      (res) => {
        this.entryList = res.data;
      },
      (error) => {}
    );
  },
  components: {
    MissionDetailCard,
    HeaderComponent,
  },
  methods: {
    kakaoLogout() {
      this.$session.destroy();
      window.Kakao.API.request({
        url: "/v1/user/unlink",
        success: function (res) {
          console.log(res);
        },
        fail: function (err) {
          console.log(err);
        },
      });
      window.Kakao.Auth.logout(function () {
        alert("로그아웃 완료!");
      });
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
.v-application--wrap {
  height: 100% !important;
}
.white {
  background-color: whitesmoke !important;
}
.v-toolbar__title {
  font-family: "Jua", sans-serif;
  font-size: x-large;
  margin: 0 0 0 20px;
}
.jua {
  font-family: "Jua", sans-serif;
}
.v-application .deep-purple--text.text--accent-4 {
  color: white !important;
  background: navy !important;
}
.v-responsive__content {
  background-color: rgb(34, 34, 34);
  opacity: 0.5;
  color: white !important;
  margin: 0 0 0 20px;
  height: 30px;
  width: 10%;
}
.v-card__title {
  font-family: "Nanum Pen Script", cursive;
  font-size: xx-large;
  padding: 0 0 0 20px;
}
</style>