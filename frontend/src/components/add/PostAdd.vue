<!-- https://kanetami.tistory.com/97 -->
<template>
  <div class="wrapC">
    <div class="post-adds">
      <div class="wrap components-page p-0">
        <select @change="getvalue($event)" class="select-component jua">
          <option :value="null" disabled selected>게시글을 올릴 미션을 선택하세요</option>
          <option
            v-for="target in options"
            :value="target.value"
            :key="target.value"
          >{{target.title}}</option>
        </select>
      </div>

      <div class="filebox jua" style="vertical-align:middle;">
        <input class="upload-name" :value="postImg.name" disabled="disabled" />
        <label for="ex_filename">업로드</label>
        <input
          id="ex_filename"
          class="upload-hidden"
          v-on:change="fileSelect()"
          type="file"
          ref="postImg"
        />
        <div v-if="postImg">
          <img :src="preView" class="pre_view" />
        </div>
      </div>

      <div class="wrap components-page p-0">
        <div class="textarea-wrap jua">
          <h4 class="jua">게시글 작성하기</h4>
          <span class="jua">{{post.postContent.length}}/{{this.maxLength}}</span>
          <br />
          <hr />
          <textarea v-model="post.postContent" placeholder="내용을 입력하세요" />
        </div>
      </div>

      <v-combobox
        v-model="chips"
        chips
        clearable
        label="Hashtags"
        multiple
        solo
        class="jua"
        style="height:30%; width:100%"
      >
        <template v-slot:selection="{ attrs, item, select, selected }">
          <v-chip
            v-bind="attrs"
            :input-value="selected"
            close
            @click="select"
            @click:close="remove(item)"
          >
            <strong>{{ item }}</strong>&nbsp;
          </v-chip>
        </template>
      </v-combobox>

      <!-- <HashtagBar style="height: 30%; width: 100%;" /> -->

      <div>
        <button class="submit_button btn-bottom" @click="postRegister">인증하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import MissionApi from "../../api/MissionApi"; // 멤버넘버별 포스트 받아오기
import PostApi from "../../api/PostApi";
import HashtagApi from "../../api/HashtagApi";

export default {
  watch: {
    "post.postContent": function (value) {
      let length = this.maxLength;
      value = value.length > length ? value.substr(0, length) : value;
      this.post.postContent = value;
    },
  },

  created() {
    this.post.memberNo = this.$session.get("user").memberNo;

    //지금 접속해 있는 사람의 아이디를 기반으로 참여하고있는 미션 리스트 가져오기
    MissionApi.requestMissionByMember(
      this.post.memberNo,
      (res) => {
        // 연결 성공하면 여기로 와서 리스트 뽑아냄
        this.missionList = res.data;

        for (let i in this.missionList) {
          let option = {
            value: this.missionList[i].missionNo,
            title: this.missionList[i].missionTitle,
          };
          this.options.push(option);
        }
      },
      (error) => {}
    );
  },
  components: {
    // HashtagBar,
  },
  data() {
    return {
      post: {
        postNo: 0,
        memberNo: 0,
        // postDate: Object,
        postPhoto: "",
        postContent: "",
        missionNo: 0,
        postImg: "",
      },
      postImg: "",
      missionList: [],
      preView: "",

      maxLength: 25,

      options: [],
      chips: [],
      hashtag: "",
    };
  },
  methods: {
    getvalue(event) {
      this.post.missionNo = event.target.value;
    },
    postRegister() {
      this.post.postImg = this.postImg;
      var formData = new FormData();

      formData.append("postImg", this.postImg);
      formData.append("memberNo", this.post.memberNo);
      formData.append("postContent", this.post.postContent);
      formData.append("missionNo", this.post.missionNo);

      for (var i in this.chips) {
        this.hashtag += "#" + this.chips[i];
      }

      PostApi.requestInsertPost(
        formData,
        (res) => {
          PostApi.requestMaxPostNo(
            (res) => {
              HashtagApi.requestAddHashtag(
                {
                  hashtagContent: this.hashtag,
                  postNo: res.data,
                },
                (res) => {},
                (error) => {}
              );
            },
            (error) => {}
          );
          this.$router.push("/posts");
        },
        (error) => {}
      );
    },

    fileSelect() {
      this.postImg = this.$refs.postImg.files[0];
      // 미리보기
      this.preView = URL.createObjectURL(this.$refs.postImg.files[0]);
    },
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
  },
};
</script>

<style>
.submit_button {
  margin-bottom: 3rem;
}
.post-adds {
  margin-bottom: 7.5rem;
}
.jua {
  font-family: "Jua", sans-serif;
}
.jua_small {
  font-size: medium;
  font-family: "Jua", sans-serif;
}
#postContent {
  text-align: center;
  float: left;
  margin: 10% 10%;
}

.filebox {
  margin-bottom: 10px;
}

.filebox input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}

.filebox label {
  height: 100%;
  margin-left: 5px;
  margin-bottom: 0;
  display: inline-block;
  padding: 0.5em 0.75em;
  color: rgb(255, 255, 255);
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #9575cd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: 0.25em;
  font-family: "Jua", sans-serif;
} /* named upload */

.filebox .upload-name {
  width: 70%;
  display: inline-block;
  padding: 0.5em 0.75em; /* label의 패딩값과 일치 */
  font-size: inherit;
  font-family: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #ebebeb;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: 0.25em;
  -webkit-appearance: none; /* 네이티브 외형 감추기 */
  -moz-appearance: none;
  appearance: none;
}

.pre_view {
  width: 100%;
  height: 100%;
}

.v-select.v-select--chips input {
  min-height: 32px;
}
b,
strong {
  font-family: "Jua", sans-serif;
  color: white;
}
.theme--light.v-chip:not(.v-chip--active) {
  background: #ff80ab;
}
.v-icon.notranslate.v-chip__close.v-icon--link.v-icon--right.mdi.mdi-close-circle.theme--light {
  color: black;
}
#input-119 {
  font-family: "Jua", sans-serif;
}
</style>
