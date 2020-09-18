<!-- https://vuejsexamples.com/simple-and-clean-calendar-written-in-vue-js/ -->
<template>
  <div class="wrapC">
    <v-app>
      <div class="form-wrap mission-adds">
        <v-chip-group mandatory active-class="primary--text" class="jua" style="color: black;">
          <v-chip
            v-for="cat in category"
            :key="cat.categoryNo"
            @click="toggleSwitch(cat.categoryNo)"
          >{{ cat.categoryContent }}</v-chip>
        </v-chip-group>

        <br />

        <div class="input-with-label jua">
          <label for="missionTitle" class="jua">미션 이름</label>
          <input
            id="missionTitle"
            type="text"
            v-model="mission.missionTitle"
            placeholder="미션 제목을 입력하세요."
          />
        </div>

        <div class="input-with-label jua">
          <label for="description" class="jua">설명</label>
          <input
            id="description"
            type="text"
            v-model="mission.description"
            placeholder="미션을 간단하게 설명해주세요."
          />
          <br />
          <br />
          <br />
        </div>

        <div class="input-with-label">
          <label>미션 수행 기간</label>
          <v-range-selector :start-date.sync="range.start" :end-date.sync="range.end" />
        </div>

        <div class="wrap components-page p-0">
          <select v-model="mission.joinMem" class="select-component jua">
            <option :value="null" disabled selected>미션 참여 인원을 설정하세요.</option>
            <option
              v-for="target in options"
              :value="target.value"
              :key="target.value"
            >{{target.title}}</option>
          </select>
        </div>

        <div class="input-with-label jua">
          <label for="point" class="jua">배당 포인트</label>
          <input v-model="mission.point" id="point" placeholder="미션 입장 포인트를 입력하세요." type="text" />
        </div>

        <div class="input-with-label jua">
          <label for="minusPoint" class="jua">차감 포인트</label>
          <input
            v-model="mission.minusPoint"
            id="minusPoint"
            placeholder="미션 미수행시 차감될 포인트를 입력하세요."
            type="text"
          />
        </div>

        <div class="input-with-label jua">
          <label for="cutCnt" class="jua">커트라인</label>
          <input
            v-model="mission.cutCnt"
            id="cutCnt"
            placeholder="최소 미션 미수행 숫자를 입력하세요."
            type="text"
          />
        </div>

        <br />
        <br />
        <div>
          <button class="submit_button btn-bottom" @click="missionRegister">미션 등록하기</button>
        </div>
      </div>
    </v-app>
  </div>
</template>

<script>
import VRangeSelector from "../common/vl-range-selector";
import "../css/vuelendar.scss";
import MissionApi from "../../api/MissionApi.js";
import SearchApi from "../../api/SearchApi.js";
import EntryApi from "../../api/EntryApi.js";

export default {
  components: {
    VRangeSelector,
  },
  created() {
    this.mission.memberNo = this.$session.get("user").memberNo;
    this.getCategoryList();
  },
  data() {
    return {
      mission: {
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
        description: "",
        v0: true,
      },
      models: {
        base: false,
      },
      range: {},
      category: [],
      isOn: [
        false,
        true,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
      ],

      options: [
        { value: 1, title: "1 명" },
        { value: 2, title: "2 명" },
        { value: 3, title: "3 명" },
        { value: 4, title: "4 명" },
        { value: 5, title: "5 명" },
        { value: 6, title: "6 명" },
        { value: 7, title: "7 명" },
        { value: 8, title: "8 명" },
        { value: 9, title: "9 명" },
        { value: 10, title: "10 명" },
      ],
    };
  },

  methods: {
    toggleSwitch(num) {
      this.isOn[num] = true;
      for (var i in this.isOn) {
        if (i != num) {
          this.isOn[i] = false;
        }
      }
    },

    getCategoryList() {
      SearchApi.requestCategory(
        (res) => {
          this.category = res.data;
        },
        (error) => {
          console.error(error);
        }
      );
    },

    getDateFullString() {
      let today = new Date();
      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1; // 월
      let date = today.getDate(); // 날짜
      if (month < 10) month = "0" + month;
      if (date < 10) date = "0" + date;
      return year + "-" + month + "-" + date;
    },

    missionRegister() {
      this.mission.startDate = this.range.start;
      this.mission.endDate = this.range.end;

      // 여기서 시작 날짜랑 오늘 날짜 비교해서 시작 == 오늘이면 isStart는 바로 true로 해주기
      let today = this.getDateFullString();

      if (today == this.mission.startDate) this.mission.isStart = true;

      for (var i = 0; i < 10; i++) {
        if (this.isOn[i]) this.mission.missionCat = i;
      }

      MissionApi.requestMissionRegister(
        this.mission,
        (res) => {
          let entry = {
            memberNo: this.mission.memberNo,
            missionNo: res.data,
            reward: 0,
            nonCnt: 0,
          };

          EntryApi.requestEntryRegister(
            entry,
            (resentry) => {
              // console.log("entry regist success");
            },
            (error) => {
              // console.log("entry regist fail");
            }
          );

          this.$router.push("/mymission");
        },
        (error) => {}
      );
    },
  },
};
</script>

<style>
.submit_button {
  margin-bottom: 3rem;
}
.mission-adds {
  margin-bottom: 0rem;
  padding-bottom: 3rem;
}
.v-slide-group__content {
  background: white !important;
}
.jua {
  font-family: "Jua", sans-serif;
}
.v-tab.v-tab--active {
  font-family: "Jua", sans-serif;
  font-size: x-large;
}
.v-tab {
  font-family: "Jua", sans-serif;
  font-size: middle;
}
.categories {
  color: black !important;
  font-family: "Jua", sans-serif;
  font-size: large;
  display: inline-block;
  vertical-align: middle;
  height: 100%;
}
.v-btn:not(.v-btn--round).v-size--small {
  border-color: white;
}
.ma-2.mx-1.v-btn.v-btn--depressed.v-btn--flat.v-btn--outlined.theme--light.v-size--small {
  background: #eceff1;
}

.v-btn__content {
  color: white;
}
.vl-calendar-month__title {
  font-family: "Jua", sans-serif;
  font-size: large;
  color: #673ab7;
}
.vl-calendar-month__week-day {
  font-family: "Jua", sans-serif;
  font-size: medium;
  color: black;
}
.vl-calendar-month__day {
  font-family: "Jua", sans-serif;
  font-size: medium;
  color: #546e7a;
}
.vl-calendar-month__day:hover:not(.selected) {
  background: black;
}
.vl-calendar-month__day.selected.selected--first.selected--last.disabled.disabled--first.disabled--last {
  color: yellow;
  background: black;
  border: transparent;
}
.vl-calendar-month__day.selected.selected--first {
  background: #f48fb1;
}
.vl-calendar-month__day.selected {
  background: #ec407a;
}
.vl-calendar-month__day.selected.selected--last {
  background: #880e4f;
}
label.jua {
  margin: -4px 0 5px;
  font-size: medium;
}
.input-with-label.jua {
  text-align: right;
}
.submit_button.btn-bottom {
  background: linear-gradient(to left, #f48fb1, #3949ab);
  border-color: transparent;
  border-radius: 5px;
  font-family: "Jua", sans-serif;
  font-size: large;
}
.vl-calendar {
  background-color: white;
}
#joinMem {
  margin: 10px 10px;
  float: left;
}
span.v-chip__content {
  font-family: "Jua", sans-serif;
  color: white;
}
.v-chip.primary--text.v-chip--active.v-chip--clickable.v-chip--no-color.theme--light.v-size--default {
  background: navy;
}
</style>
